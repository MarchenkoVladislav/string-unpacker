package ru.marchenko.string.unpacker;

import java.util.Scanner;

/**
 * @author Created by Vladislav Marchenko on 19.03.2021
 */
public class Main {

    public static void main(String[] args) {
        StringUnpacker unpacker = new StringUnpacker();

        String str;

        if (args[0] != null) {
            str = args[0];
        } else {
            System.out.println("Please, enter a string, that need to be unpacked:");
            Scanner scanner = new Scanner(System.in);
            str = scanner.nextLine();
        }

        System.out.println(unpacker.unpack(str));
    }
}
