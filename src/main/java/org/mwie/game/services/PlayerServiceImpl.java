package org.mwie.game.services;

import lombok.RequiredArgsConstructor;
import org.mwie.game.board.elements.Pit;
import org.mwie.game.board.elements.StandardPit;

@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final BoardService boardService;

    @Override
    public Pit takeTurn(int pitNumber) {
        var pit = boardService.getStandardPit(pitNumber);
        return takeTurn(pit);
    }

    private Pit takeTurn(StandardPit pit) {
        //TODO: implement me!
        return null;
    }
}
