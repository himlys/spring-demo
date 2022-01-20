package com.asianfo.springsamples.freemarker.cache.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "asiainfo")
@Data
public class AsiainfoCacheProperties {
    private List<RedisCacheConfig> cache = new ArrayList<>();
    @Data
    static class RedisCacheConfig {
        private String name;
        private long ttl;
        private boolean cacheNullValues = true;
        private boolean usePrefix = true;
    }
}
