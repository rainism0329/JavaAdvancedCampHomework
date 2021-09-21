package com.example.readwritesplit2ss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Readwritesplit2ssApplication {

    public static void main(String[] args) {
        SpringApplication.run(Readwritesplit2ssApplication.class, args);
    }

}
