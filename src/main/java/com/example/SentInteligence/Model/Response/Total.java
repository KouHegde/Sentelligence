package com.example.SentInteligence.Model.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Total {
    private int value;
    private String relation;

}