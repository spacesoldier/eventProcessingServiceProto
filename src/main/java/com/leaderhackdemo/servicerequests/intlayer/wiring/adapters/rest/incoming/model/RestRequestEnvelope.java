package com.leaderhackdemo.servicerequests.intlayer.wiring.adapters.rest.incoming.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Data @Builder
public class RestRequestEnvelope {
    private String requestId;
    private Map<String,String> pathVariables;
    private MultiValueMap<String,String> queryParams;
    private Object payload;
}
