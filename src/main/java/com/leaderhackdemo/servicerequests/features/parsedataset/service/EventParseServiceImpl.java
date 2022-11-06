package com.leaderhackdemo.servicerequests.features.parsedataset.service;

import com.leaderhackdemo.servicerequests.features.parsedataset.logic.manipulation.NumeratedFieldsObjectManipulator;
import com.leaderhackdemo.servicerequests.features.parsedataset.model.data.ServiceEventRaw;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.lang.reflect.Method;
import java.util.List;

@Service
@Slf4j
public class EventParseServiceImpl implements EventParseService{

    @Autowired @Qualifier("ServiceEventRaw")
    NumeratedFieldsObjectManipulator numeratedFieldsObjectManipulator;

    private static String csvStringSplitter = "@";

    private List<String> splitStringIntoParts(String strToSplit){
        return List.of(
                        strToSplit.split(csvStringSplitter)
                      );
    }

    @Override
    public ServiceEventRaw parseEventFromString(String eventStr) {
        return null;
    }

    @Override
    public void performSomeShit() {
//        ServiceEventRaw rawEvent = ServiceEventRaw.builder()
//                                                    .build();
//
//        List<Method> rawMethods = rawEvent.getMethods();
//
//        List<Method> rawEventFieldGetters = rawMethods
//                                                        .stream()
//                                                        .filter(
//                                                                method -> method.getName()
//                                                                                .contains("getField")
//                                                        )
//                                                        .toList();
//
//        rawEventFieldGetters.forEach(
//                getter -> log.info("[GETTER]: "+getter.getName())
//        );
//
//        List<Method> rawEventFieldSetters = rawMethods
//                                                        .stream()
//                                                        .filter(
//                                                                method -> method.getName()
//                                                                        .contains("setField")
//                                                        )
//                                                        .toList();
//
//        rawEventFieldSetters.forEach(
//                setter -> log.info("[SETTER]: "+setter.getName())
//        );
//
//        ServiceEventRaw newEvent = null;
//
//        try{
//            Method callMethod = rawEventFieldSetters.get(0);
//            log.info("[SET FIELD]: will invoke "+callMethod.getName());
//            callMethod.invoke(rawEvent,"WOOOOW");
//        } catch (Exception e){
//            log.info("[PARSER]: experiment failed");
//        }
//
//        String newField = null;
//        try{
//            Method callMethod = rawEventFieldGetters.get(0);
//            log.info("[SET FIELD]: will invoke "+callMethod.getName());
//            newField = (String) callMethod.invoke(rawEvent);
//        } catch (Exception e){
//            log.info("[PARSER]: experiment failed");
//        }


        log.info("[PARSE EVENTS]: boooooooo");
    }
}
