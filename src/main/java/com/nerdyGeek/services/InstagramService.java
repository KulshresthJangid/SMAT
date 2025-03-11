package com.nerdyGeek.services;

import com.nerdyGeek.documents.InstagramAnalytics;
import com.nerdyGeek.elastisearch.repositories.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InstagramService {

//    @Autowired
//    private AnalyticsRepository analyticsRepo;
//
//    public void save(InstagramAnalytics analyticsDoc) {
//        analyticsRepo.save(analyticsDoc);
//    }

    private String type;
    private String label;
    private String operator;
    private String value;
    private ArrayList<InstagramService> conditions;
}
