package ru.marchenko.string.unpacker;

/**
 * @author Created by Vladislav Marchenko on 19.03.2021
 */
public class StringUnpacker {

    public String unpack(String str) {
        if (!isValid(str)) {
            throw new IllegalArgumentException("Invalid string format");
        }

        return null;
    }

    private boolean isValid(String str) {
        return true;
    }
}
