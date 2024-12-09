package com.example.SentInteligence.Model.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hit {
    private String index;
    private String id;
    @JsonProperty("_source")
    private Map<String, Object> source;
    private List<Long> sort;
}
