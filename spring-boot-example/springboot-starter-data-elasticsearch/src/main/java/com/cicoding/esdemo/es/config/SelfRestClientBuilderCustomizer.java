package com.cicoding.esdemo.es.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.boot.autoconfigure.elasticsearch.RestClientBuilderCustomizer;
import org.springframework.stereotype.Component;

/**
 * @author cidoing
 */
@Component
public class SelfRestClientBuilderCustomizer implements RestClientBuilderCustomizer {
    @Override
    public void customize(HttpAsyncClientBuilder builder) {

    }

    @Override
    public void customize(RequestConfig.Builder builder) {

    }

    @Override
    public void customize(RestClientBuilder builder) {

    }

}
