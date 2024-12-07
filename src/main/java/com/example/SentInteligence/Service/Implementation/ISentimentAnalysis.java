package com.example.SentInteligence.Service.Implementation;

import com.example.SentInteligence.Model.Request.RequestWrapper;
import com.example.SentInteligence.Model.Request.TranscriptDetail;
import com.example.SentInteligence.Model.Response.ConversationSentiment;
import com.example.SentInteligence.Model.Response.ResponseWrapper;
import com.example.SentInteligence.Service.SentimentAnalysisService;

public class ISentimentAnalysis implements SentimentAnalysisService {
    @Override
    public ResponseWrapper<ConversationSentiment> analyseSentiment(RequestWrapper<TranscriptDetail> transcriptDetailRequest) {

        return null;
    }
}
