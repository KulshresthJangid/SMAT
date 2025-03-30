package com.nerdyGeek.smat.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nerdyGeek.smat.documents.InstagramAnalytics;
import com.nerdyGeek.smat.elastisearch.repositories.AnalyticsRepository;

@Service
public class InstagramService {
	private final AnalyticsRepository analyticsRepository;

	public InstagramService(AnalyticsRepository analyticsRepository) {
		this.analyticsRepository = analyticsRepository;
	}

	public InstagramAnalytics saveAnalytics(InstagramAnalytics instagramAnalytics) {
		return analyticsRepository.save(instagramAnalytics);
	}

	public List<InstagramAnalytics> getAllInstagramAnalytics() {
		Iterable<InstagramAnalytics> analytics = analyticsRepository.findAll();
		List<InstagramAnalytics> target = new ArrayList<>();
		analytics.forEach(target::add);
		return target;
	}

	public InstagramAnalytics getAnalyticsById(String id) {
		return analyticsRepository.findById(id).orElse(null);
	}

	public void deleteAnalytics(String id) {
		analyticsRepository.deleteById(id);
	}
}
