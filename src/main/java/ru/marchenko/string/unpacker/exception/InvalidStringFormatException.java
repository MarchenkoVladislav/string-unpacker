package ru.marchenko.string.unpacker.exception;

/**
 * Exception class for throwing exception when
 * an incorrect character or incorrect character arrangement is encountered in a string
 *
 * @author Created by Vladislav Marchenko on 20.03.2021
 */
public class InvalidStringFormatException extends RuntimeException {
    public InvalidStringFormatException(int position) {
        super("Invalid string format: incorrect symbol at position - " + position);
    }

    public InvalidStringFormatException() {
        super("Invalid string format: invalid count of brackets");
    }
}
