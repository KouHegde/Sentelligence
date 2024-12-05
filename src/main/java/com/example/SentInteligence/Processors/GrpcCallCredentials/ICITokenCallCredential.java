package com.example.SentInteligence.Processors.GrpcCallCredentials;

import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.Executor;
@Getter
@Slf4j
public class ICITokenCallCredential extends CallCredentials {
    /**
     Apply meta-data that needs to be passed along with GRPC request
     In this class we are passing the auth token along with the request
     **/

    @Override
    public void applyRequestMetadata(RequestInfo requestInfo, Executor executor, final MetadataApplier metadataApplier) {
//        String token = AuthHandler.getToken();
        executor.execute(() -> {
            try {
                Metadata headers = new Metadata();
                headers.put(Metadata.Key.of("authorization", Metadata.ASCII_STRING_MARSHALLER), "Bearer " + "token");
                Metadata.Key<String> trackingId = Metadata.Key.of("trackingId", Metadata.ASCII_STRING_MARSHALLER);
                headers.put(trackingId, "client_" + UUID.randomUUID().toString().replace("-", ""));
                log.info("Tracking ID is: {}", headers.get(trackingId));
                metadataApplier.apply(headers);
            } catch (Exception e) {
                log.error("Error in CITokenCallCredential due to {}", e.getMessage(), e);
                metadataApplier.fail(Status.UNAUTHENTICATED.withCause(e));
            }
        });
    }

//Mytest
//    private final String authToken;
//    private static final Logger LOGGER = LoggerFactory.getLogger(ICITokenCallCredential.class);
//
//    public ICITokenCallCredential(String authToken) {
//        this.authToken = authToken;
//    }
//
//    @Override
//    public void applyRequestMetadata(RequestInfo requestInfo, Executor executor, MetadataApplier metadataApplier) {
//        executor.execute(()-> {
//            try {
//                Metadata grpCHeaders = new Metadata();
//                Metadata.Key<String> tokenKey = Metadata.Key.of("authorization", Metadata.ASCII_STRING_MARSHALLER);
//                grpCHeaders.put(tokenKey, authToken);
//                Metadata.Key<String> trackingID = Metadata.Key.of("trackingid", Metadata.ASCII_STRING_MARSHALLER);
//                grpCHeaders.put(trackingID, "sample_client_" + UUID.randomUUID().toString().replace("-", ""));
//                LOGGER.info("Tracking ID is : {} " , grpCHeaders.get(trackingID));
//                metadataApplier.apply(grpCHeaders);
//            } catch (Exception e) {
//                metadataApplier.fail(Status.UNAUTHENTICATED.withCause(e));
//            }
//        });
//    }

}
