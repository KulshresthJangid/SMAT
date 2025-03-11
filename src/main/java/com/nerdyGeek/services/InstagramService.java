package com.nerdyGeek.services;

import com.nerdyGeek.documents.InstagramAnalytics;
import com.nerdyGeek.elastisearch.repositories.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        return (List<InstagramAnalytics>) analyticsRepository.findAll();
    }

    public InstagramAnalytics getAnalyticsById(String id) {
        return analyticsRepository.findById(id).orElse(null);
    }

    public void deleteAnalytics(String id) {
        analyticsRepository.deleteById(id);
    }
}
