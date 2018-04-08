package ru.tilacyn.analyzer;

public class AnalyzeException extends Exception{
    private String message;
    AnalyzeException(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }
}
