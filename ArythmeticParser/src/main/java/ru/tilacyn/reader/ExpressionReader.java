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
        int c;
        while ((c = reader.read()) != -1) {
            input += (char) c;
        }

        //System.out.println(input);

        Element e;

        while ((e = readElement()) != Element.END) {
            elements.add(e);
        }

        elements.removeIf(element -> element.isSpace() || element.isSlashN());

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
                break;
            case ')':
                e = new Element(Element.Type.RP);
                break;
            case '+':
                e = new Element(Element.Type.PLUS);
                break;
            case '-':
                e = new Element(Element.Type.MINUS);
                break;
            case '*':
                e = new Element(Element.Type.MULT);
                break;
            case '/':
                e = new Element(Element.Type.DIV);
                break;
            case '^':
                e = new Element(Element.Type.DEG);
                break;
            case '\n':
                e = new Element(Element.Type.SLASHN);
                break;
            case ' ':
                e = new Element(Element.Type.SPACE);
                break;
            default:
                if (f > '9' || f < '0') {
                    throw new ExpressionParseException("Unknown symbol");
                }
                if (f == '0') {
                    if (pos != input.length() - 1 && isDigit(input.charAt(pos + 1))) {
                        throw new ExpressionParseException("0 is a first digit of a number");
                    } else {
                        e = new Element(0);
                    }
                    break;
                }

                pos++;

                int n = f - '0';

                while (pos < input.length() && isDigit(f = input.charAt(pos))) {
                    n = add(n, f - '0');
                    pos++;
                }
                pos--;

                e = new Element(n);
                break;
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
