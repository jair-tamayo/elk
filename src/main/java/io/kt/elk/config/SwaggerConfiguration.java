package io.kt.elk.config;

import io.kt.elk.rest.constant.ApplicationHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ParameterType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

@Configuration
class SwaggerConfiguration {

    @Bean
    Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.kt.elk.rest.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(List.of(
                        new RequestParameterBuilder()
                                .in(ParameterType.HEADER)
                                .required(false)
                                .name(ApplicationHeaders.CORRELATION_HEADER)
                                .build()
                ));
    }

}
