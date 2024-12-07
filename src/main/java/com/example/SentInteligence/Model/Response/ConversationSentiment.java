package com.example.SentInteligence.Model.Response;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConversationSentiment {
    private String conversationId;   // Unique identifier for the conversation
    private Double score;              // Sentiment score (e.g., 95.56)
    private String rating;            // Sentiment rating (e.g., "POSITIVE")
    private String description;       // Description or summary of the sentiment analysis
    private Map<String,String> metadata;
}
