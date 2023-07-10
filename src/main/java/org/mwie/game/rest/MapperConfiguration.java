package org.mwie.game.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public GameMapper gameMapper() {
        return new GameMapper();
    }
}
