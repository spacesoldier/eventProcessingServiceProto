package com.leaderhackdemo.servicerequests.features.parsedataset.model.manipulation;

import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Method;

@Data @Builder
public class DataFieldAccessor {
    private Class fieldType;
    private Method getter;
    private Method setter;
}
