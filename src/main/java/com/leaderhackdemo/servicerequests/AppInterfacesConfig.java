package com.leaderhackdemo.servicerequests;

import com.leaderhackdemo.servicerequests.intlayer.wiring.adapters.rest.incoming.EndpointAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppInterfacesConfig {

    @Autowired
    EndpointAdapter endpointAdapter;


}
