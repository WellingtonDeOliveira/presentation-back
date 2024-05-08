package br.com.base.configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI openAPI() {
        var openAPI = new OpenAPI();
        addInfo(openAPI);
        addJWTSecurity(openAPI);
        return openAPI;
    }

    private void addInfo(OpenAPI openAPI) {
        openAPI.info(
                new Info()
                        .title("Base Project API")
                        .version("0.1")
                        .description("This API exposes endpoints to Base Project API.")
        );
    }

    private void addJWTSecurity(OpenAPI openAPI) {
        final String securitySchemeName = "Bearer Authentication";
        openAPI.addSecurityItem(
                        new SecurityRequirement().addList(securitySchemeName)
                )
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                                .description("description")
                                )
                );
    }
}
