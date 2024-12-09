package com.example.SentInteligence.Service.Implementation;

import com.example.SentInteligence.Exceptions.SentimentLlmException;
import com.example.SentInteligence.Model.Request.RequestWrapper;
import com.example.SentInteligence.Model.Response.ConversationSentiment;
import com.example.SentInteligence.Model.Response.ElasticSearchResponse;
import com.example.SentInteligence.Model.Response.ResponseWrapper;
import com.example.SentInteligence.Service.SentimentAnalysisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

@Component
public class IConversationInsightsAnalysis  implements SentimentAnalysisService<ElasticSearchResponse,ConversationSentiment> {
    @Override
    public ResponseWrapper<ConversationSentiment> analyseSentiment(RequestWrapper<ElasticSearchResponse> elasticSearchResponseRequest) throws JsonProcessingException, SentimentLlmException {
        return null;
    }
}
