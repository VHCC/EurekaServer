package com.frank.spring.eureka.server.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @Author IChen.Chu
 * @Date 2023/03/17
 */
@Configuration
public class ErrorPageConfig {

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                ErrorPage page404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
                ErrorPage page500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");
                factory.addErrorPages(page404, page500);
            }
        };
    }

}
