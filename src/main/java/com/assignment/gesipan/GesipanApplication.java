package com.assignment.gesipan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GesipanApplication {

    public static void main(String[] args) {
        SpringApplication.run(GesipanApplication.class, args);
    }

}
