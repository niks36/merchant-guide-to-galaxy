package com.galaxy.intergalactic.dao;

import com.galaxy.intergalactic.RomanNumber;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryRomanNumeralDao implements RomanNumeralDao {
    private Map<String, RomanNumber> dataMap;

    public InMemoryRomanNumeralDao() {
        dataMap = new HashMap<>();
    }

    @Override
    public void add(String romanNumeral, RomanNumber romanNumber) {
        dataMap.put(romanNumeral, romanNumber);
    }

    @Override
    public RomanNumber get(String romanNumeral) {
        return dataMap.get(romanNumeral);
    }
}
