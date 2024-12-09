package com.example.SentInteligence.Utils;

import com.cisco.wcc.ccai.v1.InsightServingResponse;
import com.cisco.wcc.ccai.v1.Suggestions;
import com.example.SentInteligence.Model.Request.TranscriptContent;
import com.example.SentInteligence.Model.Response.ElasticSearchResponse;
import com.example.SentInteligence.Model.Response.Hit;
import com.example.SentInteligence.Model.Response.Hits;
import com.google.common.base.Objects;
import org.slf4j.Logger;

import java.rmi.ServerException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ServingApiClientUtils {
    private static final String LATENCY_CONSTANT = "Latency : ";
    private static final String ROLE = ", role:";
    private static final String PIPELINE = " | ";


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

    public static List<TranscriptContent> getContentFromHits(ElasticSearchResponse response){
        AtomicInteger orderCounter = new AtomicInteger(0); // Counter to assign sequential order
        return Optional.ofNullable(response)
                .map(ElasticSearchResponse::getHits)                      // Get Hits
                .map(Hits::getHits)                                       // Get List<Hit>
                .orElse(Collections.emptyList())                          // Handle nulls gracefully
                .stream()                                                 // Stream the List<Hit>
                .map(Hit::getSource)                                      // Map to _source
                .filter(source -> source != null && source.containsKey("content") && source.containsKey("role")) // Ensure both keys exist
                .map(source -> new TranscriptContent(
                        (String) source.get("content"),                   // Extract "content"
                        orderCounter.getAndIncrement(),                   // Assign sequential order
                        (String) source.get("role")                       // Extract "role"
                ))
                .collect(Collectors.toList());
    }

    public static String getMergedContentByOffset(List<TranscriptContent> transcriptList, int offset, int limit) {

        if (transcriptList == null || transcriptList.isEmpty()) {
            throw  new RuntimeException("Could not able to get the proper transcriptContent from hits") ;  // Return an empty string if the list is null or empty
        }

        // Ensure offset is within bounds
        if (offset >= transcriptList.size()) {
            throw  new RuntimeException("Offset is out of bound") ; // Return an empty string if offset is out of bounds
        }

        // Adjust the limit to avoid exceeding the list size
        int adjustedLimit = Math.min(offset + limit, transcriptList.size());

        // Filter based on the offset and limit
        return transcriptList.subList(offset, adjustedLimit).stream()
                .map(tc -> tc.getContent() + ROLE + tc.getRole()) // Format each item
                .collect(Collectors.joining(PIPELINE));                   // Merge into a single string with delimiter
    }
}
