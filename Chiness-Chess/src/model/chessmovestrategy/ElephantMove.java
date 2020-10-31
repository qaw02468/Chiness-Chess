package model.chessmovestrategy;

import model.exception.MoveInvalidException;
import model.sprite.Color;
import model.sprite.chess.Chess;

import java.util.List;

public class ElephantMove implements ChessMoveStrategy {

    @Override
    public void move(int moveRow, int moveCol, Chess chess, List<Chess> inGameAllChess) throws MoveInvalidException {
        int newRowAndOldRowDistance = moveRow - chess.getRow();
        int newColAndOldColDistance = moveCol - chess.getCol();

        if (Math.abs(newRowAndOldRowDistance) != 2 || Math.abs(newColAndOldColDistance) != 2) {
            throw new MoveInvalidException();
        }
        if (chess.getColor().equals(Color.BLACK)) {
            if (moveRow > 4) {
                throw new MoveInvalidException();
            }
        } else {
            if (moveRow < 5) {
                throw new MoveInvalidException();
            }
        }

        if (newRowAndOldRowDistance > 0 && newColAndOldColDistance > 0) {
            moveLowerRight(inGameAllChess, chess);

        } else if (newRowAndOldRowDistance > 0 && newColAndOldColDistance < 0) {
            moveLowerLeft(inGameAllChess, chess);

        } else if (newRowAndOldRowDistance < 0 && newColAndOldColDistance > 0) {
            moveUpperRight(inGameAllChess, chess);

        } else if (newRowAndOldRowDistance < 0 && newColAndOldColDistance < 0) {
            moveUpperLeft(inGameAllChess, chess);
        }

        chess.topOrBottomMove(newRowAndOldRowDistance);
        chess.LeftOrRightMove(newColAndOldColDistance);
    }

    public void moveLowerRight(List<Chess> inGameAllChess, Chess chess) throws MoveInvalidException {
        for (int i = 1; i < 2; i++) {
            for (Chess gameAllChess : inGameAllChess) {
                if (gameAllChess.getCol() == chess.getCol() + i && gameAllChess.getRow() == chess.getRow() + i) {
                    throw new MoveInvalidException();
                }
            }
        }
    }

    public void moveLowerLeft(List<Chess> inGameAllChess, Chess chess) throws MoveInvalidException {
        for (int i = 1; i < 2; i++) {
            for (Chess gameAllChess : inGameAllChess) {
                if (gameAllChess.getCol() == chess.getCol() - i && gameAllChess.getRow() == chess.getRow() + i) {
                    throw new MoveInvalidException();
                }
            }
        }
    }

    public void moveUpperRight(List<Chess> inGameAllChess, Chess chess) throws MoveInvalidException {
        for (int i = 1; i < 2; i++) {
            for (Chess gameAllChess : inGameAllChess) {
                if (gameAllChess.getCol() == chess.getCol() + i && gameAllChess.getRow() == chess.getRow() - i) {
                    throw new MoveInvalidException();
                }
            }
        }
    }

    public void moveUpperLeft(List<Chess> inGameAllChess, Chess chess) throws MoveInvalidException {
        for (int i = 1; i < 2; i++) {
            for (Chess gameAllChess : inGameAllChess) {
                if (gameAllChess.getCol() == chess.getCol() - i && gameAllChess.getRow() == chess.getRow() - i) {
                    throw new MoveInvalidException();
                }
            }
        }
    }
}
