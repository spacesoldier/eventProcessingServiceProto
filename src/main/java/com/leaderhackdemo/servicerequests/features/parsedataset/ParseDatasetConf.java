package com.leaderhackdemo.servicerequests.features.parsedataset;

import com.leaderhackdemo.servicerequests.features.parsedataset.model.DataSetFiles;
import com.leaderhackdemo.servicerequests.features.parsedataset.service.EventParseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

//@EnableConfigurationProperties(value = DataSetFiles.class)
@Slf4j
public class ParseDatasetConf {

    @Autowired
    DataSetFiles dataPaths;

    @Autowired
    EventParseService eventParseService;

    @Bean
    public void prepareDataFiles(){
        if (dataPaths != null){
            dataPaths.getFiles().forEach(
                    path -> {
                        log.info("[DATASETS]: "+path.getPath());
                    }
            );
        }

        eventParseService.performSomeShit();
    }
}
