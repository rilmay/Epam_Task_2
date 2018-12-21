package com.epam.shapes.exception;

public class ParsedFileException extends Exception {
    public ParsedFileException() {
    }

    public ParsedFileException(String message) {
        super(message);
    }

    public ParsedFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParsedFileException(Throwable cause) {
        super(cause);
    }
}
