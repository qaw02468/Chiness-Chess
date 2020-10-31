package model.chessmovestrategy;

import model.exception.MoveInvalidException;
import model.sprite.chess.Chess;

import java.util.List;

public interface ChessMoveStrategy {

    void move(int moveRow, int moveCol, Chess chess, List<Chess> inGameAllChess) throws MoveInvalidException;

}
