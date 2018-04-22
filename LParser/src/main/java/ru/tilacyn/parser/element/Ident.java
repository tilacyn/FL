package ru.tilacyn.parser.element;

public class Ident extends Element {

    public Ident(int line, int start, int end) {
        super(line, start, end);
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }



    @Override
    public String getName() {
        return "Ident";
    }

    @Override
    public String getAdditionalInfo() {
        return additionalInfo;
    }
}
