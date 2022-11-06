package com.leaderhackdemo.servicerequests.features.parsedataset.service;

import com.leaderhackdemo.servicerequests.features.parsedataset.model.data.ServiceEventRaw;

public interface EventParseService {
    ServiceEventRaw parseEventFromString(String eventStr);

    void performSomeShit();
}
