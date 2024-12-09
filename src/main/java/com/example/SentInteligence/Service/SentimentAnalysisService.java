package com.example.SentInteligence.Service;

import com.example.SentInteligence.Exceptions.SentimentLlmException;
import com.example.SentInteligence.Model.Request.RequestWrapper;
import com.example.SentInteligence.Model.Request.TranscriptDetail;
import com.example.SentInteligence.Model.Response.ConversationSentiment;
import com.example.SentInteligence.Model.Response.ResponseWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface SentimentAnalysisService<REQ,RES>{

ResponseWrapper<RES> analyseSentiment(RequestWrapper<REQ> transcriptDetailRequest) throws JsonProcessingException, SentimentLlmException;

}
