package com.ehealth.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogRequestDTO implements Serializable {
    private String serviceName; // Name of the microservice from which the log originated
    private String hostName; // Host machine name
    private String message;
    private String level; // Log level like INFO, WARN, ERROR
    private LocalDateTime timestamp;
    private String traceId; // Unique identifier for tracing a request across services
    private String spanId; // Identifier for a specific span within a trace
}
