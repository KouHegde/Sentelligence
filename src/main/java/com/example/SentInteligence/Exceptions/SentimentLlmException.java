package com.example.SentInteligence.Exceptions;

public class SentimentLlmException extends Exception {

    // Default constructor
    public SentimentLlmException() {
        super();
    }

    // Constructor with a custom message
    public SentimentLlmException(String message) {
        super(message);
    }

    // Constructor with a cause (another throwable that caused this exception)
    public SentimentLlmException(Throwable cause) {
        super(cause);
    }

    // Constructor with both a custom message and a cause
    public SentimentLlmException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with additional control over stack trace and suppression
    public SentimentLlmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}