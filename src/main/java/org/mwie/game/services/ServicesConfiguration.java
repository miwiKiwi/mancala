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
    public PlayerService playerService(BoardService boardService) {
        return new PlayerServiceImpl(boardService);
    }

    @Bean
    public BoardService boardService() {
        return new BoardServiceimpl();
    }
}
