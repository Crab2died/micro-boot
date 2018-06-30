package com.github.crab2died.restapi.controller;

import com.github.crab2died.restapi.pojo.HelloApi;
import com.github.crab2died.restapi.service.HelloApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

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
    @ApiOperation(value = "Get Overview")
    public Object getOverview() {
        return System.getProperties();
    }

    @PostMapping("hello")
    @ResponseBody
    @ApiOperation(value = "Say Hello")
    public ResponseEntity<Void> sayHello(@RequestBody @ApiParam(value = "Hello Api Body", name = "helloApi") HelloApi helloApi) {
        helloApiService.insertHello(helloApi);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/hello/{id}")
    @ResponseBody
    @ApiOperation(value = "Hello")
    public ResponseEntity<HelloApi> hello(@PathVariable @ApiParam(value = "ID.", name = "id") String id) {

        // id 424f5528-1e4b-4b58-b1c6-0171b1cd2220
        HttpHeaders headers = new HttpHeaders();
        headers.add("ETag", "341567381");

        headers.add("Last-Modified", "Fri, 29 Jun 2018 08:10:59 GMT");
        headers.add("Cache-Control", "max-age=5");
        return new ResponseEntity<>(helloApiService.getHello(id), headers, HttpStatus.OK);
    }
}
