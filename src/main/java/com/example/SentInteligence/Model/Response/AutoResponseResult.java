package com.example.SentInteligence.Model.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutoResponseResult {
    private String autoResponse;
    private String autoResponseQuery;
    private Boolean isValid;
}
