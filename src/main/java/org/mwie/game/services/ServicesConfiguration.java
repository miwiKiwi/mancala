package org.mwie.game.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfiguration {

    @Bean
    public GameService gameService() {
        return new GameServiceImpl();
    }

    @Bean
    public PlayerService playerService() {
        return new PlayerServiceImpl();
    }


}
