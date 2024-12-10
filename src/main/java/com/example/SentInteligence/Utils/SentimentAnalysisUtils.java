package com.example.SentInteligence.Utils;

import com.example.SentInteligence.Model.Request.TranscriptContent;
import com.example.SentInteligence.Model.Response.*;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SentimentAnalysisUtils {

    private static final String CONTENT = "content";
    private static final String ROLE = "role";


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

    public static ConvosResponse getOrgConvIds(String orgConvIdJson) throws JsonProcessingException {
        if (orgConvIdJson != null && !orgConvIdJson.isEmpty()) {
            return JsonUtils.fromJson(orgConvIdJson, ConvosResponse.class);
        }
        return null;
    }
}
