package org.example.exceptions;

public class GitExecutionException extends RuntimeException {
    public GitExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
