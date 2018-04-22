package ru.tilacyn.parser;

public class ParseException extends Exception{
    private String message;
    public ParseException(String message) {
        this.message = message;
    }
    public String get() {
        return message;
    }
}
