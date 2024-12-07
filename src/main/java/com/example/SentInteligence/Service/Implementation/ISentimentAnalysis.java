package com.example.SentInteligence.Service.Implementation;

import com.example.SentInteligence.Exceptions.SentimentLlmException;
import com.example.SentInteligence.HttpService.Implementation.IHttpWrapper;
import com.example.SentInteligence.Model.Request.RequestWrapper;
import com.example.SentInteligence.Model.Request.SentimentRequest;
import com.example.SentInteligence.Model.Request.TranscriptDetail;
import com.example.SentInteligence.Model.Response.ConversationSentiment;
import com.example.SentInteligence.Model.Response.ResponseWrapper;
import com.example.SentInteligence.Model.Response.SentimentAnalysisResult;
import com.example.SentInteligence.Service.SentimentAnalysisService;
import com.example.SentInteligence.Utils.ApplicationPropertiesUtils;
import com.example.SentInteligence.Utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.net.http.HttpResponse;
import java.util.Objects;

import static com.example.SentInteligence.CommonConstants.CommonConstants.LLM_URL;
import static com.example.SentInteligence.CommonConstants.CommonConstants.UNKNOWN;

@Primary
@Component
@Getter
public class ISentimentAnalysis implements SentimentAnalysisService {

    private final IHttpWrapper httpWrapper;
    private final ApplicationPropertiesUtils applicationPropertiesUtils;

    @Autowired
    public ISentimentAnalysis(IHttpWrapper httpWrapper, ApplicationPropertiesUtils applicationPropertiesUtils) {
        this.httpWrapper = httpWrapper;
        this.applicationPropertiesUtils = applicationPropertiesUtils;
    }
    @Override
    public ResponseWrapper<ConversationSentiment> analyseSentiment(RequestWrapper<TranscriptDetail> transcriptDetailRequest) throws JsonProcessingException,SentimentLlmException {
        HttpResponse<String> llmResponse = getSentimentAnalysisFromLLM(transcriptDetailRequest.getBody(), transcriptDetailRequest.getConvId());
        return getConversationSentiment(llmResponse ,transcriptDetailRequest);
    }

    private ResponseWrapper<ConversationSentiment> getConversationSentiment(HttpResponse<String> llmResponse , RequestWrapper<TranscriptDetail> transcriptDetailRequest) throws JsonProcessingException {
        if (llmResponse.statusCode() == 200){
            SentimentAnalysisResult sentimentAnalysisResult = JsonUtils.fromJson(llmResponse.body(),SentimentAnalysisResult.class);
            return ResponseWrapper.<ConversationSentiment>builder()
                    .body(createConversationSentiment(sentimentAnalysisResult,transcriptDetailRequest))
                    .statusCode(llmResponse.statusCode())
                    .build();
        }
        return ResponseWrapper.<ConversationSentiment>builder()
                .body(createConversationSentiment(null,transcriptDetailRequest))
                .statusCode(llmResponse.statusCode())
                .statusMessage("Failed to get the response from the LLM")
                .build();

    }

    private ConversationSentiment createConversationSentiment(SentimentAnalysisResult sentimentAnalysisResult, RequestWrapper<TranscriptDetail> transcriptDetailRequest) {
        ConversationSentiment.ConversationSentimentBuilder builder = ConversationSentiment.builder();
        builder.conversationId(transcriptDetailRequest.getConvId());
        builder.score(Objects.nonNull(sentimentAnalysisResult) ? sentimentAnalysisResult.getConfidence() : null);
        builder.rating(Objects.nonNull(sentimentAnalysisResult) ?sentimentAnalysisResult.getSentiment() : UNKNOWN);
        return builder.build();
    }

    private HttpResponse<String> getSentimentAnalysisFromLLM(TranscriptDetail transcriptDetail, String convId) throws JsonProcessingException,SentimentLlmException {
        SentimentRequest sentimentRequest = new SentimentRequest(transcriptDetail.getTranscript());
        String requestBody =JsonUtils.toJson(sentimentRequest);
        String url = applicationPropertiesUtils.getPropertyValue(LLM_URL);
        try {
            return httpWrapper.post(url, requestBody);
        } catch (Exception e){
            throw  new SentimentLlmException("Failed to get response from the LLM for the conversationID" + convId);
        }
    }


}
