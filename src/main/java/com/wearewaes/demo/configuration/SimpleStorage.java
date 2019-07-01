package com.wearewaes.demo.configuration;

import com.wearewaes.demo.model.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SimpleStorage {

    @Bean
    public Map<Long, Data> storage() {
        return new HashMap<>();
    }
}
