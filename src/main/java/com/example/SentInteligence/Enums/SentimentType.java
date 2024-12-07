package com.example.SentInteligence.Enums;

import lombok.Getter;

@Getter
public enum SentimentType {

    NEGATIVE("Negative"),
    POSITIVE("Positive"),
    NEUTRAL("Neutral");

    private final String sentiment;

    SentimentType(String sentiment) {
        this.sentiment = sentiment;
    }

    @Override
    public String toString() {
        return sentiment;
    }

}
