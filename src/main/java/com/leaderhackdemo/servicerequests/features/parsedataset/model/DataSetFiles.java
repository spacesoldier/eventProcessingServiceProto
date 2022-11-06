package com.leaderhackdemo.servicerequests.features.parsedataset.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ConfigurationProperties(prefix = "internal-resources")
public class DataSetFiles {
    private List<DataFile> files;

    @Data
    public static class DataFile {
        private String name;
        private String path;
    }
}
