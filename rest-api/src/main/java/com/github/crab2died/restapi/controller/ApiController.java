package com.github.crab2died.restapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "API Home", tags = {"API Home"})
@Controller
public class ApiController {

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
}
