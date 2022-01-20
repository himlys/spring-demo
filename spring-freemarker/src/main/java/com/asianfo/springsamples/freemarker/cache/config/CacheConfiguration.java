package com.asianfo.springsamples.freemarker.cache.config;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableCaching
@EnableConfigurationProperties({CacheProperties.class, AsiainfoCacheProperties.class})
public class CacheConfiguration {

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer(AsiainfoCacheProperties asiainfoCacheProperties) {
        return c -> {
            // 初始化缓存
            c.withInitialCacheConfigurations(getRedisCacheConfigurations(asiainfoCacheProperties));
            // 禁用没有配置的缓存，没有配置的缓存不自动创建
            c.disableCreateOnMissingCache();
        };
    }
    // 获取缓存配置信息。没有使用原版的，主要是要使用ttl。
    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurations(AsiainfoCacheProperties asiainfoCacheProperties) {
        Map<String, RedisCacheConfiguration> map = new HashMap<>();
        asiainfoCacheProperties.getCache().stream().forEach(redisCacheConfig -> {
            String name = redisCacheConfig.getName();
            RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                    .entryTtl(Duration.of(redisCacheConfig.getTtl(), ChronoUnit.SECONDS));
            map.put(name,redisCacheConfiguration);
        });
        return map;
    }

    @Bean
    @ConditionalOnMissingBean
    public CacheManagerCustomizers cacheManagerCustomizers(ObjectProvider<CacheManagerCustomizer<?>> customizers) {
        return new CacheManagerCustomizers(customizers.orderedStream().collect(Collectors.toList()));
    }

    @Bean
    public CachingConfigurer cachingConfigurer(RedisCacheManager redisCacheManager) {
        CachingConfigurer cachingConfigurer = new CachingConfigurerSupport() {
            @Override
            public CacheManager cacheManager() {
                System.out.println("走到自定义CachingConfigurer");
                return redisCacheManager;
            }
        };
        return cachingConfigurer;
    }

    @Configuration
    class LocalSimpleCacheConfiguration {

        @Bean("concurrentMapCacheManager")
        ConcurrentMapCacheManager cacheManager(CacheProperties cacheProperties,
                                               CacheManagerCustomizers cacheManagerCustomizers) {
            ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
            List<String> cacheNames = cacheProperties.getCacheNames();
            if (!cacheNames.isEmpty()) {
                cacheManager.setCacheNames(cacheNames);
            }
            return cacheManagerCustomizers.customize(cacheManager);
        }

    }

    @Configuration
    class LocalRedisCacheConfiguration {

        @Bean("redisCacheManager")
        RedisCacheManager cacheManager(CacheProperties cacheProperties, CacheManagerCustomizers cacheManagerCustomizers,
                                       ObjectProvider<RedisCacheConfiguration> redisCacheConfiguration,
                                       ObjectProvider<RedisCacheManagerBuilderCustomizer> redisCacheManagerBuilderCustomizers,
                                       RedisConnectionFactory redisConnectionFactory, ResourceLoader resourceLoader) {
            RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(
                    determineConfiguration(cacheProperties, redisCacheConfiguration, resourceLoader.getClassLoader()));
            List<String> cacheNames = cacheProperties.getCacheNames();
            if (!cacheNames.isEmpty()) {
                builder.initialCacheNames(new LinkedHashSet<>(cacheNames));
            }
            if (cacheProperties.getRedis().isEnableStatistics()) {
                builder.enableStatistics();
            }
            redisCacheManagerBuilderCustomizers.orderedStream().forEach((customizer) -> customizer.customize(builder));
            return cacheManagerCustomizers.customize(builder.build());
        }

        private RedisCacheConfiguration determineConfiguration(
                CacheProperties cacheProperties,
                ObjectProvider<RedisCacheConfiguration> redisCacheConfiguration,
                ClassLoader classLoader) {
            return redisCacheConfiguration.getIfAvailable(() -> createConfiguration(cacheProperties, classLoader));
        }

        private RedisCacheConfiguration createConfiguration(
                CacheProperties cacheProperties, ClassLoader classLoader) {
            CacheProperties.Redis redisProperties = cacheProperties.getRedis();
            RedisCacheConfiguration config = RedisCacheConfiguration
                    .defaultCacheConfig();
            config = config.serializeValuesWith(
                    RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer(classLoader)));
            if (redisProperties.getTimeToLive() != null) {
                config = config.entryTtl(redisProperties.getTimeToLive());
            }
            if (redisProperties.getKeyPrefix() != null) {
                config = config.prefixCacheNameWith(redisProperties.getKeyPrefix());
            }
            if (!redisProperties.isCacheNullValues()) {
                config = config.disableCachingNullValues();
            }
            if (!redisProperties.isUseKeyPrefix()) {
                config = config.disableKeyPrefix();
            }
            return config;
        }

    }
}
