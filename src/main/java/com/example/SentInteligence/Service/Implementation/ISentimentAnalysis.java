package com.example.SentInteligence.Service.Implementation;

import com.example.SentInteligence.HttpService.Implementation.IHttpWrapper;
import com.example.SentInteligence.Model.Request.RequestWrapper;
import com.example.SentInteligence.Model.Request.SentimentRequest;
import com.example.SentInteligence.Model.Request.TranscriptDetail;
import com.example.SentInteligence.Model.Response.ConversationSentiment;
import com.example.SentInteligence.Model.Response.ResponseWrapper;
import com.example.SentInteligence.Model.Response.SentimentAnalysisResult;
import com.example.SentInteligence.Service.SentimentAnalysisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class ISentimentAnalysis implements SentimentAnalysisService {


    private final IHttpWrapper httpWrapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public ISentimentAnalysis(IHttpWrapper httpWrapper, ObjectMapper objectMapper) {
        this.httpWrapper = httpWrapper;
        this.objectMapper = objectMapper;
    }
    @Override
    public ResponseWrapper<ConversationSentiment> analyseSentiment(RequestWrapper<TranscriptDetail> transcriptDetailRequest) throws JsonProcessingException {
        SentimentAnalysisResult sentimentAnalysisResult = getSentimentAnalysisFromLLM(transcriptDetailRequest.getBody());
        return getConversationSentiment(sentimentAnalysisResult ,transcriptDetailRequest);
    }

    private ResponseWrapper<ConversationSentiment> getConversationSentiment(SentimentAnalysisResult sentimentAnalysisResult, RequestWrapper<TranscriptDetail> transcriptDetailRequest) {
        return ResponseWrapper.<ConversationSentiment>builder()
                .body(createConversationSentiment(sentimentAnalysisResult,transcriptDetailRequest))
                .statusCode(200) //TODO: Do not hardcode it ,handle in a better way
                .build();

    }

    private ConversationSentiment createConversationSentiment(SentimentAnalysisResult sentimentAnalysisResult, RequestWrapper<TranscriptDetail> transcriptDetailRequest) {
        return ConversationSentiment.builder()
                .conversationId(transcriptDetailRequest.getConvId())
                .score(sentimentAnalysisResult.getConfidence())
                .rating(sentimentAnalysisResult.getSentiment().toString())
                .build();
    }

    //TODO:Refactor this method and handle exception in a better way
    private SentimentAnalysisResult getSentimentAnalysisFromLLM(TranscriptDetail transcriptDetail) throws JsonProcessingException {
        SentimentRequest sentimentRequest = new SentimentRequest(transcriptDetail.getTranscript());
        String requestBody = objectMapper.writeValueAsString(sentimentRequest);
        String url = "http://127.0.0.1:8000/analyze"; //TODO: Do not hardcode create a bean to pick up all the property values from application.properties
        String responseBody = httpWrapper.post(url, requestBody).body();

        return objectMapper.readValue(responseBody, SentimentAnalysisResult.class);
    }
}
