package com.leaderhackdemo.servicerequests.intlayer.config;

import com.leaderhackdemo.servicerequests.intlayer.routing.IntlayerObjectRouter;
import com.leaderhackdemo.servicerequests.intlayer.wiring.adapters.WiringAdapter;
import com.leaderhackdemo.servicerequests.intlayer.wiring.providers.MonoChannelProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WiringConfig {

    @Autowired
    MonoChannelProvider monoChannelProvider;

    @Autowired
    IntlayerObjectRouter intlayerObjectRouter;

    @Bean
    public WiringAdapter initWiringAdapter(){

        WiringAdapter wiring = WiringAdapter.builder()
                    .incomingMsgSink        (   intlayerObjectRouter.singleRequestsInput()                      )
                    .monoProvider           (
                                                requestId -> monoChannelProvider.newWire(requestId)
                                                                                .getMonoToSubscribe()
                                            )
                    .routableFunctionSink   (
                                                (typeToRoute,routableFn) ->
                                                        intlayerObjectRouter.addRoutableFunction(
                                                                                                    typeToRoute,
                                                                                                    routableFn
                                                                                                )
                                            )
                .build();

        intlayerObjectRouter.setOnRouterReadyAction(wiring.invokeInitActions());

        return wiring;
    }
}
