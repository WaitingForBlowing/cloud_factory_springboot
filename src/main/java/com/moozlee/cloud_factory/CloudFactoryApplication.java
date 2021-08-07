package com.moozlee.cloud_factory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.moozlee.cloud_factory.mapper")
public class CloudFactoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudFactoryApplication.class, args);
    }

}
