package com.example.SentInteligence.Clients.ServingApiClient;

import com.cisco.wcc.ccai.v1.AiInsightGrpc;
import com.example.SentInteligence.Utils.EnvFeeder;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NegotiationType;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.example.SentInteligence.CommonConstants.CommonConstants.*;

@Slf4j
public class ServingAPIClient {

    @Getter
    private final AiInsightGrpc.AiInsightBlockingStub blockingStub;
    @Getter
    private final AiInsightGrpc.AiInsightStub asyncStub;
    private ManagedChannel channel;

    public ServingAPIClient() {
        String servingUrl = EnvFeeder.get(SERVING_URL);
        int servingPort = EnvFeeder.getInt(SERVING_PORT);
        boolean useTls = EnvFeeder.getBoolean(SERVING_USE_TLS);
        try {
            if (useTls) {
                channel = NettyChannelBuilder.forAddress(servingUrl, servingPort).negotiationType(NegotiationType.TLS).build();
                log.info("Building TLS channel with API endpoint: {}, port: {} ", servingUrl, servingPort);

            } else {
                channel = NettyChannelBuilder.forAddress(servingUrl, servingPort).negotiationType(NegotiationType.PLAINTEXT).build();
                log.info("Building NonTLS channel with API endpoint: {}, port: {} ", servingUrl, servingPort);
            }
        } catch (Exception e) {
            log.info("Error in creating channel: {}", e.getMessage(), e);
        }
        blockingStub = AiInsightGrpc.newBlockingStub(channel);
        asyncStub = AiInsightGrpc.newStub(channel);
        addShutDownHook();
    }

    public void addShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.warn("Shutting down serving-api grpc channel");
            try {
                if (Objects.nonNull(channel)) {
                    log.debug("channel.shutdown() called, terminating in 5 secs");
                    channel.shutdown().awaitTermination(3, TimeUnit.SECONDS);
                    channel.shutdownNow();
                }
            } catch (InterruptedException e) {
                log.error("Error in shutting down channel due to {}", e.getMessage(), e);
                Thread.currentThread().interrupt();
            }
        }));
    }



//Mytest

//    private static final Logger LOGGER = LoggerFactory.getLogger(ServingAPIClient.class);
//
//    public final AiInsightGrpc.AiInsightBlockingStub blockingStub;
//    public final AiInsightGrpc.AiInsightStub asyncStub;
//    ManagedChannel channel = null;
//    private static final boolean useTLS = false;
//
//    public ServingAPIClient(String apiUrl, int port) {
//        try {
//            if (true) {
//                channel = NettyChannelBuilder.forAddress(apiUrl,port)
//                        .negotiationType(NegotiationType.TLS)
//                        .build();
//                LOGGER.info("building TLS channel with API Endpoint : {}, port : {} " , apiUrl , port);
//
//            } else {
//                channel = NettyChannelBuilder.forAddress(apiUrl,port)
//                        .negotiationType(NegotiationType.PLAINTEXT)
//                        .build();
//                LOGGER.info("Building NonTLS channel with API Endpoint : {}, port : {} " , apiUrl , port);
//            }
//        } catch (Exception e) {
//            LOGGER.error(e.toString());
//            e.printStackTrace();
//        }
//        blockingStub = AiInsightGrpc.newBlockingStub(channel);
//        asyncStub = AiInsightGrpc.newStub(channel);
//    }
//
//    public void shutDown(String conversationId) {
//        try {
//
//            if (channel != null) {
//                LOGGER.debug("channel.shutdown() called on conversationId : {} terminating in 3 secs ",conversationId);
//                channel.shutdown().awaitTermination(3, TimeUnit.SECONDS);
//                channel.shutdownNow();
//            }
//        } catch (InterruptedException e) {
//            LOGGER.error("InterruptedException: ", e);
//            Thread.currentThread().interrupt();
//        }
//
//    }

}