package com.galaxy.intergalactic.dao;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by punit.patel on 5/11/17.
 */
public class InMemoryGalacticUnitDaoTest {

    @Test
    public void testAdd() {
        GalacticUnitDao galacticUnitDao = new InMemoryGalacticUnitDao();
        galacticUnitDao.add("Silver", Double.valueOf(34));
        assertThat(galacticUnitDao.get("Silver"), is(Double.valueOf(34)));
    }

}