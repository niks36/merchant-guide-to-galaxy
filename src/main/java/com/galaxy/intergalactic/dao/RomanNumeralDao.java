package com.galaxy.intergalactic.dao;

import com.galaxy.intergalactic.RomanNumber;

public interface RomanNumeralDao {
    void add(String romanNumeral, RomanNumber romanNumber);

    RomanNumber get(String romanNumeral);
}
