package ru.tilacyn.analyzer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AnalyzerTest {
    private static Element lp = new Element(Element.Type.LP);
    private static Element rp = new Element(Element.Type.RP);
    private static Element plus = new Element(Element.Type.PLUS);
    private static Element minus = new Element(Element.Type.MINUS);
    private static Element mult = new Element(Element.Type.MULT);
    private static Element div = new Element(Element.Type.DIV);
    private static Element deg = new Element(Element.Type.DEG);
    private static Element one = new Element(1);
    private static Element two = new Element(2);
    private static Element three = new Element(3);


    @Test
    public void plus() throws Exception {
        Analyzer.setElements(Arrays.asList(one, plus, two));
        assertEquals(3, new Analyzer().analyze());

        Analyzer.setElements(Arrays.asList(one, plus, one, plus, one, plus, one));
        assertEquals(4, new Analyzer().analyze());

    }

    @Test
    public void plusWithParencies() throws Exception {
        Analyzer.setElements(Arrays.asList(one, plus, two, plus, lp, two, plus, one, rp));
        assertEquals(6, new Analyzer().analyze());

        Analyzer.setElements(Arrays.asList(lp, lp, lp, one, plus, one, rp, plus, one, rp, plus, one, rp));
        assertEquals(4, new Analyzer().analyze());
    }


    @Test
    public void plusAndMultWithParencies() throws Exception {
        Analyzer.setElements(Arrays.asList(one, plus, two, mult, two));
        assertEquals(5, new Analyzer().analyze());

        Analyzer.setElements(Arrays.asList(lp, one, plus, two, rp, mult, lp, two, plus, one, rp));
        assertEquals(9, new Analyzer().analyze());

        Analyzer.setElements(Arrays.asList(three, mult, lp, lp, lp, one, plus, one, rp, mult, two, rp, mult, one, rp));
        assertEquals(12, new Analyzer().analyze());
    }

    @Test
    public void divAndMinus() throws Exception {
        Analyzer.setElements(Arrays.asList(one, minus, two, div, two));
        assertEquals(0, new Analyzer().analyze());

        Analyzer.setElements(Arrays.asList(lp, new Element(56), div, two, rp, mult,
                lp, new Element(34), minus, new Element(32), rp));
        assertEquals(56, new Analyzer().analyze());
    }

    @Test
    public void deg() throws Exception {
        Analyzer.setElements(Arrays.asList(one, plus, two, deg, two));
        assertEquals(5, new Analyzer().analyze());

        Analyzer.setElements(Arrays.asList(lp, one, plus, two, rp, deg, lp, two, deg, one, rp));
        assertEquals(9, new Analyzer().analyze());

        Analyzer.setElements(Arrays.asList(three, deg, lp, lp, lp, one, plus, one, rp, mult, two, rp, mult, one, rp));
        assertEquals(81, new Analyzer().analyze());

    }

}