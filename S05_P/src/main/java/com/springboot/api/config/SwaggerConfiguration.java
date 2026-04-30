package com.springboot.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().components(new Components()).info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Spring Boot Open API Test with Swagger V1")
                .description("설명 부분")
                .version("1.0.0");
    }
}

// @Operation: API 설명
// @Parameter: 매개변수에 대한 설명 및 설정을 위한 애노테이션, 메서드의 매개변수 뿐 아니라 DTO의 필드에도 사용할 수 있음