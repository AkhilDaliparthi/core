package com.core.util;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Log4j2
@Configuration
public class CacheUtil<T> {

    private RedisTemplate<String, T> redisTemplate;
    private HashOperations<String, String, T> hashOperations;
    private ValueOperations<String, T> keyValuePair;
    private ListOperations<String, T> listOperations;

    @Autowired
    CacheUtil(final RedisTemplate<String, T> template) {
        this.redisTemplate = template;
        this.hashOperations = redisTemplate.opsForHash();
        this.keyValuePair = redisTemplate.opsForValue();
        this.listOperations = redisTemplate.opsForList();
    }

    public void set(final @NonNull String key, final T value) {
        keyValuePair.set(key, value);
    }

    @Async
    public void setAsync(final @NonNull String key, final T value) {
        keyValuePair.set(key, value);
    }

    public void setList(final @NonNull String key, final List<T> value) {
        listOperations.leftPushAll(key, value);
    }

    public void setHashSet(final @NonNull String key, final Set<T> value) {
        listOperations.leftPushAll(key, value);
    }

    public void setList(final @NonNull String key, final List<T> value,
                        final @NonNull Long ttlSeconds) {
        listOperations.leftPushAll(key, value);
        redisTemplate.expire(key, ttlSeconds, TimeUnit.SECONDS);
    }

    public List<T> getList(final @NonNull String key) {
        return listOperations.range(key, 0, -1);
    }

    public void set(final @NonNull String key, final T value, final @NonNull Long ttlSeconds) {
        keyValuePair.set(key, value, ttlSeconds, TimeUnit.SECONDS);
    }

    public Object get(final @NonNull String key) {
        try {
            return keyValuePair.get(key);
        } catch (Exception ex) {
            log.error("Error while fetching data from redis: {}", ex);
        }
        return null;
    }

    public List<T> multiGet(final @NonNull List<String> key) {
        try {
            return keyValuePair.multiGet(key);
        } catch (Exception ex) {
            log.error("Error while fetching data from redis: {}", ex);
        }
        return null;
    }

    public void remove(final @NonNull String key) {
        redisTemplate.delete(key);
    }

    public void mapSet(final @NonNull String key, final Map map) {
        hashOperations.putAll(key, map);
    }

    public T mapGet(final @NonNull String key, final @NonNull Object field) {
        return hashOperations.get(key, field);
    }

    public Set<String> getAllHashKeys(final @NonNull String key) {
        return hashOperations.keys(key);
    }

    public boolean isPresent(final @NonNull String key, final @NonNull T type) {
        return hashOperations.hasKey(key, type);
    }

    public boolean hasKey(final @NonNull String key) {
        return redisTemplate.hasKey(key);
    }
}

