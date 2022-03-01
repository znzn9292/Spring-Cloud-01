package com.znzn.configsevice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigSeviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigSeviceApplication.class, args);
    }

}
