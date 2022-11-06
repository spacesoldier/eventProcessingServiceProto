package com.leaderhackdemo.servicerequests.features.parsedataset.logic;

import com.leaderhackdemo.servicerequests.features.parsedataset.logic.manipulation.NumeratedFieldsObjectManipulator;
import com.leaderhackdemo.servicerequests.features.parsedataset.model.data.ServiceEventRaw;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ManipulationConf {

    @Bean(name = "ServiceEventRaw")
    public NumeratedFieldsObjectManipulator initFieldManipulator(){

        return NumeratedFieldsObjectManipulator.builder()
                                            .delimiter("_")
                                            .fieldNumberPosition(1)
                                            .fieldNamePosition(2)
                                            .typeToManipulate(ServiceEventRaw.class)
                                            .constructObjectToManipulate(
                                                    () -> ServiceEventRaw.builder().build()
                                            )
                                        .build();
    }
}
