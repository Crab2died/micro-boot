package com.github.crab2died.restapi.controller;


import com.github.crab2died.restapi.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "System Info", tags = {"System Info",})
@RestController
public class SystemController {

    @Autowired
    public TestService testService;

    @Autowired
    public ApplicationContext ctx;

    @GetMapping("/sys/ctx")
    @ResponseBody
    @ApiOperation(value = "App Context", tags = {"App Context",})
    public Object getAppContext(){

        testService.redisTest();
        return ctx.getBeanDefinitionNames();
    }
}
