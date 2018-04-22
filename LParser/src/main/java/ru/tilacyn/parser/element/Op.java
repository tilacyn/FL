package ru.tilacyn.parser.element;

public class Op extends Element {
    private Type t;

    public Op(int line, int start, int end) {
        super(line, start, end);
    }

    public Op set(Type t) {
        this.t = t;
        return this;
    }


    @Override
    public String getName() {
        return "Op";
    }

    @Override
    public String getAdditionalInfo() {
        switch (t) {
            case PLUS:
                additionalInfo = "Plus";
                break;
            case MINUS:
                additionalInfo = "Minus";
                break;
            case MULT:
                additionalInfo = "Multiply";
                break;
            case DIV:
                additionalInfo = "Divide";
                break;
            case EQ:
                additionalInfo = "Equals";
                break;
            case NEQ:
                additionalInfo = "Not equals";
                break;
            case GT:
                additionalInfo = "Greater";
                break;
            case LT:
                additionalInfo = "Less";
                break;
            case LEQ:
                additionalInfo = "Less/equals";
                break;
            case GEQ:
                additionalInfo = "Greater/equals";
                break;
        }
        return additionalInfo;
    }

    public enum Type {
        PLUS, MINUS, MULT, DIV, PERCENT, EQ, NEQ, GT, LT, GEQ, LEQ, AND, OR
    }


}
