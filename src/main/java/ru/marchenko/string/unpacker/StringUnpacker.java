package ru.marchenko.string.unpacker;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.marchenko.string.unpacker.exception.InvalidStringFormatException;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Class, that unpacks strings
 *
 * @author Created by Vladislav Marchenko on 19.03.2021
 */
public class StringUnpacker {

    /**
     * Method, that unpacks strings of the form:
     * x[...], where x - count of repeats, ... - Latin letters sequence.
     * It is also worth noting that when unpacking a string, it is validated.
     * A check is in progress for the correctness of characters
     * (valid characters are Latin letters, numbers and square brackets),
     * the correctness of the placement of brackets.
     * It also checks that the number must be followed by an open bracket.
     * For example, the following line is incorrect: 9a9.
     * If the string is invalid, an InvalidStringFormatException is thrown.
     *
     * @param str input string, that need to be unpacked
     * @return unpacked string
     */
    public String unpack(String str) {

        // String builder for output unpacked string
        StringBuilder result = new StringBuilder();

        // String builder for current count of repeats
        StringBuilder currentRepeatCountString = new StringBuilder();

        // Stack that contains repeatable substrings
        Deque<Part> parts = new ArrayDeque<>();

        // Stack that contains brackets of input string (for validation)
        Deque<Character> brackets = new ArrayDeque<>();

        // Flag that says whether we are in a repeating part or not
        boolean isRepeatablePart = false;

        // Char array from input string
        char[] chars = str.toCharArray();

        // Loop for each character from input string
        for (int i = 0; i < chars.length; i++) {
            // If we are not in repeatable part ant current symbol is not digit and open bracket, then
            if (!isRepeatablePart && !isDigit(chars[i]) && !isOpenBracket(chars[i])) {
                // If current symbol is not a letter or this symbol is after digit (situation as "9a9"),
                // then throw exception
                if (!isLetter(chars[i]) || !currentRepeatCountString.toString().isEmpty()) {
                    throwException(i);
                }
                // else append this symbol to the output string builder
                result.append(chars[i]);
            }
            // If current symbol is digit, then append it to the string builder
            // for current count of repeats
            else if (isDigit(chars[i])) {
                currentRepeatCountString.append(chars[i]);
            }
            // If current symbol is a [ we are coming in repeatable substring
            else if (isOpenBracket(chars[i])) {
                // If current count of repeats string is empty, then throw an exception.
                // This is an incorrect situation as "aaa[aaa]", for example.
                if (currentRepeatCountString.toString().isEmpty()) {
                    throwException(i);
                }
                // Add [ into brackets stack
                brackets.addLast(chars[i]);
                // We come in repeatable part
                isRepeatablePart = true;
                // Add this repeatable part as last element of the stack
                parts.addLast(new Part(Integer.parseInt(currentRepeatCountString.toString()), new StringBuilder()));
                currentRepeatCountString = new StringBuilder();
            }
            // If we are in repeatable part ant current symbol is not digit and bracket, then
            else if (isRepeatablePart && !isDigit(chars[i]) && !isCloseBracket(chars[i]) && !isOpenBracket(chars[i])) {
                // If current symbol is not a letter or this symbol is after digit (situation as "9a9"),
                // then throw exception
                if (!isLetter(chars[i]) || !currentRepeatCountString.toString().isEmpty()) {
                    throwException(i);
                }
                // else append this symbol to the last repeatable part
                parts.getLast().string.append(chars[i]);
            }
            // If the current symbol is close bracket
            else if (isCloseBracket(chars[i])) {
                // Brackets validation
                if (brackets.isEmpty()) {
                    throwException(i);
                } else {
                    brackets.pollLast();
                }
                // If parts stack size > 1, then
                if (parts.size() > 1) {
                    // Pop last part from stack
                    Part part = parts.pollLast();
                    // Append into new last part this string repeated required number of times
                    parts.getLast().string.append(
                            part != null
                                    ? part.string.toString().repeat(Math.max(0, part.repeatsCount))
                                    : ""
                    );

                } else {
                    // Come out from repeatable part
                    isRepeatablePart = false;
                    // Pop last part from stack
                    Part part = parts.pollLast();
                    // Append into output string builder this string repeated required number of times
                    result.append(
                            part != null
                                    ? part.string.toString().repeat(Math.max(0, part.repeatsCount))
                                    : ""
                    );
                }
            }
        }

        // Brackets validation
        if (!brackets.isEmpty()) {
            throwException(-1);
        }

        return result.toString();
    }

    /**
     * Method that checks if a character is a digit
     *
     * @param c character to check if it is a digit
     * @return true if character is a digit
     */
    private boolean isDigit(Character c) {
        return c.toString().matches("[0-9]");
    }

    /**
     * Method that checks if a character is an English letter
     *
     * @param c character to check if it is an English letter
     * @return true if character is an English letter
     */
    private boolean isLetter(Character c) {
        return c.toString().matches("[a-zA-Z]");
    }

    /**
     * Method that checks if a character is an open bracket
     *
     * @param c character to check if it is an open bracket
     * @return true if character is an open bracket
     */
    private boolean isOpenBracket(Character c) {
        return c.equals('[');
    }

    /**
     * Method that checks if a character is a close bracket
     *
     * @param c character to check if it is a close bracket
     * @return true if character is a close bracket
     */
    private boolean isCloseBracket(Character c) {
        return c.equals(']');
    }

    /**
     * Method for throwing InvalidStringFormatException
     *
     * @param position position of invalid symbol,
     *                 if position == -1 then we need throw exception for invalid brackets count
     */
    private void throwException(int position) {
        if (position != -1)
            throw new InvalidStringFormatException(position);
        else throw new InvalidStringFormatException();
    }

    /**
     * A nested class whose objects store information about repeating parts of a string
     */
    @Data
    @AllArgsConstructor
    private static class Part {
        /**
         * Count of repeats of the string
         */
        private int repeatsCount;
        /**
         * String builder for repeatable string
         */
        private StringBuilder string;
    }
}
