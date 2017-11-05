package com.galaxy.intergalactic;

import org.junit.Test;

import javax.xml.bind.ValidationException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by punit.patel on 5/11/17.
 */
public class RomanNumberTest {

    @Test
    public void evaluate() throws Exception {
        assertThat(RomanNumber.evaluate("MMVI"), is(equalTo(2006 )));
        assertThat(RomanNumber.evaluate("MCMXLIV"), is(equalTo(1944 )));
    }

    @Test(expected = ValidationException.class)
    public void evaluateShouldThrowValidationExceptionForInvalidRomanSymbol() throws Exception {
        try {
            RomanNumber.evaluate("MMVIY");
        } catch (ValidationException e) {
            assertThat(e.getMessage(), is(equalTo("Invalid Roman Symbol Y")));
            throw e;
        }
    }

    @Test(expected = ValidationException.class)
    public void evaluateShouldThrowValidationExceptionForSubtraction() throws Exception {
        try {
            RomanNumber.evaluate("MCMXMIV");
        } catch (ValidationException e) {
            assertThat(e.getMessage(), is(equalTo("Roman Number X can not subtracted from Roman Number M")));
            throw e;
        }
    }

    @Test(expected = ValidationException.class)
    public void evaluateShouldThrowValidationExceptionRepeatation() throws Exception {
        try {
            RomanNumber.evaluate("MMMM");
        } catch (ValidationException e) {
            assertThat(e.getMessage(), is(equalTo("Roman Number M can be repeated for 3 times")));
            throw e;
        }
    }


}