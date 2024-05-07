package com.ehealth.logger.log.repository;

import com.ehealth.logger.log.Log;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogRepository extends ElasticsearchRepository<Log, String> {
}