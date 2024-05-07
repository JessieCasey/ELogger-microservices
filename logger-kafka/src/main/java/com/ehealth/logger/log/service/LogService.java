package com.ehealth.logger.log.service;

import com.ehealth.logger.log.Log;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LogService {
    List<Log> searchLogs(Optional<String> serviceName,
                         Optional<String> hostName,
                         Optional<String> level,
                         Optional<LocalDateTime> fromTimestamp,
                         Optional<LocalDateTime> toTimestamp,
                         Optional<String> traceId,
                         Optional<String> spanId);

    void publishOnKafka(String ehealth, Log logRequest);

    List<Log> getAll();

}
