package com.leaderhackdemo.servicerequests.features.parsedataset.service.parsing;

import com.leaderhackdemo.servicerequests.features.parsedataset.model.DataSetFiles;
import com.leaderhackdemo.servicerequests.features.parsedataset.model.data.ServiceEventRaw;

public interface EventParseService {
    ServiceEventRaw parseEventFromString(String eventStr, String partsDelimiter);
}
