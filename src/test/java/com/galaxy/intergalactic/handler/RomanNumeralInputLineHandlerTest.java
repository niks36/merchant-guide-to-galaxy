package com.galaxy.intergalactic.handler;

import com.galaxy.intergalactic.RomanNumber;
import com.galaxy.intergalactic.dao.RomanNumeralDao;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by punit.patel on 5/11/17.
 */
public class RomanNumeralInputLineHandlerTest {

    @Test
    public void canHandle() throws Exception {
        RomanNumeralDao mockDao = Mockito.mock(RomanNumeralDao.class);
        RomanNumeralInputLineHandler handler = new RomanNumeralInputLineHandler(mockDao);
        assertThat(handler.canHandle("glob is I"), is(true));
        assertThat(handler.canHandle("prok is V"), is(true));
        assertThat(handler.canHandle("glob glob silver is 34 Credits"), is(false));
    }

    @Test
    public void handle() throws Exception {
        RomanNumeralDao mockDao = Mockito.mock(RomanNumeralDao.class);
        RomanNumeralInputLineHandler handler = new RomanNumeralInputLineHandler(mockDao);
        handler.handle("glob is I");
        verify(mockDao, times(1)).add("glob", RomanNumber.I);
    }

}