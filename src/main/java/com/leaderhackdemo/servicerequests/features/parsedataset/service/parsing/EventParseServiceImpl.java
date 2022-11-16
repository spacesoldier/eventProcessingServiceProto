package com.leaderhackdemo.servicerequests.features.parsedataset.service.parsing;

import com.leaderhackdemo.servicerequests.features.parsedataset.logic.manipulation.NumeratedFieldsObjectManipulator;
import com.leaderhackdemo.servicerequests.features.parsedataset.model.DataSetFiles;
import com.leaderhackdemo.servicerequests.features.parsedataset.model.data.ServiceEventRaw;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

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
    public ServiceEventRaw parseEventFromString(String eventStr, String partsDelimiter) {

        List<String> partsOfObject = List.of(
                eventStr.split(
                        partsDelimiter
                )
        );

        log.info("[EVENT PARSER]: received new event "+ partsOfObject.get(0));

        ServiceEventRaw result = (ServiceEventRaw) numeratedFieldsObjectManipulator
                                                            .constructObjectFromList(
                                                                                        partsOfObject
                                                                                    );

        return result;
    }

}
