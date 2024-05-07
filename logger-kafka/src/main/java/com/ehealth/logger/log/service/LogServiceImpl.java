package com.ehealth.logger.log.service;

import com.ehealth.logger.log.Log;
import com.ehealth.logger.log.repository.LogCustomRepository;
import com.ehealth.logger.log.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogCustomRepository logCustomRepository;
    private final KafkaTemplate<String, Log> kafkaTemplate;
    private final LogRepository logRepository;

    @Override
    public void publishOnKafka(String ehealth, Log logRequest) {
        logRequest.setId(UUID.randomUUID().toString());
        kafkaTemplate.send("ehealth", logRequest);
    }

    @Override
    public List<Log> searchLogs(Optional<String> serviceName,
                                Optional<String> hostName,
                                Optional<String> level,
                                Optional<LocalDateTime> fromTimestamp,
                                Optional<LocalDateTime> toTimestamp,
                                Optional<String> traceId,
                                Optional<String> spanId) {
        return logCustomRepository.searchLogs(serviceName, hostName, level, fromTimestamp, toTimestamp, traceId, spanId);
    }

    @Override
    public List<Log> getAll() {
        Iterable<Log> allLogs = logRepository.findAll();

        // Create a new ArrayList to store the logs
        List<Log> logList = new ArrayList<>();

        // Add each log to the list
        allLogs.forEach(logList::add);

        return logList;
    }
}
