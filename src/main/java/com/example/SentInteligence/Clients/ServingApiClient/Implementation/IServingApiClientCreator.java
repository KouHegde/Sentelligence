package com.example.SentInteligence.Clients.ServingApiClient.Implementation;

import com.cisco.wcc.ccai.v1.InsightServingRequest;
import com.cisco.wcc.ccai.v1.StreamingInsightServingRequest;
import com.example.SentInteligence.Clients.ClientCreator;
import com.example.SentInteligence.Clients.ServingApiClient.ServingAPIClient;
import com.example.SentInteligence.Processors.GrpcCallCredentials.ICITokenCallCredential;
import com.example.SentInteligence.Utils.EnvFeeder;
import io.grpc.CallCredentials;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static com.example.SentInteligence.Clients.ServingApiClient.ServingApiConstants.ServingApiConstants.HISTORICAL_MESSAGE_FLAG;
import static com.example.SentInteligence.Clients.ServingApiClient.ServingApiConstants.ServingApiConstants.HISTORICAL_VIRTUAL_AGENT_FLAG;
import static com.example.SentInteligence.Clients.ServingApiClient.ServingApiConstants.ServingApiConstants.REALTIME_MESSAGE_FLAG;
import static com.example.SentInteligence.CommonConstants.CommonConstants.*;


@Getter
@Setter
@Builder
@Slf4j
public class IServingApiClientCreator implements ClientCreator {
    public void doServerStreaming(long timeOut,String conversationId) {
        log.info("Received request to start consuming streaming responses for conversationId: {}", conversationId);
        InsightServingRequest insightServingRequest = InsightServingRequest.newBuilder()
                .setConversationId(conversationId)
                .setOrgId(EnvFeeder.get(ORG_ID))
                .setRealTimeTranscripts(EnvFeeder.getBoolean(REALTIME_TRANSCRIPT_FLAG))
                .setHistoricalTranscripts(EnvFeeder.getBoolean(HISTORICAL_TRANSCRIPT_FLAG))
                .setRealtimeAgentAssist(EnvFeeder.getBoolean(REALTIME_AGENT_ASSIST_FLAG))
                .setHistoricalAgentAssist(EnvFeeder.getBoolean(HISTORICAL_AGENT_ASSIST_FLAG))
                .setRealTimeMessage(EnvFeeder.getBoolean(REALTIME_MESSAGE_FLAG))
                .setHistoricalMessage(EnvFeeder.getBoolean(HISTORICAL_MESSAGE_FLAG))
                .setHistoricalVirtualAgent(EnvFeeder.getBoolean(HISTORICAL_VIRTUAL_AGENT_FLAG))
                .build();
        log.info("Insight serving request: {}", insightServingRequest);

        StreamingInsightServingRequest streamingInsightServingRequest = StreamingInsightServingRequest.newBuilder().setInsightServingRequest(insightServingRequest).build();
        log.info("Streaming insight serving request: {}", streamingInsightServingRequest);

        ServingAPIClient servingChannelBuilder = new ServingAPIClient();
        CallCredentials callCredential = new ICITokenCallCredential();
        servingChannelBuilder.getAsyncStub()
                .withCallCredentials(callCredential)
                .withWaitForReady()
                .streamingInsightServing(streamingInsightServingRequest, new IServingAPIStreamObserver());
        log.info("Starting streaming for conversationId: {}", conversationId);
    }
}



//Mytest

//private static final Logger LOGGER = LoggerFactory.getLogger(IServingApiClientCreator.class);
//private final String orgId;
//private final String token;
//private final String conversationId;
//private final Map<String, Boolean> flags;
//private final ServingAPIClient servingAPIClient;
//private final CountDownLatch finishLatch = new CountDownLatch(1);

//    public IServingApiClientCreator(String orgId, String token, String conversationId, Map<String, Boolean> flags, ServingAPIClient servingAPIClient) {
//        this.orgId = orgId;
//        this.token = token;
//        this.conversationId = conversationId;
//        this.flags = flags;
//        this.servingAPIClient = servingAPIClient;
//    }

//    @Override
//    public AtomicReference<Throwable> doServerStreamingCall(long timeOut) {
//        AtomicReference<Throwable> err = new AtomicReference<>(null);
//        ICITokenCallCredential iCiTokenCallCredential = new ICITokenCallCredential(token);
//        LOGGER.info("conversationId is : {}", conversationId);
//
//        try {
//            LOGGER.debug("Creating the client request");
//            InsightServingRequest insightServingRequest = InsightServingRequest.newBuilder()
//                    .setConversationId(conversationId)
//                    .setOrgId(orgId)
//                    .setRealTimeTranscripts(flags.get(REALTIME_TRANSCRIPT_FLAG))
//                    .setHistoricalTranscripts(flags.get(HISTORICAL_TRANSCRIPT_FLAG))
//                    .setRealtimeAgentAssist(flags.get(REALTIME_AGENT_ASSIST_FLAG))
//                    .setHistoricalAgentAssist(flags.get(HISTORICAL_AGENT_ASSIST_FLAG))
//                    .setRealTimeMessage(flags.get(REALTIME_MESSAGE_FLAG))
//                    .setHistoricalMessage(flags.get(HISTORICAL_MESSAGE_FLAG))
//                    .setHistoricalVirtualAgent(flags.get(HISTORICAL_VIRTUAL_AGENT_FLAG))
//                    .build();
//
//            LOGGER.info("request is : \n {} ", insightServingRequest);
//            StreamingInsightServingRequest request = StreamingInsightServingRequest.newBuilder()
//                    .setInsightServingRequest(insightServingRequest)
//                    .build();
//            logCurrentTimestamp();
//
//            StreamObserver<StreamingInsightServingResponse> responseStreamObserver = new IServingAPIStreamObserver(err,orgId,conversationId,finishLatch);
//            servingAPIClient.asyncStub.withCallCredentials(iCiTokenCallCredential).withWaitForReady().streamingInsightServing(request, responseStreamObserver);
//
//            boolean await = finishLatch.await(timeOut, TimeUnit.SECONDS);
//
//            if(!await) {
//                LOGGER.info("calling context cancelled from client for conversationId : {} after {} seconds ",conversationId,timeOut);
//                Context.current().withCancellation().cancel(null);
//            }
//            LOGGER.info("Current Thread : {}, for conversationId : {}", Thread.currentThread().getName(), conversationId);
//        }catch (Exception e){
//            LOGGER.warn("Exception occurred : {} ", e.getMessage());
//            err.set(e);
//        }
//        return  err;
//    }
//
//
//
//    private void logCurrentTimestamp() {
//        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(Calendar.getInstance().getTime());
//        LOGGER.debug("current time is : {}", timeStamp);
//    }


