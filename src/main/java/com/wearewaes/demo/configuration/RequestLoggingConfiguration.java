package com.wearewaes.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;


/**
 * Configuration class for {@link CommonsRequestLoggingFilter}, filter that writes the request URI
 * (and optionally the query string) to the logs.
 *
 * @author Hani Al-Hamidawi
 */
@Configuration
public class RequestLoggingConfiguration {

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(true);
        filter.setAfterMessagePrefix("REQUEST DATA : ");
        return filter;
    }
}
