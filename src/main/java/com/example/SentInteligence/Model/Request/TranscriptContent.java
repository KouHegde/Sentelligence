package com.example.SentInteligence.Model.Request;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@Builder
public class TranscriptContent {
    private String content;  // Content value from _source
    private int order;       // Sequential order
    private String role;     // Role value (e.g., IVR, Human)

    // Constructor
    public TranscriptContent(String content, int order, String role) {
        this.content = content;
        this.order = order;
        this.role = role;
    }

    @Override
    public String toString() {
        return "TranscriptContent{" +
                "content='" + content + '\'' +
                ", order=" + order +
                ", role='" + role + '\'' +
                '}';
    }
}
