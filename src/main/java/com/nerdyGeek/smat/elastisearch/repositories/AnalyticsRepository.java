package com.nerdyGeek.smat.elastisearch.repositories;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import com.nerdyGeek.smat.documents.InstagramAnalytics;

@EnableElasticsearchRepositories
public interface AnalyticsRepository extends ElasticsearchRepository<InstagramAnalytics, String> {
	List<InstagramAnalytics> findByCategory(String category);
}
