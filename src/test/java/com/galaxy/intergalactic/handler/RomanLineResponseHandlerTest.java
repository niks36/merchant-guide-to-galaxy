package com.galaxy.intergalactic.handler;

import com.galaxy.intergalactic.RomanNumber;
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

public class RomanLineResponseHandlerTest {

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
        RomanLineResponseHandler handler = new RomanLineResponseHandler(romanNumeralDao);
        assertThat(handler.canHandle("glob is I"), is(false));
        assertThat(handler.canHandle("prok is V"), is(false));
        assertThat(handler.canHandle("glob glob Silver is 34 Credits"), is(false));
        assertThat(handler.canHandle("how much is pish tegj glob glob ?"), is(true));
    }

    @Test
    public void handle() throws Exception {
        RomanNumeralDao romanNumeralDao = Mockito.mock(RomanNumeralDao.class);
        when(romanNumeralDao.get("pish")).thenReturn(RomanNumber.X);
        when(romanNumeralDao.get("tegj")).thenReturn(RomanNumber.L);
        when(romanNumeralDao.get("glob")).thenReturn(RomanNumber.I);
        RomanLineResponseHandler handler = new RomanLineResponseHandler(romanNumeralDao);
        handler.handle("how much is pish tegj glob glob ?");
        assertThat(outContent.toString(), is("pish tegj glob glob is 42\n"));
    }

}