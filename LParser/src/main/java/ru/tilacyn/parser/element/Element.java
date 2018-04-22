package ru.tilacyn.parser.element;

public abstract class Element {
    private int start;
    private int end;
    private int line;
    protected String additionalInfo = "";



    public Element(int line, int start, int end) {
        this.line = line;
        this.start = start;
        this.end = end;
    }

    public abstract String getName();

    public abstract String getAdditionalInfo();

    public String get() {
        String additionalInfo = "";
        if (getAdditionalInfo() != null) {
            additionalInfo = getAdditionalInfo() + ", ";
        }
        return getName() + "(" + additionalInfo + line + ", " + start + ", " + end + ")";
    }

}
