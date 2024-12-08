package com.portfolio.server.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        Server server = new Server();
        server.setUrl("https://2hoddy.com");

        return new OpenAPI()
                .servers(List.of(server))
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo(){

        return new Info()
                .title("Portfolio Rest Api Documentation")
                .description("Hoddy Portfolio API 문서입니다.")
                .version("1.0.0");
    }
}
