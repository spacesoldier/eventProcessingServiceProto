package com.leaderhackdemo.servicerequests.intlayer.startup;

import com.leaderhackdemo.servicerequests.intlayer.routing.IntlayerObjectRouter;
import com.leaderhackdemo.servicerequests.intlayer.wiring.adapters.rest.outgoing.ApiClientAdapter;
import com.leaderhackdemo.servicerequests.intlayer.wiring.adapters.rest.outgoing.model.adapter.ExternalResourceCallDefinition;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

public class AppReadyListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ConfigurableApplicationContext context = event.getApplicationContext();

        Map<String, ExternalResourceCallDefinition> externalResourceCallDefs = context.getBeansOfType(ExternalResourceCallDefinition.class);

        if (!externalResourceCallDefs.isEmpty()){

            ApiClientAdapter adapter = context.getBean(ApiClientAdapter.class);

            externalResourceCallDefs.forEach(
                    (key, value) -> adapter.registerResourceClient(value)
            );
        }

        IntlayerObjectRouter objectRouter = context.getBean(IntlayerObjectRouter.class);

        objectRouter.start();
    }

}
