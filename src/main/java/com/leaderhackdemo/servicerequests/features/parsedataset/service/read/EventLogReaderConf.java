package com.leaderhackdemo.servicerequests.features.parsedataset.service.read;

import com.leaderhackdemo.servicerequests.features.parsedataset.model.DataSetFiles;
import com.leaderhackdemo.servicerequests.features.parsedataset.service.model.ParseEventLogRequest;
import com.leaderhackdemo.servicerequests.features.parsedataset.service.model.ReadEventLogRequest;
import com.leaderhackdemo.servicerequests.intlayer.wiring.adapters.WiringAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventLogReaderConf {

    @Autowired
    DataSetFiles dataPaths;

    @Bean
    public void dataFilesLookup(){
        if (dataPaths != null){
            dataPaths.getFiles().forEach(
                    path -> {
                        log.info("[DATASETS]: "+path.getPath());
                    }
            );
        }
    }


    @Autowired
    private WiringAdapter wiringAdapter;

    @Autowired
    EventLogReaderService eventLogReaderService;

    @Bean
    public void initEventLogRead() {

        wiringAdapter.registerInitAction(
                "fetch query status types list",
                () -> {
                    log.info("[INIT]: parse event log initialization");
                    return ReadEventLogRequest.builder().build();
                }
        );

        wiringAdapter.registerFeature(
                ReadEventLogRequest.class,
                requestObj -> {
                    // to return non-null something,
                    // object router will be pleased to receive non-null, believe me
                    Object result = "OK";

                    try{
                        result = eventLogReaderService.readEventLog(dataPaths.getFiles().get(0));
                    } catch (Exception e){
                        log.info("[EVENT LOG READER]: crashed when reading file due to error "+e.getMessage());
                    }

                    return result;
                }
        );
    }

}
