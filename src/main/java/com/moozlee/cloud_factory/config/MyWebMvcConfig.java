package com.moozlee.cloud_factory.config;

import com.moozlee.cloud_factory.annotation.ApiRestController;
import com.moozlee.cloud_factory.config.properties.ApiPathProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Resource
    private ApiPathProperties apiPathProperties;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer
            .addPathPrefix(apiPathProperties.getGlobalPrefix(),c -> c.isAnnotationPresent(ApiRestController.class));
    }

}
