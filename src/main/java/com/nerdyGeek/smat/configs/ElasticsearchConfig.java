package com.nerdyGeek.smat.configs;

import java.util.Base64;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.nerdyGeek.smat.elasticsearch")
public class ElasticsearchConfig {

    @Value("${spring.elasticsearch.uris}")
    private String elasticsearchUri;

    @Value("${spring.elasticsearch.username}")
    private String elasticsearchUsername;

    @Value("${spring.elasticsearch.password}")
    private String elasticsearchPassword;

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        String auth = elasticsearchUsername + ":" + elasticsearchPassword;
        String encodedAuth = Base64.getEncoder()
                .encodeToString(auth.getBytes());
        Header authHeader = new BasicHeader("Authorization",
                "Basic " + encodedAuth);

        HttpHost host = HttpHost.create(elasticsearchUri);

        RestClient restClient = RestClient.builder(host)
                .setDefaultHeaders(new Header[] { authHeader }).build();

        RestClientTransport transport = new RestClientTransport(restClient,
                new JacksonJsonpMapper());

        return new ElasticsearchClient(transport);
    }
}
