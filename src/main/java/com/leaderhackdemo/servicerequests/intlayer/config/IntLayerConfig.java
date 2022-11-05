package com.leaderhackdemo.servicerequests.intlayer.config;

import com.leaderhackdemo.servicerequests.intlayer.wiring.adapters.WiringAdapter;
import com.leaderhackdemo.servicerequests.intlayer.wiring.adapters.rest.incoming.EndpointAdapter;
import com.leaderhackdemo.servicerequests.intlayer.wiring.adapters.rest.outgoing.ApiClient;
import com.leaderhackdemo.servicerequests.intlayer.wiring.adapters.rest.outgoing.ApiClientAdapter;
import com.leaderhackdemo.servicerequests.intlayer.wiring.adapters.rest.outgoing.ApiClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class IntLayerConfig {

    @Autowired
    WiringAdapter wiringAdapter;

    @Bean
    public EndpointAdapter initEndpointAdapter(){

        return EndpointAdapter.builder()
                                .monoProvider(
                                        rqId -> wiringAdapter.initSingleRequest(rqId)
                                )
                                .requestSink(
                                        (rqId, payload) -> wiringAdapter.receiveSingleRequest(rqId,payload)
                                )
                .build();
    }

    @Autowired
    private ApiClientImpl apiClientImplementation;


    @Bean
    public ApiClientAdapter initApiClientAdapter(){
        return ApiClientAdapter.builder()
                                        .errorHandlerSink(  apiClientImplementation.errorHandlerSink()  )
                                        .routableFunctionSink(
                                            (rqType, handler) -> wiringAdapter.registerFeature(rqType,handler)
                                        )
                                .build();

    }

    @Bean
    public ApiClient buildApiClient(){
        return ApiClient.builder()
                .paramToMVMapConverter(
                        queryParamConfig -> apiClientImplementation.parameterToMultiValueMap(
                                                                                queryParamConfig.getCollectionFormat(),
                                                                                queryParamConfig.getName(),
                                                                                queryParamConfig.getValue()
                                                                        )
                )
                .headersToMediaTypeConverter(
                        accepts -> apiClientImplementation.selectHeaderAccept(accepts)
                )
                .headerContentTypeConverter(
                        contentTypes -> apiClientImplementation.selectHeaderContentType(contentTypes)
                )
                .apiCallClientProxy(
                        apiClientImplementation.invokeAPIfnWrapper()
                )
              .build();
    }
}
