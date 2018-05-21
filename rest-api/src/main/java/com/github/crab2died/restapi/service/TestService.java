package com.github.crab2died.restapi.service;

import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

public interface TestService {

    @Cacheable(value = "test#30#1", key = "'redis-test'")
    Map<String, String> redisTest();
}
