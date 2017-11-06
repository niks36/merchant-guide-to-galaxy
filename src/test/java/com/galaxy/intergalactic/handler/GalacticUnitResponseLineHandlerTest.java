package com.galaxy.intergalactic.handler;

import com.galaxy.intergalactic.RomanNumber;
import com.galaxy.intergalactic.dao.GalacticUnitDao;
import com.galaxy.intergalactic.dao.RomanNumeralDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by punit.patel on 6/11/17.
 */
public class GalacticUnitResponseLineHandlerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void canHandle() throws Exception {
        RomanNumeralDao romanNumeralDao = Mockito.mock(RomanNumeralDao.class);
        GalacticUnitDao galacticUnitDao = Mockito.mock(GalacticUnitDao.class);
        GalacticUnitResponseLineHandler handler = new GalacticUnitResponseLineHandler(romanNumeralDao, galacticUnitDao);
        assertThat(handler.canHandle("glob is I"), is(false));
        assertThat(handler.canHandle("prok is V"), is(false));
        assertThat(handler.canHandle("glob glob Silver is 34 Credits"), is(false));
        assertThat(handler.canHandle("how much is pish tegj glob glob ?"), is(false));
        assertThat(handler.canHandle("how many Credits glob pork Silver ?"), is(true));
    }

    @Test
    public void handle() throws Exception {
        RomanNumeralDao romanNumeralDao = Mockito.mock(RomanNumeralDao.class);
        when(romanNumeralDao.get("glob")).thenReturn(RomanNumber.I);
        when(romanNumeralDao.get("prok")).thenReturn(RomanNumber.V);
        GalacticUnitDao galacticUnitDao = Mockito.mock(GalacticUnitDao.class);
        when(galacticUnitDao.get("Silver")).thenReturn(Double.valueOf(17));
        GalacticUnitResponseLineHandler handler = new GalacticUnitResponseLineHandler(romanNumeralDao, galacticUnitDao);
        handler.handle("how many Credits is glob prok Silver ? ");
        assertThat(outContent.toString(), is("glob prok Silver is 68.0 Credits\n"));
    }

}