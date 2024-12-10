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
    private String _index;
    private String _id;
    private Object _score;
    private Map<String, Object> _source;
    private List<Long> sort;
    @JsonProperty("content")
    private Content content; //TODO: Set this separately in the future for easy access

}
