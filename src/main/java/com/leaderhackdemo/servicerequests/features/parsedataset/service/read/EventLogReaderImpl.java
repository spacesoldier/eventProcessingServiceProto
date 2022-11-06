package com.leaderhackdemo.servicerequests.features.parsedataset.service.read;

import com.leaderhackdemo.servicerequests.features.parsedataset.model.DataSetFiles;
import com.leaderhackdemo.servicerequests.features.parsedataset.model.data.ServiceEventRaw;
import com.leaderhackdemo.servicerequests.features.parsedataset.service.model.ParseEventLogRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import javax.validation.constraints.Max;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.BaseStream;

@Service
@Slf4j
public class EventLogReaderImpl implements EventLogReaderService {

    @Override
    public Flux<ParseEventLogRequest> readEventLog(DataSetFiles.DataFile fileDescriptor) {

        Flux output = null;

        if (fileDescriptor != null){

            String pathToFileStr = fileDescriptor.getPath().replaceAll("/","\\\\");
            Path pathToFile = Paths.get(pathToFileStr);

            Flux<String> eventLogStream = Flux.using(
                    () -> Files.lines( pathToFile ),
                    Flux::fromStream,
                    BaseStream::close
            );

            return eventLogStream.map(
                                        record -> ParseEventLogRequest.builder()
                                                                        .eventLogRecord(record)
                                                                    .build()
                                    );
        }

        if (output == null){
            output = Flux.fromIterable(List.of("Cannot read file", "giving up"));
        }

        return output;
    }
}
