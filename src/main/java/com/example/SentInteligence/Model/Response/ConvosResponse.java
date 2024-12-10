package com.example.SentInteligence.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConvosResponse {
    private DataObject data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataObject {
        private String orgId;
        private List<String> conversationsIds;
    }
}

