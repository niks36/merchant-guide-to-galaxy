package com.galaxy.intergalactic.handler;

import com.galaxy.intergalactic.RomanNumber;
import com.galaxy.intergalactic.dao.RomanNumeralDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by punit.patel on 5/11/17.
 */
@Component
public class RomanNumeralInputLineHandler implements InputLineHandler {
    private RomanNumeralDao romanNumeralDao;

    @Autowired
    public RomanNumeralInputLineHandler(RomanNumeralDao romanNumeralDao) {
        this.romanNumeralDao = romanNumeralDao;
    }

    @Override
    public boolean canHandle(String line) {
        char lastChar = line.charAt(line.length() - 1);
        return Arrays.stream(RomanNumber.values()).anyMatch(romanNumber -> romanNumber.name().equals(Character.toString(lastChar)));
    }

    @Override
    public void handle(String line) {
        String romanNumeral = line.substring(0, line.indexOf(" "));
        romanNumeralDao.add(romanNumeral, RomanNumber.valueOf(line.substring(line.length() - 1)));
    }
}
