package com.nerdyGeek.rest.controller;

import com.nerdyGeek.documents.InstagramAnalytics;
import com.nerdyGeek.elastisearch.repositories.AnalyticsRepository;
import com.nerdyGeek.services.InstagramService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {
    private final InstagramService instagramService;


    public AnalyticsController(InstagramService instagramService) {
        this.instagramService = instagramService;
    }

    @PostMapping
    public InstagramAnalytics createProduct(@RequestBody InstagramAnalytics instagramAnalytics) {
        return instagramService.saveAnalytics(instagramAnalytics);
    }

    @GetMapping
    public List<InstagramAnalytics> getAllAnalytics() {
        return instagramService.getAllInstagramAnalytics();
    }

    @GetMapping("/{id}")
    public InstagramAnalytics getAnalyticsById(@PathVariable String id) {
        return instagramService.getAnalyticsById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAnalytics(@PathVariable String id) {
        instagramService.deleteAnalytics(id);
    }
}
