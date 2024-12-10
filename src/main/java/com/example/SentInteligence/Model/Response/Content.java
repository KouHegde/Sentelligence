package com.example.SentInteligence.Model.Response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Content {
    private List<String> replyText;
    private List<Alternative> alternatives;

    @Override
    public String toString() {
        return "Content{" +
                "replyText=" + replyText +
                ", alternatives=" + alternatives +
                '}';
    }
}
