package com.leaderhackdemo.servicerequests.intlayer.config;

import com.leaderhackdemo.servicerequests.intlayer.wiring.providers.FluxChannelProvider;
import com.leaderhackdemo.servicerequests.intlayer.wiring.providers.MonoChannelProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ChannelProvidersConfig {

    @Bean
    public FluxChannelProvider initFluxProvider(){
        return FluxChannelProvider.builder().build();
    }

    @Bean
    public MonoChannelProvider initMonoProvider(){
        return MonoChannelProvider.builder().build();
    }
}
