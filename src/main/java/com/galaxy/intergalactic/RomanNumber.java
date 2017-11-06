package com.galaxy.intergalactic;

import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.ValidationException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum RomanNumber {
    I(1, 3, Arrays.asList('V', 'X')),
    V(5, 0, Collections.emptyList()),
    X(10, 3, Arrays.asList('L', 'C')),
    L(50, 0, Collections.emptyList()),
    C(100, 3, Arrays.asList('D', 'M')),
    D(500, 0, Collections.emptyList()),
    M(1000, 3, Arrays.asList('I', 'V', 'X', 'L', 'C', 'D'));

    private final int value;
    private final int noOfRepeatAllowed;
    private final List<Character> subtractedFrom;
    private static Map<Character, RomanNumber> romanNumberMap = createRomanNumberMap();

    private static Map<Character, RomanNumber> createRomanNumberMap() {
        return Arrays.stream(RomanNumber.values())
                .collect(Collectors.toMap(o -> o.name().toCharArray()[0], Function.identity()));
    }

    RomanNumber(int value, int noOfRepeatAllowed, List<Character> subtractedFrom) {
        this.value = value;
        this.noOfRepeatAllowed = noOfRepeatAllowed;
        this.subtractedFrom = subtractedFrom;
    }

    public int getValue() {
        return value;
    }

    public static int evaluate(String expression) throws ValidationException {
        if (StringUtils.isBlank(expression)) {
            return 0;
        }
        char[] roman = expression.toCharArray();
        int sum = fromChar(roman[roman.length - 1]).getValue();
        int count = 1;
        for (int i = roman.length - 2; i >= 0; --i) {
            RomanNumber current = fromChar(roman[i]);
            RomanNumber after = fromChar(roman[i + 1]);
            if (after.getValue() == current.getValue()) {
                /*validate no of repeat*/
                count++;
                if (count > current.noOfRepeatAllowed) {
                    throw new ValidationException(String.format("Roman Number %s can be repeated for %d times", current.name(), current.noOfRepeatAllowed));
                }
                sum += current.getValue();
                continue;
            }

            count = 1;
            if (after.getValue() < current.getValue()) {
                sum += current.getValue();
            } else {
                char afterChar = roman[i + 1];
                /*validate subtracted From */
                if (current.subtractedFrom.stream().noneMatch(character -> character.equals(afterChar))) {
                    throw new ValidationException(String.format("Roman Number %c can not subtracted from Roman Number %c", roman[i], afterChar));
                }
                sum -= current.getValue();
            }
        }
        return sum;
    }

    public static RomanNumber fromChar(char romanSymbol) throws ValidationException {
        RomanNumber romanNumber = romanNumberMap.get(romanSymbol);
        if (romanNumber == null) {
            throw new ValidationException(String.format("Invalid Roman Symbol %c", romanSymbol));
        }
        return romanNumber;
    }

}