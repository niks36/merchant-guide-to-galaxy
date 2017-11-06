package com.galaxy.intergalactic.handler;

import com.galaxy.intergalactic.RomanNumber;
import com.galaxy.intergalactic.dao.RomanNumeralDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.StringTokenizer;

@Component
public class RomanLineResponseHandler implements InputLineHandler {
    private RomanNumeralDao romanNumeralDao;

    @Autowired
    public RomanLineResponseHandler(RomanNumeralDao romanNumeralDao) {
        this.romanNumeralDao = romanNumeralDao;
    }

    @Override
    public boolean canHandle(String line) {
        return line.startsWith("how much");
    }

    @Override
    public void handle(String line) {
        try {
            line = line.substring(12, line.lastIndexOf("?") - 1);
            StringTokenizer tokenizer = new StringTokenizer(line);
            StringBuilder builder = new StringBuilder();
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                if ("?".equals(token)) {
                    break;
                }
                RomanNumber romanNumber = romanNumeralDao.get(token);
                if (romanNumber == null) {
                    InputLineHandler.super.handle(line);
                    return;
                }
                builder.append(romanNumber);
            }
            int evaluate = RomanNumber.evaluate(builder.toString());
            System.out.println(line + " is " + evaluate);
        } catch (Exception e) {
            //TODO log error
            InputLineHandler.super.handle(line);
        }


    }
}
