package com.neal.nealservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { JacksonAutoConfiguration.class, DataSourceAutoConfiguration.class })
public class NealServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NealServiceApplication.class, args);
    }
}
