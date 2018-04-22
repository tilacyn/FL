package ru.tilacyn.parser;

import ru.tilacyn.parser.element.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class Main {
    public static void main(String[] args) {

        if (args == null || args.length != 1) {
            System.out.println("Bad input, try again!");
            return;
        }
        String path = args[0];

        File inFile = new File(path);

        if (!inFile.exists()) {
            System.out.println("Input file doesn't exist");
        }

        int result;

        try {
            Parser parser = new Parser(new FileReader(inFile));

            ArrayList<Element> elements = parser.read();

            parser.print();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        } catch (IOException e) {
            System.out.println("Problems with file reading occurred");
            return;
        } catch (ParseException e) {
            System.out.println("Expression parsing failed");
            if (e.get() != null) {
                System.out.println(e.get());
            }
            return;
        }

        System.out.println();

    }
}
