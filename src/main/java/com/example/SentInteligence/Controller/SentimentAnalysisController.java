package com.example.SentInteligence.Controller;

import com.example.SentInteligence.Model.Request.RequestWrapper;
import com.example.SentInteligence.Model.Request.TranscriptDetail;
import com.example.SentInteligence.Model.Response.ConversationSentiment;
import com.example.SentInteligence.Model.Response.ResponseWrapper;
import com.example.SentInteligence.Service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sentiment/")
public class SentimentAnalysisController {
    private static final  String ORG_ID = "orgId";

    private final SentimentAnalysisService<TranscriptDetail,ConversationSentiment> sentimentAnalysisService;

    @Autowired
    public SentimentAnalysisController(SentimentAnalysisService<TranscriptDetail,ConversationSentiment> sentimentAnalysisService) {
        this.sentimentAnalysisService = sentimentAnalysisService;
    }

    @PostMapping("/process/{convId}")
    public ResponseEntity<ConversationSentiment> processSentiment(
            @PathVariable("convId") String convId,
            @RequestParam Map<String, String> requestParams,
            @RequestBody TranscriptDetail transcriptDetailRequest) {
        try {
            RequestWrapper<TranscriptDetail> transcriptDetailRequestWrapper = gettranscriptDetailRequestWrapper(transcriptDetailRequest,convId, requestParams.get(ORG_ID),requestParams);
            ResponseWrapper<ConversationSentiment> response = sentimentAnalysisService.analyseSentiment(transcriptDetailRequestWrapper);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private RequestWrapper<TranscriptDetail> gettranscriptDetailRequestWrapper(TranscriptDetail transcriptDetailRequest, String convId, String orgId, Map<String, String> requestParams) {
        return new RequestWrapper<>(convId,orgId,transcriptDetailRequest,requestParams);
    }

}



