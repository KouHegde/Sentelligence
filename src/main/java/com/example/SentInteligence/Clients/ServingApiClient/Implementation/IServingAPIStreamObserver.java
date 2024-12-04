package com.example.SentInteligence.Clients.ServingApiClient.Implementation;

import com.cisco.wcc.ccai.v1.InsightServingResponse;
import com.cisco.wcc.ccai.v1.StreamingInsightServingResponse;
import com.example.SentInteligence.Models.Response.AutoResponseResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.grpc.stub.StreamObserver;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.ServerException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import static com.example.SentInteligence.Utils.ServingApiClientUtils.*;

@Getter
@Slf4j
public class IServingAPIStreamObserver implements StreamObserver<StreamingInsightServingResponse> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onNext(StreamingInsightServingResponse servingResponse) {
        InsightServingResponse insightServingResponse = servingResponse.getInsightServingResponse();
        log.info("Received response from serving-api server: {}", insightServingResponse);

        InsightServingResponse.ServiceType serviceType = insightServingResponse.getInsightType();
        log.info("Received {} response from serving-api for conversationId: {}", serviceType, insightServingResponse.getConversationId());

        if (InsightServingResponse.ServiceType.DEFAULT_TRANSCRIPTION.equals(serviceType)) {
            String rawContentJson = insightServingResponse.getResponseContent().getRawContent();
            try {
                AutoResponseResult autoResponseResult = objectMapper.readValue(rawContentJson, AutoResponseResult.class);
                log.info("Auto response received: {} for conversationId: {}", autoResponseResult, insightServingResponse.getConversationId());
            } catch (JsonProcessingException e) {
                log.error("Unable to parse raw content json to AutoResponseResult due to {} for conversationId: {}", e.getMessage(), insightServingResponse.getConversationId(), e);
            }
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Error in serving-api streaming due to {}", throwable.getMessage(), throwable);
    }

    @Override
    public void onCompleted() {
        log.info("Serving-api streaming completed");
    }


//My test
//    private static final Logger LOGGER = LoggerFactory.getLogger(IServingAPIStreamObserver.class);
//    private static final String ERROR_MESSAGE = "Error in streamingRecognize, ";
//    private final AtomicLong isFinalCount = new AtomicLong(0L);
//    private static final String CONVERSATION_ID_MESSAGE = " for ConversationId : ";
//
//
//
//    private final AtomicReference<Throwable> err;
//    private final  String orgId;
//    private final String conversationId;
//    private final CountDownLatch latch;
//
//    public IServingAPIStreamObserver(AtomicReference<Throwable> err, String orgId, String conversationId, CountDownLatch latch) {
//        this.err = err;
//        this.orgId = orgId;
//        this.conversationId = conversationId;
//        this.latch = latch;
//    }
//
//
//    @Override
//    public void onNext(StreamingInsightServingResponse servingResponse) {
//        InsightServingResponse insightServingResponse = servingResponse.getInsightServingResponse();
//        try {
//            assertEquals(insightServingResponse.getOrgId(), orgId, "Org Id received in response does not match with orgId sent"); // TODO: Put NPE Check and make it fail safe
//            if ((insightServingResponse.getInsightType().name()).equalsIgnoreCase("TRANSCRIPTION") && insightServingResponse.getIsFinal()) {
//                isFinalCount.incrementAndGet();
//                validateTranscription(insightServingResponse,LOGGER);
//            } else if ((insightServingResponse.getInsightType().name()).equalsIgnoreCase("AGENT_ANSWERS") && insightServingResponse.getIsFinal()) {
//                isFinalCount.incrementAndGet();
//                processAgentAnswers(insightServingResponse,LOGGER);
//            }
//        } catch (ServerException e) {
//            throw new RuntimeException(e.getMessage(),e);
//        }
//
//    }
//
//    @Override
//    public void onError(Throwable throwable) {
//        LOGGER.error(" {} throwable : {} {} {} ", ERROR_MESSAGE, throwable, CONVERSATION_ID_MESSAGE, conversationId);
//        LOGGER.error(" {} getMessage : {} {} {} ", ERROR_MESSAGE, throwable.getMessage(), CONVERSATION_ID_MESSAGE, conversationId);
//        LOGGER.error(" {} getCause : {} {} {} ", ERROR_MESSAGE, throwable.getCause(), CONVERSATION_ID_MESSAGE, conversationId);
//        err.set(throwable.getCause());
//    }
//
//    @Override
//    public void onCompleted() {
//        LOGGER.info("Completed processing for conversationId: {}", conversationId);
//    }

}
