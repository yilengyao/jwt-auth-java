package io.github.yilengyao.jwtauth.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import java.util.Collections;

import static io.github.innobridge.security.constants.HTTPConstants.ACCESS_TOKEN;
import static io.github.innobridge.security.constants.HTTPConstants.BEARER_ACCESS_TOKEN_SCHEMA;
import static io.github.innobridge.security.constants.HTTPConstants.BEARER_ACCESS_TOKEN_FORMAT;
import static io.github.innobridge.security.constants.HTTPConstants.BEARER;

@Configuration
public class OpenApiConfig implements WebMvcConfigurer {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
            .info(new Info()
                .title("Employee API")
                .version("1.0")
                .description("API for managing employees"))
            .components(new Components()
                    .addSecuritySchemes(BEARER_ACCESS_TOKEN_SCHEMA, new SecurityScheme()
                            .name(ACCESS_TOKEN)
                            .type(SecurityScheme.Type.HTTP)
                            .bearerFormat(BEARER_ACCESS_TOKEN_FORMAT)
                            .in(SecurityScheme.In.HEADER)
                            .scheme(BEARER)))
            .security(Collections.singletonList(new SecurityRequirement().addList(BEARER_ACCESS_TOKEN_SCHEMA)));
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
  }
}
