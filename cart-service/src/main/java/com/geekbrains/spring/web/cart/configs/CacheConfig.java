package com.geekbrains.spring.web.cart.configs;

import com.geekbrains.spring.web.cart.properties.CacheProps;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Configuration
@EnableCaching
@EnableRedisRepositories
@EnableConfigurationProperties(
        CacheProps.class
)
@RequiredArgsConstructor
public class CacheConfig {

    private final CacheProps cacheProps;

    @Value("${spring.cache.default.expire-time}")
    private int defaultExpireTime;

//    @Value("${spring.cache.user.expire-time}")
//    private int userCacheExpireTime;
//    @Value("${spring.cache.user.name}")
//    private String userCacheName;

    @Bean(name = "com/geekbrains/spring/web/cart/test")
    public CacheManager cacheManager(RedisConnectionFactory lettuceConnectionFactory) {
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
        // Устанавливаем время истечения срока действия кеша по умолчанию, управляемое менеджером кеша
        defaultCacheConfig = defaultCacheConfig.entryTtl(Duration.ofSeconds(defaultExpireTime))
                // Устанавливаем ключ для сериализации строки
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // Устанавливаем значение для сериализации json
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        // Не кэшировать пустые значения
        // .disableCachingNullValues();

        Set<String> cacheNames = new HashSet<>();
        cacheNames.add(cacheProps.getName());

        // Применяем разные конфигурации к каждому кеш-пространству
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        configMap.put(cacheProps.getName(), defaultCacheConfig.entryTtl(Duration.ofSeconds(cacheProps.getExpireTime())));

        RedisCacheManager cacheManager = RedisCacheManager.builder(lettuceConnectionFactory)
                .cacheDefaults(defaultCacheConfig)
                .initialCacheNames(cacheNames)
                .withInitialCacheConfigurations(configMap)
                .build();
        return cacheManager;
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }



}
