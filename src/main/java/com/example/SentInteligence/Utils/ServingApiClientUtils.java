package com.example.SentInteligence.Utils;

import com.cisco.wcc.ccai.v1.InsightServingResponse;
import com.cisco.wcc.ccai.v1.Suggestions;
import com.example.SentInteligence.Model.Request.TranscriptContent;
import com.example.SentInteligence.Model.Response.*;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    private static final String CONTENT = "content";
    private static final String ROLE = "role";


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

    public static void validateTranscription(InsightServingResponse insightServingResponse, Logger logger) throws ServerException {
        for (int i = 0; i < insightServingResponse.getResponseContent().getRecognitionResult().getAlternativesCount(); i++) {
            logger.info("{}:{} msecs , transcript : {} ", LATENCY_CONSTANT, (System.currentTimeMillis() - insightServingResponse.getPublishTimestamp()), insightServingResponse.getResponseContent().getRecognitionResult().getAlternatives(i).getTranscript());
            if (insightServingResponse.getIsFinal() && insightServingResponse.getInsightProvider().name().equalsIgnoreCase("cisco")) {
                String expected = "lost my credit card while I was traveling yesterday";
                String actual = insightServingResponse.getResponseContent().getRecognitionResult().getAlternatives(i).getTranscript();
                logger.info("Transcript for CISCO:: Expected: {}, Actual: {}", expected, actual);
                assertTrue(insightServingResponse.getResponseContent().getRecognitionResult().getAlternatives(i).getTranscript().contains(expected), "Transcript does not match for CISCO");
            } else if (insightServingResponse.getIsFinal() && insightServingResponse.getInsightProvider().name().equalsIgnoreCase("google")) {
                String expected = "lost my credit card while I was traveling yesterday";
                String actual = insightServingResponse.getResponseContent().getRecognitionResult().getAlternatives(i).getTranscript();
                logger.info("Transcript for GOOGLE:: Expected: {}, Actual: {}", expected, actual);
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
        if (!Objects.equal(expected, actual)) {
            throw new ServerException(errMsg);  //TODO: Add a separate new exception for handling and managing
        }
    }

    public static void assertFalse(boolean expected, String errMsg) throws ServerException {
        if (Boolean.TRUE.equals(expected)) {
            throw new ServerException(errMsg);  //TODO: Add a separate new exception for handling and managing
        }
    }

    public static void assertTrue(boolean expected, String errMsg) throws ServerException {
        if (Boolean.FALSE.equals(expected)) {
            throw new ServerException(errMsg);  //TODO: Add a separate new exception for handling and managing
        }
    }

    public static List<TranscriptContent> getContentFromHits(ElasticSearchResponse response) {
        AtomicInteger orderCounter = new AtomicInteger(0);
        return Optional.ofNullable(response)
                .map(ElasticSearchResponse::getHits)
                .map(Hits::getHits)
                .orElse(Collections.emptyList())
                .stream()
                .map(Hit::get_source)
                .filter(source -> source != null && source.containsKey(CONTENT) && source.containsKey(ROLE)) // Ensure both keys exist
                .map(source -> new TranscriptContent(
                        (String) source.get(CONTENT),
                        orderCounter.getAndIncrement(),
                        (String) source.get(ROLE)
                ))
                .collect(Collectors.toList());
    }

    public static String getMergedContentByOffset(List<TranscriptContent> transcriptList, int offset, int limit) {
        validateInput(transcriptList, offset);

        int adjustedLimit = Math.min(offset + limit, transcriptList.size());
        StringBuilder finalString = new StringBuilder();

        for (int i = offset; i < adjustedLimit; i++) {
            appendTranscriptContent(finalString, transcriptList.get(i));
        }

        return finalString.toString().trim();
    }

    private static void validateInput(List<TranscriptContent> transcriptList, int offset) {
        if (transcriptList == null || transcriptList.isEmpty()) {
            throw new IllegalArgumentException("Transcript list is null or empty");
        }
        if (offset >= transcriptList.size()) {
            throw new IndexOutOfBoundsException("Offset is out of bounds");
        }
    }

    private static void appendTranscriptContent(StringBuilder finalString, TranscriptContent transcriptContent) {
        if (transcriptContent == null || transcriptContent.getContent() == null || transcriptContent.getContent().isEmpty()) {
            return;
        }

        Content content = parseContent(transcriptContent.getContent());
        if (content == null) {
            return;
        }

        appendAlternatives(finalString, transcriptContent, content);
        appendReplyText(finalString, transcriptContent, content);
    }

    private static Content parseContent(String contentJson) {
        try {
            return JsonUtils.fromJson(contentJson, Content.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing JSON for transcript content", e);
        }
    }

    private static void appendAlternatives(StringBuilder finalString, TranscriptContent transcriptContent, Content content) {
        if (content.getAlternatives() == null || content.getAlternatives().isEmpty()) {
            return;
        }

        String alternativeTranscript = content.getAlternatives().get(0).getTranscript();
        if (alternativeTranscript != null && !alternativeTranscript.isEmpty()) {
            finalString.append(transcriptContent.getRole())
                    .append(":")
                    .append(alternativeTranscript)
                    .append(" ");
        }
    }

    private static void appendReplyText(StringBuilder finalString, TranscriptContent transcriptContent, Content content) {
        if (content.getReplyText() == null || content.getReplyText().isEmpty()) {
            return;
        }

        String replyText = content.getReplyText().get(0);
        if (replyText != null && !replyText.isEmpty()) {
            finalString.append(transcriptContent.getRole())
                    .append(":")
                    .append(replyText)
                    .append(" ");
        }
    }
}
