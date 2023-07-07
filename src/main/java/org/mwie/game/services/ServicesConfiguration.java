package org.mwie.game.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfiguration {

    @Bean
    public GameService gameService(PlayerService playerService) {
        return new GameServiceImpl(playerService);
    }

    @Bean
    public PlayerService playerService() {
        return new PlayerServiceImpl();
    }


}
