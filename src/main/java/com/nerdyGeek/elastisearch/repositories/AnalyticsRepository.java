package com.nerdyGeek.elastisearch.repositories;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.nerdyGeek.documents.InstagramAnalytics;
import org.springframework.stereotype.Repository;


public interface AnalyticsRepository extends ElasticsearchRepository<InstagramAnalytics, String> {
	List<InstagramAnalytics> findByCategory(String category);
}
