package com.nerdyGeek.elastisearch.repositories;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.nerdyGeek.documents.InstagramAnalytics;

public interface AnalyticsRepository extends ElasticsearchRepository<InstagramAnalytics, String> {
	List<InstagramAnalytics> findByCategory(String category);
}
