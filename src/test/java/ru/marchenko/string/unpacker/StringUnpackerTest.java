package ru.marchenko.string.unpacker;

import org.junit.jupiter.api.Test;
import ru.marchenko.string.unpacker.exception.InvalidStringFormatException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Created by Vladislav Marchenko on 20.03.2021
 */
class StringUnpackerTest {

    /**
     * Valid input string without repeats that need to be unpacked
     */
    private final static String VALID_STRING_WITHOUT_REPEATS = "dhkfhkdvdkfddfkfdkdfkfhdldfdfhkjdh";

    /**
     * Valid input string without nested repeats that need to be unpacked
     */
    private final static String VALID_STRING_WITHOUT_NESTED_REPEATS
            = "5[d]hkfhkd3[vdk]fddfkfdk10[df]kfhdldfdfhkjdh";

    /**
     * Unpacked string from valid string without nested repeats
     */
    private final static String UNPACKED_VALID_STRING_WITHOUT_NESTED_REPEATS
            = "dddddhkfhkdvdkvdkvdkfddfkfdkdfdfdfdfdfdfdfdfdfdfkfhdldfdfhkjdh";

    /**
     * Valid input string with nested repeats that need to be unpacked
     */
    private final static String VALID_STRING_WITH_NESTED_REPEATS = "abcd3[h2[a3[b2[sd]]]a]bcg10[a]";

    /**
     * Unpacked string from valid string with nested repeats
     */
    private final static String UNPACKED_VALID_STRING_WITH_NESTED_REPEATS
            = "abcdhabsdsdbsdsdbsdsdabsdsdbsdsdbsdsdahabsdsdbsdsdbsdsdabsdsdbsdsdbsdsdahabsdsdbsdsdbsdsdabsdsdbsdsdbsdsdabcgaaaaaaaaaa";

    /**
     * Invalid input string with invalid symbol
     */
    private final static String INVALID_STRING_WITH_INVALID_SYMBOL = "abcd3[h2[a3[*2[sd]]]a]bcg10[a]";

    /**
     * Exception message for input string with invalid symbol
     */
    private final static String EXCEPTION_MSG_INVALID_STRING_WITH_INVALID_SYMBOL = "Invalid string format: incorrect symbol at position - 12";

    /**
     * Invalid input string with invalid brackets
     */
    private final static String INVALID_STRING_WITH_INVALID_BRACKETS = "abcd3[h2[a3[2[sd]]]a]bcg10[a";

    /**
     * Exception message for input string with invalid brackets
     */
    private final static String EXCEPTION_MSG_INVALID_STRING_WITH_INVALID_BRACKETS = "Invalid string format: invalid count of brackets";

    /**
     * Invalid input string with invalid repeats count format (situation as "9a9")
     */
    private final static String INVALID_STRING_WITH_INVALID_REPEATS_COUNT = "ab4cd3[h2[a3[2[sd]]]a]bcg10[a]";

    /**
     * Exception message for input string with invalid repeats count
     */
    private final static String EXCEPTION_MSG_INVALID_STRING_WITH_INVALID_REPEATS_COUNT = "Invalid string format: incorrect symbol at position - 3";

    /**
     * String unpacker
     */
    private final static StringUnpacker STRING_UNPACKER = new StringUnpacker();

    @Test
    void testUnpackValidStringWithoutRepeats() {
        assertEquals(VALID_STRING_WITHOUT_REPEATS,
                STRING_UNPACKER.unpack(VALID_STRING_WITHOUT_REPEATS));
    }

    @Test
    void testUnpackValidStringWithoutNestedRepeats() {
        assertEquals(UNPACKED_VALID_STRING_WITHOUT_NESTED_REPEATS,
                STRING_UNPACKER.unpack(VALID_STRING_WITHOUT_NESTED_REPEATS));
    }

    @Test
    void testUnpackValidStringWithNestedRepeats() {
        assertEquals(UNPACKED_VALID_STRING_WITH_NESTED_REPEATS,
                STRING_UNPACKER.unpack(VALID_STRING_WITH_NESTED_REPEATS));
    }

    @Test()
    void testUnpackInvalidStringWithInvalidSymbol() {
        InvalidStringFormatException exception = assertThrows(
                InvalidStringFormatException.class,
                () -> STRING_UNPACKER.unpack(INVALID_STRING_WITH_INVALID_SYMBOL)
        );

        assertEquals(EXCEPTION_MSG_INVALID_STRING_WITH_INVALID_SYMBOL, exception.getMessage());
    }

    @Test()
    void testUnpackInvalidStringWithInvalidBrackets() {
        InvalidStringFormatException exception = assertThrows(
                InvalidStringFormatException.class,
                () -> STRING_UNPACKER.unpack(INVALID_STRING_WITH_INVALID_BRACKETS)
        );

        assertEquals(EXCEPTION_MSG_INVALID_STRING_WITH_INVALID_BRACKETS, exception.getMessage());
    }

    @Test()
    void testUnpackInvalidStringWithInvalidRepeatsCount() {
        InvalidStringFormatException exception = assertThrows(
                InvalidStringFormatException.class,
                () -> STRING_UNPACKER.unpack(INVALID_STRING_WITH_INVALID_REPEATS_COUNT)
        );

        assertEquals(EXCEPTION_MSG_INVALID_STRING_WITH_INVALID_REPEATS_COUNT, exception.getMessage());
    }
}