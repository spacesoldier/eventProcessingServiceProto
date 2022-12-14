package com.leaderhackdemo.servicerequests.intlayer.wiring.adapters.rest.outgoing.model.adapter;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import reactor.core.CorePublisher;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class ExternalResourceCallDefinition<T,R> {
    @Getter
    private String path;
    @Getter
    private HttpMethod method;

    @Getter
    private Class outgoingMsgType;

    @Getter
    private Function<T, ? extends CorePublisher<R>> resourceInvocationCall;

    @Getter
    private Map<HttpStatus, Function<String, Throwable>> failStatusHandlers;

    @Builder
    private ExternalResourceCallDefinition(
            String path,
            HttpMethod method,
            Class outgoingMsgType,
            Function<T, ? extends CorePublisher<R>> resourceInvocationCall,
            Map<HttpStatus, Function<String, Throwable>> errorHandlers
    ){
        this.path = path;
        this.method = method;

        this.outgoingMsgType = outgoingMsgType;
        this.resourceInvocationCall = resourceInvocationCall;

        this.failStatusHandlers = new HashMap<>();

        if (errorHandlers != null && !errorHandlers.isEmpty()){
            errorHandlers.entrySet().stream()
                    .filter(entry -> entry.getKey() != HttpStatus.OK)
                    .toList().forEach(
                            entry -> this.failStatusHandlers.put(
                                    entry.getKey(),
                                    entry.getValue()
                            )
                    );
        }

    }
}
