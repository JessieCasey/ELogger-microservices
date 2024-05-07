package com.ehealth.system;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final RestTemplate restTemplate;

    @PostMapping
    public void registerLog() {
        LogRequestDTO logRequestDTO = LogRequestDTO.builder()
                .serviceName("payments")
                .hostName("host-1")
                .message("Order processing started")
                .level("INFO")
                .timestamp(LocalDateTime.now())
                .traceId("abcd1234efgh5678")
                .spanId("ijkl9012mnop3456")
                .build();

        var fraudCheckResponse = restTemplate.postForEntity(
                "http://LOGGER/api/logs",
                logRequestDTO,
                Void.class
        );
    }
}
