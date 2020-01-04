package com.hu.blogback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket(Environment environment) {

        Profiles profiles = Profiles.of("dev");

        boolean isDev = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //.enable(isDev)
                .enable(false)
                .groupName("开发环境")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hu.blogback.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {

        Contact contact = new Contact("胡鹏成", "", "");

        return new ApiInfo(
                "个人博客API",
                "这是个人博客的后端API，方便后续升级",
                "1.0",
                "",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
