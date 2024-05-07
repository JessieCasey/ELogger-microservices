package com.ehealth.logger.log.repository;

import com.ehealth.logger.log.Log;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LogCustomRepositoryImpl implements LogCustomRepository {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public List<Log> searchLogs(Optional<String> serviceName,
                                Optional<String> hostName,
                                Optional<String> level,
                                Optional<LocalDateTime> fromTimestamp,
                                Optional<LocalDateTime> toTimestamp,
                                Optional<String> traceId,
                                Optional<String> spanId) {
        BoolQueryBuilder query = QueryBuilders.boolQuery();

        serviceName.ifPresent(s -> query.must(QueryBuilders.termQuery("serviceName", s)));
        hostName.ifPresent(h -> query.must(QueryBuilders.termQuery("hostName", h)));
        level.ifPresent(l -> query.must(QueryBuilders.termQuery("level", l)));
        fromTimestamp.ifPresent(ft -> query.must(QueryBuilders.rangeQuery("timestamp").gte(ft)));
        toTimestamp.ifPresent(tt -> query.must(QueryBuilders.rangeQuery("timestamp").lte(tt)));
        traceId.ifPresent(t -> query.must(QueryBuilders.termQuery("traceId", t)));
        spanId.ifPresent(s -> query.must(QueryBuilders.termQuery("spanId", s)));

        NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder().withQuery(query);
        SearchHits<Log> searchHits = elasticsearchRestTemplate.search(searchQuery.build(), Log.class);

        return searchHits.stream().map(SearchHit::getContent).toList();
    }
}