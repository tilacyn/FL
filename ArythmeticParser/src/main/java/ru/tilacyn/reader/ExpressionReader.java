package ru.tilacyn.reader;

import ru.tilacyn.analyzer.Element;

import java.io.IOException;
import java.util.ArrayList;


import java.io.Reader;

public class ExpressionReader {
    private Reader reader;
    private ArrayList<Element> elements = new ArrayList<>();
    private String input = "";
    private int pos = 0;


    public ExpressionReader(Reader reader) {
        this.reader = reader;
    }

    public ArrayList<Element> read() throws IOException, ExpressionParseException {
        System.out.println("Lol");

        int c;
        while ((c = reader.read()) != -1) {
            input += (char) c;
        }

        Element e;

        int pos = 0;

        while ((e = readElement()) != Element.END) {
            elements.add(e);
        }

        return elements;
    }

    private Element readElement() throws IOException, ExpressionParseException {
        if (pos == input.length()) {
            return Element.END;
        }

        char f = input.charAt(pos);
        Element e;


        switch (f) {
            case '(':
                e = new Element(Element.Type.LP);
            case ')':
                e = new Element(Element.Type.RP);
            case '+':
                e = new Element(Element.Type.PLUS);
            case '-':
                e = new Element(Element.Type.MINUS);
            case '*':
                e = new Element(Element.Type.MULT);
            case '/':
                e = new Element(Element.Type.DIV);
            case '^':
                e = new Element(Element.Type.DEG);
            default:
                if (f > '9' || f < '0') {
                    throw new ExpressionParseException();
                }
                if (f == '0') {
                    if (pos != input.length() - 1 && isDigit(input.charAt(pos + 1))) {
                        e = new Element(0);
                    } else {
                        throw new ExpressionParseException();
                    }
                    pos++;
                }

                int n = f - '0';

                while (isDigit(f = input.charAt(pos))) {
                    n = add(n, f - '0');
                    pos++;
                }
                pos--;

                e = new Element(n);
        }

        pos++;

        return e;
    }


    private int add(int n, int c) {
        return n * 10 + c;
    }


    private boolean isDigit(char c) {
        return c <= '9' && c >= '0';
    }

}
