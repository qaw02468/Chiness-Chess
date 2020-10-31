package model;

import model.exception.NoWinnerException;
import model.player.Player;
import model.prototypefactory.ChessPrototypeFactory;
import model.sprite.Color;
import model.sprite.chess.Chess;
import model.sprite.chess.ChessKind;

import java.util.List;

public class StandardChessGame extends ChessGame {


    public StandardChessGame(ChessPrototypeFactory chessPrototypeFactory) {
        super(chessPrototypeFactory);
    }

    @Override
    public void initChessBoard() {
        List<Chess> chessList = new ChessPutInBoardThenGetList(chessPrototypeFactory).get();

        getInGameAllChesses().addAll(chessList);
        getSprites().addAll(getInGameAllChesses());

    }

    public Player checkWin() throws NoWinnerException {
        for (Chess inGameAllChess : getInGameAllChesses()) {
            if (inGameAllChess.getColor().equals(Color.BLACK) &&
                    inGameAllChess.getChessKind().equals(ChessKind.KING)) {
                for (Chess gameAllChess : getInGameAllChesses()) {
                    if (gameAllChess.getColor().equals(Color.RED) &&
                            gameAllChess.getChessKind().equals(ChessKind.KING)) {
                        throw new NoWinnerException();
                    }
                }
                for (Player player : getPlayers()) {
                    if (player.getColor().equals(Color.BLACK)) {
                        return player;
                    }
                }
            }
        }
        for (Player player : getPlayers()) {
            if (player.getColor().equals(Color.RED)) {
                return player;
            }
        }
        throw new IllegalAccessError();
    }

    @Override
    public int[] countColAndRow(int x, int y) {
        int row, col;

        col = (x - 27) / 60;
        row = (y - 27) / 56;

        return new int[]{col, row};
    }
}
