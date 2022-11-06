package com.leaderhackdemo.servicerequests.features.parsedataset.service.parsing;

import com.leaderhackdemo.servicerequests.features.parsedataset.model.DataSetFiles;
import com.leaderhackdemo.servicerequests.features.parsedataset.service.parsing.EventParseService;
import com.leaderhackdemo.servicerequests.features.parsedataset.service.model.ParseEventLogRequest;
import com.leaderhackdemo.servicerequests.intlayer.wiring.adapters.WiringAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component

//@EnableConfigurationProperties(value = DataSetFiles.class)
@Slf4j
public class ParseDatasetConf {


    @Autowired
    EventParseService eventParseService;

    @Autowired
    private WiringAdapter wiringAdapter;

    private String CSV_ROWS_DELIMITER = Pattern.quote("$");  // nice delimiter choice, guys!
                                                                // https://stackoverflow.com/questions/31402113/why-i-cannot-split-string-with-in-java

    @Bean
    public void setupEventParseServiceIntegration(){

        wiringAdapter.registerFeature(
            ParseEventLogRequest.class,
            requestObj -> {
                // to return non-null something,
                // object router will be pleased to receive non-null, believe me
                Object result = "OK";

                try{
                    ParseEventLogRequest request = (ParseEventLogRequest) requestObj;
                    result = eventParseService.parseEventFromString(
                                                                        request.getEventLogRecord(),
                                                                        CSV_ROWS_DELIMITER
                                                                    );
                } catch (Exception e){
                    log.info("[EVENT PARSER]: cannot parse event from string");
                }

                return result;
            }
        );
    }
}
