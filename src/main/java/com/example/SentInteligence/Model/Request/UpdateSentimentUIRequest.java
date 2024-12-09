package com.example.SentInteligence.Model.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateSentimentUIRequest {
    @JsonProperty("transcriptOffset")
    private int transcriptOffset;
    private int transcriptLimit;

    @Override
    public String toString() {
        return "SentimentUIUpdateRequest{" +
                "transcriptOffset=" + transcriptOffset +
                '}';
    }
}
