package com.ehealth.logger.log;

import com.ehealth.logger.log.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/logs")
@RequiredArgsConstructor
public class LoggerController {

    private final LogService logService;

    @PostMapping
    public void publish(@RequestBody Log logRequest) {
        log.info("New log received: {}", logRequest);
        logService.publishOnKafka("ehealth", logRequest);
    }

    @GetMapping("/all")
    public List<Log> getLogs() {
        return logService.getAll();
    }

    @GetMapping
    public List<Log> getLogs(@RequestParam Optional<String> serviceName,
                             @RequestParam Optional<String> hostName,
                             @RequestParam Optional<String> level,
                             @RequestParam Optional<LocalDateTime> fromTimestamp,
                             @RequestParam Optional<LocalDateTime> toTimestamp,
                             @RequestParam Optional<String> traceId,
                             @RequestParam Optional<String> spanId) {
        return logService.searchLogs(serviceName, hostName, level, fromTimestamp, toTimestamp, traceId, spanId);
    }
}
