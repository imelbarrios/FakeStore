package com.store.fake.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo(){
        return new Info()
                .title("FakeStore")
                .license(apiLicense());
    }

    private License apiLicense(){
        return new License().name("MIT License").url("https://opensource.org/licenses");
    }
}
