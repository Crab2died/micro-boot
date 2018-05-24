package com.github.crab2died;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan
@ServletComponentScan
@MapperScan("com.github.crab2died.restapi.mapper")
public class RestApiApplication {

	public static void main(String[] args) {

		//SpringApplication.run(RestApiApplication.class, args);
        new SpringApplicationBuilder(RestApiApplication.class)
                .properties("spring.config.location=classpath:/application.yaml")
                //.profiles("")
                .run(args);
	}
}
