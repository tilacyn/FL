package ru.tilacyn.parser.element;

public class KeyWord extends Element {
    private Type t;

    public KeyWord(int line, int start, int end) {
        super(line, start, end);
    }

    public KeyWord set(Type t) {
        this.t = t;
        return this;
    }

    @Override
    public String getName() {
        String name = "KW_";

        switch (t) {
            case IF:
                name += "If";
                break;
            case THEN:
                name += "Then";
                break;
            case ELSE:
                name += "Else";
                break;
            case READ:
                name += "Read";
                break;
            case WRITE:
                name += "Write";
                break;
            case WHILE:
                name += "While";
                break;
            case DO:
                name += "Do";
                break;
        }

        return name;
    }

    @Override
    public String getAdditionalInfo() {
        return null;
    }

    public enum Type {
        IF, THEN, ELSE, READ, WRITE, WHILE, DO
    }
}
