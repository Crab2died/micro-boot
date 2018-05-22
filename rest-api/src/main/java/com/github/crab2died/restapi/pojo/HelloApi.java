package com.github.crab2died.restapi.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Hello API Model", description = "Hello api model", parent = Object.class)
public class HelloApi {

    @ApiModelProperty(
            value = "Your Name", name = "name", dataType = "string",
            notes = "pls. write your name", example = "Crab2Died"
    )
    private String name;

    @ApiModelProperty(
            value = "Say Hello", name = "sayHello", dataType = "string",
            notes = "pls. write your greeting", example = "Hello, Boy"
    )
    private String sayHello;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSayHello() {
        return sayHello;
    }

    public void setSayHello(String sayHello) {
        this.sayHello = sayHello;
    }
}
