package ru.tilacyn.analyzer;

public class Element {
    public static final Element END = new Element(Type.END);

    public Element(int n) {
        number = n;
        t = Type.NUMBER;
    }
    public Element(Type t) {
        this.t = t;
    }

    public enum Type{
        NUMBER, LP, RP, PLUS, MULT, MINUS, DIV, DEG, END
    }

    public boolean isNumber() {
        return t == Type.NUMBER;
    }

    public boolean isPlus() {
        return t == Type.PLUS;
    }

    public boolean isLP() {
        return t == Type.LP;
    }

    public boolean isRP() {
        return t == Type.RP;
    }

    public boolean isMinus() {
        return t == Type.MINUS;
    }

    public boolean isMult() {
        return t == Type.MULT;
    }

    public boolean isDiv() {
        return t == Type.DIV;
    }

    public boolean isDeg() {
        return t == Type.DEG;
    }


    public Type t;
    public int number = -1;
}
