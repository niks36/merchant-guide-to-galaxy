package com.galaxy.intergalactic.handler;

import com.galaxy.intergalactic.RomanNumber;
import com.galaxy.intergalactic.dao.GalacticUnitDao;
import com.galaxy.intergalactic.dao.RomanNumeralDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.StringTokenizer;

@Component
public class GalacticUnitResponseLineHandler implements InputLineHandler {
    private RomanNumeralDao romanNumeralDao;
    private GalacticUnitDao galacticUnitDao;

    @Autowired
    public GalacticUnitResponseLineHandler(RomanNumeralDao romanNumeralDao, GalacticUnitDao galacticUnitDao) {
        this.romanNumeralDao = romanNumeralDao;
        this.galacticUnitDao = galacticUnitDao;
    }

    @Override
    public boolean canHandle(String line) {
        return line.startsWith("how many");
    }

    @Override
    public void handle(String line) {
        try {
            line = line.substring(20, line.lastIndexOf("?") - 1);
            StringTokenizer tokenizer = new StringTokenizer(line);
            StringBuilder romanBuilder = new StringBuilder();
            Double galacticUnitValue = Double.valueOf(0);
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                RomanNumber romanNumber = romanNumeralDao.get(token);
                if (romanNumber != null) {
                    romanBuilder.append(romanNumber);
                    continue;
                }
                galacticUnitValue = galacticUnitDao.get(token);
            }
            int value = RomanNumber.evaluate(romanBuilder.toString());
            System.out.println(line + " is " + value * galacticUnitValue.doubleValue() + " Credits");

        } catch (Exception e) {
            //TODO log exception and error
            InputLineHandler.super.handle(line);
        }

    }
}
