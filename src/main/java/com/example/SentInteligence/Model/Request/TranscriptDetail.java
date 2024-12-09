package com.example.SentInteligence.Model.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Builder
@Setter
@Getter
public class TranscriptDetail {
    private String transcript;         // The conversation or transcript text
    private String language;           // The language of the transcript (e.g., "en")
    private String timestamp;         // Timestamp when the transcript was created or processed
    private Map<String, String> metadata;
}
