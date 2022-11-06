package com.leaderhackdemo.servicerequests.intlayer.config;

import com.leaderhackdemo.servicerequests.intlayer.wiring.adapters.rest.outgoing.ApiClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenAPIClientImplConfig {

    @Value("${external-resources.someapi.base-url}")
    private String externalResourceBasePath;

    @Bean
    public ApiClientImpl prepareOpenApiClient(){
        return ApiClientImpl.builder()
                                .basePath(externalResourceBasePath)
                            .build();
    }
}
