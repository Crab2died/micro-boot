package com.github.crab2died.restapi;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RestApiApplication {

	public static void main(String[] args) {

		//SpringApplication.run(RestApiApplication.class, args);
        new SpringApplicationBuilder(RestApiApplication.class)
                .properties("spring.config.location=classpath:/application.yaml")
                .profiles("")
                .run(args);
	}
}
