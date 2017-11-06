package com.galaxy.intergalactic.handler;


import com.galaxy.intergalactic.RomanNumber;
import com.galaxy.intergalactic.dao.GalacticUnitDao;
import com.galaxy.intergalactic.dao.RomanNumeralDao;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;
import java.util.StringTokenizer;

@Component
public class GalacticUnitInputLineHandler implements InputLineHandler {
    private RomanNumeralDao romanNumeralDao;
    private GalacticUnitDao galacticUnitDao;

    @Autowired
    public GalacticUnitInputLineHandler(RomanNumeralDao romanNumeralDao, GalacticUnitDao galacticUnitDao) {
        this.romanNumeralDao = romanNumeralDao;
        this.galacticUnitDao = galacticUnitDao;
    }

    @Override
    public boolean canHandle(String line) {
        String lastWord = line.substring(line.lastIndexOf(" ") + 1);
        return "Credits".equals(lastWord);
    }

    @Override
    public void handle(String line) {
        StringTokenizer lineTokenizer = new StringTokenizer(line);

        StringBuilder romanNumberBuilder = new StringBuilder();
        String galacticName = null;
        while (lineTokenizer.hasMoreTokens()) {
            String token = lineTokenizer.nextToken();
            RomanNumber romanNumber = romanNumeralDao.get(token);
            if (romanNumber == null) {
                galacticName = token;
                lineTokenizer.nextToken();
                break;
            }
            romanNumberBuilder.append(romanNumber.name());
        }

        int value = evaluateRomanNumber(romanNumberBuilder);
        if (value == 0) {
            return;
        }

        galacticUnitDao.add(galacticName, NumberUtils.toDouble(lineTokenizer.nextToken(), 0)/value);
    }

    private int evaluateRomanNumber(StringBuilder romanNumberBuilder) {
        try {
            return RomanNumber.evaluate(romanNumberBuilder.toString());
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
