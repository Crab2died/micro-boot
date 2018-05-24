package com.github.crab2died.restapi.mapper;

import com.github.crab2died.restapi.pojo.HelloApi;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

public interface HelloApiMapper {

    void insert(@Param("helloApi") HelloApi helloApi);

    @Cacheable(value = "HELLO_API", key = "#p0")
    HelloApi getHello(@Param("id") String id);
}
