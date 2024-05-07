package com.ehealth.logger.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "log")
public class Log {

    @Id
    private String id;
    private String serviceName; // Name of the microservice from which the log originated
    private String hostName; // Host machine name
    private String message;
    private String level; // Log level like INFO, WARN, ERROR

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime timestamp;

    private String traceId; // Unique identifier for tracing a request across services
    private String spanId; // Identifier for a specific span within a trace
}