package com.galaxy.intergalactic.handler;

import com.galaxy.intergalactic.RomanNumber;
import com.galaxy.intergalactic.dao.GalacticUnitDao;
import com.galaxy.intergalactic.dao.RomanNumeralDao;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by punit.patel on 5/11/17.
 */
public class GalacticUnitInputLineHandlerTest {

    @Test
    public void canHandle() throws Exception {
        RomanNumeralDao romanNumeralDao = Mockito.mock(RomanNumeralDao.class);
        GalacticUnitDao mockGalacticUnitDao = Mockito.mock(GalacticUnitDao.class);
        GalacticUnitInputLineHandler handler = new GalacticUnitInputLineHandler(romanNumeralDao, mockGalacticUnitDao);
        assertThat(handler.canHandle("glob is I"), is(false));
        assertThat(handler.canHandle("prok is V"), is(false));
        assertThat(handler.canHandle("glob glob Silver is 34 Credits"), is(true));
    }

    @Test
    public void handle() throws Exception {
        RomanNumeralDao romanNumeralDao = Mockito.mock(RomanNumeralDao.class);
        when(romanNumeralDao.get("glob")).thenReturn(RomanNumber.I);
        GalacticUnitDao mockGalacticUnitDao = Mockito.mock(GalacticUnitDao.class);
        GalacticUnitInputLineHandler handler = new GalacticUnitInputLineHandler(romanNumeralDao, mockGalacticUnitDao);
        handler.handle("glob glob Silver is 34 Credits");
        verify(mockGalacticUnitDao, times(1)).add("Silver", Double.valueOf(17));
    }

    @Test
    public void handler_should_not_invoke_handle_method() throws Exception {
        RomanNumeralDao romanNumeralDao = Mockito.mock(RomanNumeralDao.class);
        GalacticUnitDao mockGalacticUnitDao = Mockito.mock(GalacticUnitDao.class);
        GalacticUnitInputLineHandler handler = new GalacticUnitInputLineHandler(romanNumeralDao, mockGalacticUnitDao);
        handler.handle("glob glob Silver is 34 Credits");
        verify(mockGalacticUnitDao, times(0)).add(Mockito.anyString(), Mockito.anyDouble());
    }

}