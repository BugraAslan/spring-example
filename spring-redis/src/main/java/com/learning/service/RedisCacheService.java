package com.learning.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheService {

    @Cacheable(cacheNames = "longRunningCache")
    public String longRunning() throws InterruptedException {
        Thread.sleep(5000L);
        return "run: long running";
    }

    @CacheEvict(cacheNames = "longRunningCache")
    public void clearLongRunningCache() {
        System.out.println("removed: longRunningCache");
    }

    public void clearCacheByName(String cacheName) {

    }
}
