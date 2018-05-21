package com.github.crab2died.restapi.service.impl;

import com.github.crab2died.restapi.service.TestService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public Map<String, String> redisTest() {
        Map<String, String> map = new HashMap<>();
        map.put("test1", "test1");
        map.put("test2", "test2");
        return map;
    }
}
