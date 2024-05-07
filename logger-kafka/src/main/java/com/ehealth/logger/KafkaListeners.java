package com.ehealth.logger;

import com.ehealth.logger.log.Log;
import com.ehealth.logger.log.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaListeners {

    private final LogRepository logRepository;

    @KafkaListener(topics = "ehealth", groupId = "groupId", containerGroup = "logFactory")
    void listener(Log logToBeSaved) {
        try {
            logRepository.save(logToBeSaved);
        } catch (DataAccessResourceFailureException e) {
            log.error("Elasticsearch error: {} ", e.getMessage());
        } catch (Exception e) {
            log.error("General error: {} ", e.getMessage());
        }
    }
}