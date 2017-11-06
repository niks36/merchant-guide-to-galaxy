package com.galaxy.intergalactic.dao;

import com.galaxy.intergalactic.RomanNumber;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by punit.patel on 5/11/17.
 */
public class InMemoryRomanNumeralDaoTest {

    @Test
    public void testDao() throws Exception {
        InMemoryRomanNumeralDao inMemoryRomanDao = new InMemoryRomanNumeralDao();
        assertThat(inMemoryRomanDao.get("glob"), nullValue());
        inMemoryRomanDao.add("glob", RomanNumber.I);
        inMemoryRomanDao.add("prok", RomanNumber.V);
        inMemoryRomanDao.add("tagj", RomanNumber.X);
        inMemoryRomanDao.add("pish", RomanNumber.L);
        inMemoryRomanDao.add("rock", RomanNumber.C);
        inMemoryRomanDao.add("mork", RomanNumber.D);
        inMemoryRomanDao.add("noms", RomanNumber.M);

        assertThat(inMemoryRomanDao.get("glob"), is(RomanNumber.I));
        assertThat(inMemoryRomanDao.get("prok"), is(RomanNumber.V));
        assertThat(inMemoryRomanDao.get("tagj"), is(RomanNumber.X));
        assertThat(inMemoryRomanDao.get("pish"), is(RomanNumber.L));
        assertThat(inMemoryRomanDao.get("rock"), is(RomanNumber.C));
        assertThat(inMemoryRomanDao.get("mork"), is(RomanNumber.D));
        assertThat(inMemoryRomanDao.get("noms"), is(RomanNumber.M));

    }


}