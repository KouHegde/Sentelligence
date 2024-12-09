package com.example.SentInteligence.Model.Response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ElasticSearchResponse {
    private int took;
    @JsonProperty("timed_out")
    private boolean timedOut;
    private Shards shards;
    private Hits hits;
}