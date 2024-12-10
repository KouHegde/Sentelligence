package com.example.SentInteligence.Controller;

import com.example.SentInteligence.Model.Request.RequestWrapper;
import com.example.SentInteligence.Model.Request.TranscriptDetail;
import com.example.SentInteligence.Model.Response.ConversationSentiment;
import com.example.SentInteligence.Model.Response.ElasticSearchResponse;
import com.example.SentInteligence.Model.Response.ConvosResponse;
import com.example.SentInteligence.Model.Response.ResponseWrapper;
import com.example.SentInteligence.Utils.ApplicationPropertiesUtils;
import com.example.SentInteligence.Service.SentimentAnalysisService;
import com.example.SentInteligence.Utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sentiment/")
public class SentimentBotUIController {

    private final SentimentAnalysisService<ElasticSearchResponse, ConversationSentiment> sentimentAnalysisService;
    private final ApplicationPropertiesUtils applicationPropertiesUtils;

    public SentimentBotUIController(SentimentAnalysisService<ElasticSearchResponse, ConversationSentiment> sentimentAnalysisService, ApplicationPropertiesUtils applicationPropertiesUtils) {
        this.sentimentAnalysisService = sentimentAnalysisService;
        this.applicationPropertiesUtils = applicationPropertiesUtils;
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

    @GetMapping("/convos")
    public ConvosResponse getOrgConvIds() throws JsonProcessingException {
        String orgConvIdJson = applicationPropertiesUtils.getPropertyValue("org_convId");
        System.out.println(orgConvIdJson);
        return applicationPropertiesUtils.getOrgConvIds(orgConvIdJson);

    }
}


