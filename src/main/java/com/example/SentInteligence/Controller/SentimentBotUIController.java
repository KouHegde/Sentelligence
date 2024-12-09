package com.example.SentInteligence.Controller;

import com.example.SentInteligence.Model.Request.RequestWrapper;
import com.example.SentInteligence.Model.Request.TranscriptDetail;
import com.example.SentInteligence.Model.Response.ConversationSentiment;
import com.example.SentInteligence.Model.Response.ElasticSearchResponse;
import com.example.SentInteligence.Model.Response.ResponseWrapper;
import com.example.SentInteligence.Service.SentimentAnalysisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sentiment/")
public class SentimentBotUIController {
    private final SentimentAnalysisService<ElasticSearchResponse, ConversationSentiment> sentimentAnalysisService;


    public SentimentBotUIController(SentimentAnalysisService<ElasticSearchResponse, ConversationSentiment> sentimentAnalysisService) {
        this.sentimentAnalysisService = sentimentAnalysisService;
    }

    @PostMapping("/conversation/{convId}/update-ui")
    public ResponseEntity<ConversationSentiment> processSentiment(
            @PathVariable("convId") String convId,
            @RequestParam Map<String, String> requestParams,
            @RequestBody TranscriptDetail transcriptDetailRequest) {
        try {
//            RequestWrapper<TranscriptDetail> transcriptDetailRequestWrapper = getElasticDetailRequestWrapper(transcriptDetailRequest,convId, requestParams.get(ORG_ID),requestParams);
            ResponseWrapper<ConversationSentiment> response = sentimentAnalysisService.analyseSentiment(null);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
