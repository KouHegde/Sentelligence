package com.example.SentInteligence.Controller;

import ch.qos.logback.core.util.StringUtil;
import com.example.SentInteligence.Model.Request.RequestWrapper;
import com.example.SentInteligence.Model.Request.UpdateSentimentUIRequest;
import com.example.SentInteligence.Model.Response.ConversationSentiment;
import com.example.SentInteligence.Model.Response.ElasticSearchResponse;
import com.example.SentInteligence.Model.Response.ConvosResponse;
import com.example.SentInteligence.Model.Response.ResponseWrapper;
import com.example.SentInteligence.Model.Response.UpdateUIResponse;
import com.example.SentInteligence.Utils.ApplicationPropertiesUtils;
import com.example.SentInteligence.Service.SentimentAnalysisService;
import com.example.SentInteligence.Utils.JsonUtils;
import com.example.SentInteligence.Utils.SentimentAnalysisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Objects;

import static com.example.SentInteligence.CommonConstants.CommonConstants.*;


@RestController
@RequestMapping("/sentiment/")
public class SentimentBotUIController {
    private static final String ORG_CONVID = "org_convId";
    private static final String ORG_ID = "orgId";
    private final SentimentAnalysisService<ElasticSearchResponse, ConversationSentiment> sentimentAnalysisService;
    private final ApplicationPropertiesUtils applicationPropertiesUtils;


    @Autowired
    public SentimentBotUIController(SentimentAnalysisService<ElasticSearchResponse, ConversationSentiment> sentimentAnalysisService, ApplicationPropertiesUtils applicationPropertiesUtils) {
        this.sentimentAnalysisService = sentimentAnalysisService;
        this.applicationPropertiesUtils = applicationPropertiesUtils;
    }

    @PostMapping("/conversation/{convId}/update-ui")
    public ResponseEntity<UpdateUIResponse> processSentiment(
            @PathVariable("convId") String convId,
            @RequestParam Map<String, String> requestParams,
            @RequestBody UpdateSentimentUIRequest updateSentimentUIRequest) {
        try {
            RequestWrapper<ElasticSearchResponse> elasticDetailDetailRequest = getElasticDetailRequestWrapper(updateSentimentUIRequest,convId, requestParams);
            ResponseWrapper<ConversationSentiment> response = sentimentAnalysisService.analyseSentiment(elasticDetailDetailRequest);
            return ResponseEntity.ok(getUIResponse(response,elasticDetailDetailRequest));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/convos")
    public ConvosResponse getOrgConvIds() throws JsonProcessingException {
        String orgConvIdJson = applicationPropertiesUtils.getPropertyValue(ORG_CONVID);
        return SentimentAnalysisUtils.getOrgConvIds(orgConvIdJson);

    }

    private UpdateUIResponse getUIResponse(ResponseWrapper<ConversationSentiment> response, RequestWrapper<ElasticSearchResponse> elasticSearchResponseRequest) {
        if(Objects.isNull(response)){
            throw new RuntimeException("Something went wrong");
        }
        return UpdateUIResponse.builder()
                .confidenceScore(Objects.nonNull(response.getBody()) ? response.getBody().getScore() : 0)
                .orgId(elasticSearchResponseRequest.getRequestParams().get("orgID"))
                .conversationId(elasticSearchResponseRequest.getConvId())
                .rating(Objects.nonNull(response.getBody()) ? response.getBody().getRating() : UNKNOWN)
                .description(Objects.nonNull(response.getBody()) ? response.getBody().getDescription() : "Unknown transcript")
                .build();

    }

    private RequestWrapper<ElasticSearchResponse> getElasticDetailRequestWrapper(UpdateSentimentUIRequest updateSentimentUIRequest, String convId, Map<String, String> requestParam) {
        if(Objects.isNull(updateSentimentUIRequest)){
            throw new RuntimeException("Invalid request offset is empty");
        }
        ElasticSearchResponse elasticSearchResponse;
        try {
            elasticSearchResponse = JsonUtils.fromJson(TRANSCRIPT_JSON, ElasticSearchResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if(Objects.isNull(elasticSearchResponse)){
            throw new RuntimeException("Failed to get the transcript from the json");
        }
        return new RequestWrapper<>(convId,requestParam.get(ORG_ID),elasticSearchResponse,requestParam,updateSentimentUIRequest.getTranscriptOffset(),updateSentimentUIRequest.getTranscriptLimit());
    }

}


