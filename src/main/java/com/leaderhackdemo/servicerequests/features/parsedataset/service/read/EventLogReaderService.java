package com.leaderhackdemo.servicerequests.features.parsedataset.service.read;

import com.leaderhackdemo.servicerequests.features.parsedataset.model.DataSetFiles;
import com.leaderhackdemo.servicerequests.features.parsedataset.model.data.ServiceEventRaw;
import com.leaderhackdemo.servicerequests.features.parsedataset.service.model.ParseEventLogRequest;
import com.leaderhackdemo.servicerequests.features.parsedataset.service.model.ReadEventLogRequest;
import reactor.core.publisher.Flux;

public interface EventLogReaderService {

    Flux<ParseEventLogRequest> readEventLog(DataSetFiles.DataFile fileDescriptor);
}
