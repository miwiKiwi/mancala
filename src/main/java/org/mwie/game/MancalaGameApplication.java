package org.mwie.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.mwie.game")
public class MancalaGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(MancalaGameApplication.class, args);
    }

}
