package com.learning.controller;

import com.learning.service.RedisCacheService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedisCacheService redisCacheService;

    public RedisController(RedisCacheService redisCacheService) {
        this.redisCacheService = redisCacheService;
    }

    @GetMapping
    public String cacheControl() {
        try {
            return redisCacheService.longRunning();
        } catch (InterruptedException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/clear")
    public String clearCache() {
        redisCacheService.clearLongRunningCache();
        return "Clear longRunning Cache";
    }
}
