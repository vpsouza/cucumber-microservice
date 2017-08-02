package com.buscapecompany.poc.cucumbermicroservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(path -> path.indexOf("error") == -1)
                //.apis(RequestHandlerSelectors.basePackage("com.buscapecompany.poc.cucumbermicroservice.*"))
                .build()
                .forCodeGeneration(true)
                .apiInfo(metaData());
    }
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot REST API",
                "Spring Boot REST API for Cucumber Microservice with Karate",
                "1.0",
                "Terms of service",
                new Contact("Vinicius Piedade", "http://www.buscapecompany.com", "vpiedade@avenuecode.com"),
               "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}