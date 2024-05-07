package com.ehealth.logger.log.repository;

import com.ehealth.logger.log.Log;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LogCustomRepository {
    List<Log> searchLogs(Optional<String> serviceName,
                         Optional<String> hostName,
                         Optional<String> level,
                         Optional<LocalDateTime> fromTimestamp,
                         Optional<LocalDateTime> toTimestamp,
                         Optional<String> traceId,
                         Optional<String> spanId);
}
