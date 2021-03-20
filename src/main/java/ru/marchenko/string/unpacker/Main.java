package ru.marchenko.string.unpacker;

import java.util.Scanner;

/**
 * Main class
 *
 * @author Created by Vladislav Marchenko on 19.03.2021
 */
public class Main {

    public static void main(String[] args) {
        StringUnpacker unpacker = new StringUnpacker();

        String str;

        // You can pass the input string through arguments,
        // or you can type in the console
        if (args.length > 0 && args[0] != null) {
            str = args[0];
        } else {
            System.out.println("Please, enter a string, that need to be unpacked:");
            Scanner scanner = new Scanner(System.in);
            str = scanner.nextLine();
        }

        System.out.println(unpacker.unpack(str));
    }
}
