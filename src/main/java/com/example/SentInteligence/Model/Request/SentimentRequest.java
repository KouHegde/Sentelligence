package com.example.SentInteligence.Model.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SentimentRequest {

    // Getter and Setter for text
    @JsonProperty("text")
    private String text; // The transcript text

    @Override
    public String toString() {
        return "SentimentRequest{" +
                "text='" + text + '\'' +
                '}';
    }
}
