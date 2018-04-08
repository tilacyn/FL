package ru.tilacyn.analyzer;

import java.util.List;
import java.util.function.Function;

public class Analyzer {
    private static List<Element> elements;

    private Analyzer a1;
    private Analyzer a2;

    private int start, end;

    public static void setElements(List<Element> src) {
        elements = src;
    }

    public Analyzer(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public Analyzer() {
        this.start = 0;
        this.end = elements.size();
    }

    public int analyze() throws AnalyzeException {
        if (start >= end) {
            throw new AnalyzeException("Analyze failed at point: " + start);
        }

        if (start == end - 1) {
            if (elements.get(start).number == -1) {
                throw new AnalyzeException("Analyze failed at point: " + start);
            }
            System.out.println(elements.get(start).number);

            return elements.get(start).number;
        }

        if (findLeftOperand(Element::isPlus)) {
            System.out.println("PLUS");
            return a1.analyze() + a2.analyze();
        }

        if (findRightOperand(Element::isMinus)) {
            System.out.println("MINUS");
            return a1.analyze() - a2.analyze();
        }

        if (findLeftOperand(Element::isMult)) {
            return a1.analyze() * a2.analyze();
        }

        if (findRightOperand(Element::isDiv)) {
            return a1.analyze() / a2.analyze();
        }

        if (findRightOperand(Element::isDeg)) {
            return deg(a1.analyze(), a2.analyze());
        }

        if (checkForBalance()) {
            return new Analyzer(start + 1, end - 1).analyze();
        }
        throw new AnalyzeException("No operator found between " + start + " " + end);
    }

    private boolean findLeftOperand(Function<Element, Boolean> f) throws AnalyzeException {
        int balance = 0;

        for (int i = start; i < end; i++) {
            Element e = elements.get(i);

            if (balance == 0) {
                if (f.apply(e)) {
                    if (i + 1 >= end) {
                        throw new AnalyzeException("Analyze failed at point: " + (end - 1));
                    }
                    a1 = new Analyzer(start, i);
                    a2 = new Analyzer(i + 1, end);

                    return true;
                }
            }

            if (e.isLP()) {
                balance++;
            }

            if (e.isRP()) {
                balance--;
            }
        }
        return false;
    }

    private boolean findRightOperand(Function<Element, Boolean> f) throws AnalyzeException {
        int balance = 0;

        for (int i = end - 1; i >= start; i--) {
            Element e = elements.get(i);

            if (balance == 0) {
                if (f.apply(e)) {
                    if (i + 1 >= end) {
                        throw new AnalyzeException("Analyze failed at point: " + i);
                    }
                    a1 = new Analyzer(start, i);
                    a2 = new Analyzer(i + 1, end);
                    return true;
                }
            }

            if (e.isLP()) {
                balance++;
            }

            if (e.isRP()) {
                balance--;
            }
        }
        return false;
    }


    private boolean checkForBalance() {
        int balance = 0;

        for (int i = start; i < end; i++) {
            Element e = elements.get(i);
            if (balance == 0) {
                if (i != start) {
                    return false;
                }
            }
            if (e.isLP()) {
                balance++;
            }

            if (e.isRP()) {
                balance--;
            }
        }
        return true;
    }

    private int deg(int a, int b) {
        int res = 1;
        for (int i = 0; i < b; i++) {
            res *= a;
        }
        return res;
    }
}
