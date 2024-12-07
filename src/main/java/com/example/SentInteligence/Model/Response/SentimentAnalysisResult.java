package com.example.SentInteligence.Model.Response;

import com.example.SentInteligence.Enums.SentimentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SentimentAnalysisResult {
    @JsonProperty("sentiment")
    private String sentiment;        // Sentiment of the analysis (e.g., Negative/Positive/Neutral)
    private double confidence;
}
