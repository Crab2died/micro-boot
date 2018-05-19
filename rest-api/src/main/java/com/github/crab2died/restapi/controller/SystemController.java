package com.github.crab2died.restapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {

    @Autowired
    public ApplicationContext ctx;

    @GetMapping("/sys/ctx")
    @ResponseBody
    public Object getAppContext(){

        return ctx.getBeanDefinitionNames();
    }
}
