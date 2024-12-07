package com.example.SentInteligence.Controller;

import com.example.SentInteligence.Model.Request.RequestWrapper;
import com.example.SentInteligence.Model.Request.TranscriptDetail;
import com.example.SentInteligence.Model.Response.ConversationSentiment;
import com.example.SentInteligence.Model.Response.ResponseWrapper;
import com.example.SentInteligence.Service.SentimentAnalysisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sentiment/{convId}")
public class SentimentAnalysisController {

    private final SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    public SentimentAnalysisController(SentimentAnalysisService sentimentAnalysisService) {
        this.sentimentAnalysisService = sentimentAnalysisService;
    }

    @PostMapping("/process")
    public ResponseEntity<ResponseWrapper<ConversationSentiment>> processSentiment(
            @RequestBody RequestWrapper<TranscriptDetail> transcriptDetailRequest) {
        try {
            ResponseWrapper<ConversationSentiment> response = sentimentAnalysisService.analyseSentiment(transcriptDetailRequest);
            return ResponseEntity.ok(response);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500)
                    .body(ResponseWrapper.<ConversationSentiment>builder()
                            .statusCode(500)
                            .statusMessage("Error processing sentiment analysis: " + e.getMessage())
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(ResponseWrapper.<ConversationSentiment>builder()
                            .statusCode(500)
                            .statusMessage("Unexpected error occurred: " + e.getMessage())
                            .build());
        }
    }
}



