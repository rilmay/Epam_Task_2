package com.epam.shapes.exception;

public class RepositoryException extends IllegalArgumentException {
    public RepositoryException() {
        super();
    }

    public RepositoryException(String s) {
        super(s);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryException(Throwable cause) {
        super(cause);
    }
}
