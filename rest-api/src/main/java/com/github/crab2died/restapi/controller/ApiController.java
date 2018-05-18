package com.github.crab2died.restapi.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

@Api
@Controller
public class ApiController {

    @ApiIgnore
    @GetMapping("/")
    public String apiHome() {

        return "redirect:swagger-ui.html";
    }

    @GetMapping("overview")
    @ResponseBody
    public Object getOverview() {
        return System.getProperties();
    }
}
