package com.example.SentInteligence.Service;

import com.example.SentInteligence.Model.Request.RequestWrapper;
import com.example.SentInteligence.Model.Request.TranscriptDetail;
import com.example.SentInteligence.Model.Response.ConversationSentiment;
import com.example.SentInteligence.Model.Response.ResponseWrapper;

public interface SentimentAnalysisService{
ResponseWrapper<ConversationSentiment> analyseSentiment(RequestWrapper<TranscriptDetail> transcriptDetailRequest);
}
