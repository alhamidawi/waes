package com.wearewaes.demo.configuration;

import com.wearewaes.demo.model.Data;
import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for simple in-memory storage (for sake of simplicity).
 *
 * @author Hani Al-Hamidawi
 */
@Configuration
public class SimpleStorage {

    @Bean
    @Profile("dev")
    public Map<Long, Data> storageDev() {
        return new HashMap<>();
    }

    @Bean
    @Profile("it")
    public Map<Long, Data> storageIt() {
        Map<Long, Data> storage = new HashMap<>();

        byte[] input = Base64.decodeBase64("SGVsbG9FdmVyeWJvZHlIaURvY3Rvck5pY2s=");
        byte[] right = Base64.decodeBase64("R29vZGJheVdvcmxk");
        byte[] left = Base64.decodeBase64("SGVsbG9Xb3JsZA==");
        byte[] leftSameSize = Base64.decodeBase64("SGVsbG8=");
        byte[] rightSameSize = Base64.decodeBase64("T2xsZWg=");
        byte[] leftAlone = Base64.decodeBase64("cm9jaw==");

        Data data1 = new Data();
        data1.setLeft(Base64.decodeBase64(input));
        data1.setRight(Base64.decodeBase64(input));

        Data data2 = new Data();
        data2.setRight(Base64.decodeBase64(right));
        data2.setLeft(Base64.decodeBase64(left));

        Data data3 = new Data();
        data3.setLeft(Base64.decodeBase64(leftSameSize));
        data3.setRight(Base64.decodeBase64(rightSameSize));

        Data data4 = new Data();
        data4.setLeft(leftAlone);

        storage.put(1L, data1);
        storage.put(2L, data2);
        storage.put(3L, data3);
        storage.put(4L, data4);

        return storage;

    }
}
