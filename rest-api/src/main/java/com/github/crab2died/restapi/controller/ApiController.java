package com.github.crab2died.restapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class ApiController {

    @GetMapping("/")
    public String apiHome(){

        return "redirect:swagger-ui.html";
    }
}
