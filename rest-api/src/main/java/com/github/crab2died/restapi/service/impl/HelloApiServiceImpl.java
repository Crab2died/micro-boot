package com.github.crab2died.restapi.service.impl;

import com.github.crab2died.restapi.mapper.HelloApiMapper;
import com.github.crab2died.restapi.pojo.HelloApi;
import com.github.crab2died.restapi.service.HelloApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class HelloApiServiceImpl implements HelloApiService {

    @Autowired
    private HelloApiMapper helloApiMapper;

    @Transactional
    @Override
    public void insertHello(HelloApi helloApi) {
        helloApi.setId(UUID.randomUUID().toString());
        helloApiMapper.insert(helloApi);
    }

    @Override
    public HelloApi getHello(String id) {
        return helloApiMapper.getHello(id);
    }
}
