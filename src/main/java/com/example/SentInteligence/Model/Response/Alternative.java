package com.example.SentInteligence.Model.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alternative {
    private String transcript;
    private double confidence;
    @Override
    public String toString() {
        return "Alternative{" +
                "transcript='" + transcript + '\'' +
                ", confidence=" + confidence +
                '}';
    }
}
