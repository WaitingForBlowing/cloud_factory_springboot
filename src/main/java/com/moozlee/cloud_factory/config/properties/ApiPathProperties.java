package com.moozlee.cloud_factory.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api.path")
@Data
public class ApiPathProperties {

    String globalPrefix = "api";

}
