package ru.tilacyn.parser;

import ru.tilacyn.parser.element.Element;
import ru.tilacyn.parser.element.KeyWord;
import ru.tilacyn.parser.element.Op;
import ru.tilacyn.parser.element.Separator;

import java.io.IOException;
import java.util.ArrayList;


import java.io.Reader;

public class Parser{
    private Reader reader;
    private ArrayList<Element> elements = new ArrayList<>();
    private String input = "";

    private String[] inputArray;
    private int line = 0;
    private int pos = 0;


    public Parser(Reader reader) {
        this.reader = reader;
    }

    public ArrayList<Element> read() throws IOException, ParseException {
        int c;
        while ((c = reader.read()) != -1) {
            input += (char) c;
        }

        inputArray = input.split("\n");

        input = inputArray[0];

        System.out.println(input);

        Element e;

        while ((e = readElement()) != null) {
            elements.add(e);
            System.out.println("FUCKING PRINT!!!!!");

            print();

        }



        return elements;
    }

    private Element readElement() throws IOException, ParseException {

        if (pos >= input.length()) {
            if (line == inputArray.length - 1) {
                return null;
            } else {
                line++;
                input = inputArray[line];
                pos = 0;
            }
        }

        Element e;

        e = readIdent();

        if (e != null) {
            return e;
        }

        e = readNumber();

        if (e != null) {
            return e;
        }

        e = readOp();

        if (e != null) {
            return e;
        }

        e = readKeyWord();

        if (e != null) {
            return e;
        }

        e = readSeparator();

        if (e != null) {
            return e;
        }



        throw new ParseException("Parse failed at line " + line + ", point " + pos);
    }

    public void print() {
        for (Element e : elements) {
            System.out.print(e.get() + "; ");
        }
        System.out.println();
    }

    private Element readIdent() {
        //TODO

        return null;
    }

    private Element readNumber() {
        //TODO

        return null;
    }

    private int getRest() {
        return input.length() - pos;
    }

    private Element readOp() {
        int start = pos;
        if (getRest() >= 2 && input.substring(start, start + 2).equals("+ ")) {
            pos += 2;
            return new Op(line, start, start).set(Op.Type.PLUS);
        }
        else if (getRest() >= 2 && input.substring(start, start + 2).equals("- ")) {
            pos += 2;
            return new Op(line, start, start).set(Op.Type.MINUS);
        }
        else if (getRest() >= 2 && input.substring(start, start + 2).equals("% ")) {
            pos += 2;
            return new Op(line, start, start).set(Op.Type.PERCENT);
        }
        else if (getRest() >= 2 && input.substring(start, start + 2).equals("/ ")) {
            pos += 2;
            return new Op(line, start, start).set(Op.Type.DIV);
        }
        else if (getRest() >= 2 && input.substring(start, start + 2).equals("* ")) {
            pos += 2;
            return new Op(line, start, start).set(Op.Type.MULT);
        }
        else if (getRest() >= 2 && input.substring(start, start + 3).equals("&& ")) {
            pos += 3;
            return new Op(line, start, start + 1).set(Op.Type.AND);
        }
        else if (getRest() >= 3 && input.substring(start, start + 3).equals("|| ")) {
            pos += 3;
            return new Op(line, start, start).set(Op.Type.OR);
        }
        else if (getRest() >= 2 && input.substring(start, start + 2).equals("* ")) {
            pos += 2;
            return new Op(line, start, start).set(Op.Type.MULT);
        }
        else if (getRest() >= 2 && input.substring(start, start + 2).equals("< ")) {
            pos += 2;
            return new Op(line, start, start).set(Op.Type.LT);
        }
        else if (getRest() >= 2 && input.substring(start, start + 2).equals("> ")) {
            pos += 2;
            return new Op(line, start, start).set(Op.Type.GT);
        }
        else if (getRest() >= 3 && input.substring(start, start + 3).equals(">= ")) {
            pos += 3;
            return new Op(line, start, start + 1).set(Op.Type.GEQ);
        }
        else if (getRest() >= 3 && input.substring(start, start + 3).equals("<= ")) {
            pos += 3;
            return new Op(line, start, start + 1).set(Op.Type.LEQ);
        }
        //TODO

        return null;
    }

    private Element readKeyWord() {
        int start = pos;
        if (getRest() >= 3 && input.substring(start, start + 3).equals("if ")) {
            pos += 3;
            return new KeyWord(line, start, start + 1).set(KeyWord.Type.IF);
        }
        else if (getRest() >= 5 && input.substring(start, start + 5).equals("then ")) {
            pos += 5;
            return new KeyWord(line, start, start + 3).set(KeyWord.Type.THEN);
        }
        else if (getRest() >= 5 && input.substring(start, start + 5).equals("else ")) {
            pos += 5;
            return new KeyWord(line, start, start + 3).set(KeyWord.Type.ELSE);
        }
        else if (getRest() >= 5 && input.substring(start, start + 5).equals("read ")) {
            pos += 5;
            return new KeyWord(line, start, start + 3).set(KeyWord.Type.READ);
        }
        else if (getRest() >= 6 && input.substring(start, start + 5).equals("write ")) {
            pos += 6;
            return new KeyWord(line, start, start + 4).set(KeyWord.Type.WRITE);
        }
        else if (getRest() >= 5 && input.substring(start, start + 5).equals("while ")) {
            pos += 6;
            return new KeyWord(line, start, start + 4).set(KeyWord.Type.WHILE);
        }
        else if (getRest() >= 3 && input.substring(start, start + 5).equals("do ")) {
            pos += 3;
            return new KeyWord(line, start, start + 1).set(KeyWord.Type.DO);
        }
        return null;
    }

    private Element readSeparator() {
        int start = pos;
        if (getRest() < 1) {
            return null;
        }

        pos += 2;

        switch (input.substring(start, start + 1)) {
            case "; ":
                return new Separator(line, start, start).set(Separator.Type.COLON);
            case "( ":
                return new Separator(line, start, start).set(Separator.Type.OB);
            case ") ":
                return new Separator(line, start, start).set(Separator.Type.CB);
            case ", ":
                return new Separator(line, start, start).set(Separator.Type.COMA);
        }

        pos -= 2;

        return null;
    }




}
