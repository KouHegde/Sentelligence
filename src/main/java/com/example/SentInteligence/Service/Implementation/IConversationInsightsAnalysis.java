package com.example.SentInteligence.Service.Implementation;

import com.example.SentInteligence.Exceptions.SentimentLlmException;
import com.example.SentInteligence.HttpService.Implementation.IHttpWrapper;
import com.example.SentInteligence.Model.Request.RequestWrapper;
import com.example.SentInteligence.Model.Request.SentimentRequest;
import com.example.SentInteligence.Model.Request.TranscriptContent;
import com.example.SentInteligence.Model.Response.ConversationSentiment;
import com.example.SentInteligence.Model.Response.ElasticSearchResponse;
import com.example.SentInteligence.Model.Response.ResponseWrapper;
import com.example.SentInteligence.Model.Response.SentimentAnalysisResult;
import com.example.SentInteligence.Service.SentimentAnalysisService;
import com.example.SentInteligence.Utils.ApplicationPropertiesUtils;
import com.example.SentInteligence.Utils.JsonUtils;
import com.example.SentInteligence.Utils.SentimentAnalysisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;

import static com.example.SentInteligence.CommonConstants.CommonConstants.LLM_URL;
import static com.example.SentInteligence.CommonConstants.CommonConstants.UNKNOWN;

@Component
public class IConversationInsightsAnalysis  implements SentimentAnalysisService<ElasticSearchResponse,ConversationSentiment> {
    private final ApplicationPropertiesUtils applicationPropertiesUtils;
    private final IHttpWrapper httpWrapper;

    public IConversationInsightsAnalysis(ApplicationPropertiesUtils applicationPropertiesUtils, IHttpWrapper iHttpWrapper) {
        this.applicationPropertiesUtils = applicationPropertiesUtils;
        this.httpWrapper = iHttpWrapper;
    }

    @Override
    public ResponseWrapper<ConversationSentiment> analyseSentiment(RequestWrapper<ElasticSearchResponse> elasticSearchResponseRequest) throws JsonProcessingException, SentimentLlmException {

        List<TranscriptContent> transcriptContents = SentimentAnalysisUtils.getContentFromHits(elasticSearchResponseRequest.getBody());
        String finalTranscript = SentimentAnalysisUtils.getMergedContentByOffset(transcriptContents,elasticSearchResponseRequest.getOffset(),elasticSearchResponseRequest.getLimit());
        HttpResponse<String> llmResponse = getSentimentAnalysisFromLLM(finalTranscript,elasticSearchResponseRequest.getConvId());
        return getConversationSentiment(llmResponse,elasticSearchResponseRequest);
    }


    private ResponseWrapper<ConversationSentiment> getConversationSentiment(HttpResponse<String> llmResponse , RequestWrapper<ElasticSearchResponse> transcriptDetailRequest) throws JsonProcessingException {
        if (Objects.nonNull(llmResponse) && llmResponse.statusCode() == 200 ){
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

    private ConversationSentiment createConversationSentiment(SentimentAnalysisResult sentimentAnalysisResult, RequestWrapper<ElasticSearchResponse> transcriptDetailRequest) {
        return ConversationSentiment.builder()
                .conversationId(transcriptDetailRequest.getConvId())
                .score(Objects.nonNull(sentimentAnalysisResult) ? sentimentAnalysisResult.getConfidence() : null)
                .rating(Objects.nonNull(sentimentAnalysisResult) ? sentimentAnalysisResult.getSentiment() : UNKNOWN)
                .build();
    }

    private HttpResponse<String> getSentimentAnalysisFromLLM(String finalTranscript, String convId) throws JsonProcessingException,SentimentLlmException {
        SentimentRequest sentimentRequest = new SentimentRequest(finalTranscript);
        String requestBody =JsonUtils.toJson(sentimentRequest);
        String url = applicationPropertiesUtils.getPropertyValue(LLM_URL);
        try {
            return httpWrapper.post(url, requestBody);
        } catch (Exception e){
            throw  new SentimentLlmException("Failed to get response from the LLM for the conversationID" + convId);
        }
    }
}
