package com.example.SentInteligence.Model.Response;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUIResponse {
    private String conversationId;
    private String orgId;
    private double confidenceScore;
    private String rating;
    private String description;
    private Map<String, Object> metadata;
}
