package com.example.SentInteligence.Utils;

import com.cisco.wcc.ccai.v1.InsightServingResponse;
import com.cisco.wcc.ccai.v1.Suggestions;
import com.google.common.base.Objects;
import org.slf4j.Logger;

import java.rmi.ServerException;
import java.util.List;

public class ServingApiClientUtils {
    private static final String LATENCY_CONSTANT = "Latency : ";
    private static final String EXPECTED_TRANSCRIPT = "lost my credit card while I was traveling yesterday";

    //TODO: Rename all the methods better,make it more meaningful and do not throw exception from a util at the end remove all the test utils created for testing

    public static void processAgentAnswers(InsightServingResponse insightServingResponse, Logger logger) throws ServerException {
        List<Suggestions.Answer> answers = insightServingResponse.getResponseContent().getAgentAnswerResult().getAnswersList();
        for (Suggestions.Answer answer : answers) {
            String description = answer.getDescription();
            assertFalse(description.isEmpty(), "Agent answer is empty");

            if (!description.isEmpty()) {
                logAnswerLatency(insightServingResponse, answer, logger);
            }
        }
    }

    public static void validateTranscription(InsightServingResponse insightServingResponse,Logger logger) throws ServerException {
        for (int i = 0; i < insightServingResponse.getResponseContent().getRecognitionResult().getAlternativesCount(); i++) {
            logger.info("{}:{} msecs , transcript : {} ", LATENCY_CONSTANT, (System.currentTimeMillis() - insightServingResponse.getPublishTimestamp()), insightServingResponse.getResponseContent().getRecognitionResult().getAlternatives(i).getTranscript());
            if(insightServingResponse.getIsFinal() && insightServingResponse.getInsightProvider().name().equalsIgnoreCase("cisco")) {
                String expected = "lost my credit card while I was traveling yesterday";
                String actual = insightServingResponse.getResponseContent().getRecognitionResult().getAlternatives(i).getTranscript();
                logger.info("Transcript for CISCO:: Expected: {}, Actual: {}",expected, actual);
                assertTrue(insightServingResponse.getResponseContent().getRecognitionResult().getAlternatives(i).getTranscript().contains(expected), "Transcript does not match for CISCO");
            }
            else if(insightServingResponse.getIsFinal() && insightServingResponse.getInsightProvider().name().equalsIgnoreCase("google")) {
                String expected = "lost my credit card while I was traveling yesterday";
                String actual = insightServingResponse.getResponseContent().getRecognitionResult().getAlternatives(i).getTranscript();
                logger.info("Transcript for GOOGLE:: Expected: {}, Actual: {}",expected, actual);
                assertTrue(actual.contains(expected), "Transcript does not match for GOOGLE");
            }
        }
    }

    private static void logAnswerLatency(InsightServingResponse insightServingResponse, Suggestions.Answer answer, Logger logger) {
        long elapsedTime = System.currentTimeMillis() - insightServingResponse.getPublishTimestamp();
        logger.info("{}:{} msecs, Answer_{} : {}",
                LATENCY_CONSTANT, elapsedTime, insightServingResponse.getConversationId(), answer);
    }

    public static void assertEquals(Object expected, Object actual, String errMsg) throws ServerException {
        if(!Objects.equal(expected, actual)) {
            throw new ServerException(errMsg);  //TODO: Add a separate new exception for handling and managing
        }
    }

    public static void assertFalse(boolean expected, String errMsg) throws ServerException {
        if(Boolean.TRUE.equals(expected)) {
            throw new ServerException(errMsg);  //TODO: Add a separate new exception for handling and managing
        }
    }

    public static void assertTrue(boolean expected, String errMsg) throws ServerException {
        if(Boolean.FALSE.equals(expected)) {
            throw new ServerException(errMsg);  //TODO: Add a separate new exception for handling and managing
        }
    }
}
