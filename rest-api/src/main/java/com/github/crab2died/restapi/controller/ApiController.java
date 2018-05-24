package com.github.crab2died.restapi.controller;

import com.github.crab2died.restapi.pojo.HelloApi;
import com.github.crab2died.restapi.service.HelloApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "API Home", tags = {"API Home"})
@Controller
public class ApiController {

    @Autowired
    private HelloApiService helloApiService;

    @ApiIgnore
    @GetMapping("/")
    @ApiOperation(value = "Api Home")
    public String apiHome() {

        return "redirect:swagger-ui.html";
    }

    @GetMapping("overview")
    @ResponseBody
    public Object getOverview() {
        return System.getProperties();
    }

    @PostMapping("say-hello")
    @ResponseBody
    public ResponseEntity<Void> sayHello(@RequestBody HelloApi helloApi) {
        helloApiService.insertHello(helloApi);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/hello/{id}")
    @ResponseBody
    public ResponseEntity<HelloApi> hello(@PathVariable String id) {
        return new ResponseEntity<>(helloApiService.getHello(id), HttpStatus.OK);
    }
}
