package ru.tilacyn.reader;

public class ExpressionParseException extends Exception {
    private String message = null;
    public ExpressionParseException(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }
}
