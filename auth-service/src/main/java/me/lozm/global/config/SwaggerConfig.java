package me.lozm.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(
                new Info().title("Auth Service Swagger")
                        .description("documentation-backend > auth service > REST API Document"));
    }

    @Bean
    public GroupedOpenApi apiV1() {
        return GroupedOpenApi.builder()
                .group("v1.0.0")
//                .pathsToMatch("/**")
//                .pathsToExclude("/test/**")
                .addOpenApiCustomiser(buildSecurityOpenApi())
                .build();
    }

    private OpenApiCustomiser buildSecurityOpenApi() {
        SecurityScheme securityScheme = new SecurityScheme()
                .name("Authorization")
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .bearerFormat("JWT")
                .scheme("bearer");

        return OpenApi -> OpenApi
                .addSecurityItem(new SecurityRequirement().addList("jwt token"))
                .getComponents()
                .addSecuritySchemes("jwt token", securityScheme);
    }

}
