package com.tgtech.explore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.tgtech.explore")
public class IgnitionIntoHeaven {
    private static String[] args;
    private static ConfigurableApplicationContext context;

    public static void main(String... args) throws Exception {
        IgnitionIntoHeaven.args = args;
        IgnitionIntoHeaven.context = SpringApplication.run(IgnitionIntoHeaven.class, args);
    }
}
