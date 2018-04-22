package ru.tilacyn.parser.element;

public class Separator extends Element{

    private Type t;

    public Separator(int line, int start, int end) {
        super(line, start, end);
    }

    public Separator set(Type t) {
        this.t = t;
        return this;
    }

    @Override
    public String getName() {
        String name = "";

        switch (t) {
            case CB:
                name = "CloseBrace";
                break;
            case OB:
                name = "OpenBrace";
                break;
            case COLON:
                name = "Colon";
                break;
            case COMA:
                name = "Coma";
                break;
        }

        return name;
    }

    @Override
    public String getAdditionalInfo() {
        return null;
    }

    public enum Type {
        OB, CB, COLON, COMA
    }
}
