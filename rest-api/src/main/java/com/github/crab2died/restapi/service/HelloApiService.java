package com.github.crab2died.restapi.service;

import com.github.crab2died.restapi.pojo.HelloApi;

public interface HelloApiService {

    void insertHello(HelloApi helloApi);

    HelloApi getHello(String id);
}
