package ru.tilacyn.main;

import ru.tilacyn.analyzer.AnalyzeException;
import ru.tilacyn.analyzer.Analyzer;
import ru.tilacyn.analyzer.Element;
import ru.tilacyn.reader.ExpressionParseException;
import ru.tilacyn.reader.ExpressionReader;

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
            ExpressionReader reader = new ExpressionReader(new FileReader(inFile));

            ArrayList<Element> elements = reader.read();

            Analyzer.setElements(elements);

            result = new Analyzer().analyze();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        } catch (IOException e) {
            System.out.println("Problems with file reading occurred");
            return;
        } catch (ExpressionParseException e) {
            System.out.println("Expression parsing failed");
            return;
        } catch (AnalyzeException e) {
            System.out.println(e.get());
            return;
        }

        System.out.println(result);
    }
}
