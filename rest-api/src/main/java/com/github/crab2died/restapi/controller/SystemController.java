package com.github.crab2died.restapi.controller;


import com.github.crab2died.restapi.pojo.HelloApi;
import com.github.crab2died.restapi.service.TestService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "System Info", tags = {"System Info",})
public class SystemController {

    @Autowired
    public TestService testService;

    @Autowired
    public ApplicationContext ctx;

    @RequestMapping(value = "/sys/hello", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,},
            consumes = {MediaType.ALL_VALUE,}
    )
    @ResponseBody
    @ApiOperation(value = "Say Hello", notes = "hello note", httpMethod = "GET")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(value = "Your Name", name = "name", paramType = "query", defaultValue = "Crab2Died", dataTypeClass = String.class),
            @ApiImplicitParam(value = "Say Hello Pls.", name = "sayHello", paramType = "query", defaultValue = "Hello, Friend", dataType = "string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "success request", response = HelloApi.class),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "bad request")
    })
    public ResponseEntity<HelloApi> sayHello(@ApiParam(value = "Hello Model", name = "hello api") HelloApi helloApi) {
        if (null == helloApi) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(helloApi, HttpStatus.OK);
    }

    @GetMapping("/sys/ctx")
    @ResponseBody
    @ApiOperation(value = "App Context")
    public Object getAppContext() {

        testService.redisTest();
        return ctx.getBeanDefinitionNames();
    }


}
