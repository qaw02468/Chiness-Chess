package model.chessmovestrategy;

import model.exception.MoveInvalidException;
import model.sprite.chess.Chess;

import java.util.List;

public class HorseMove implements ChessMoveStrategy {
    @Override
    public void move(int moveRow, int moveCol, Chess chess, List<Chess> inGameAllChess) throws MoveInvalidException {
        int newRowAndOldRowDistance = moveRow - chess.getRow();
        int newColAndOldColDistance = moveCol - chess.getCol();

        if (newColAndOldColDistance == 2 && Math.abs(newRowAndOldRowDistance) == 1) {
            moveRightThenUpOrDown(inGameAllChess, chess);

        } else if (newColAndOldColDistance == -2 && Math.abs(newRowAndOldRowDistance) == 1) {
            moveLeftThenUpOrDown(inGameAllChess, chess);

        } else if (Math.abs(newColAndOldColDistance) == 1 && newRowAndOldRowDistance == -2) {
            moveUpThenLeftOrRight(inGameAllChess, chess);

        } else if (Math.abs(newColAndOldColDistance) == 1 && newRowAndOldRowDistance == 2) {
            moveDownThenLeftOrRight(inGameAllChess, chess);

        } else {
            throw new MoveInvalidException();
        }
        chess.topOrBottomMove(newRowAndOldRowDistance);
        chess.LeftOrRightMove(newColAndOldColDistance);
    }

    private void moveDownThenLeftOrRight(List<Chess> inGameAllChess, Chess chess) throws MoveInvalidException {
        for (Chess gameAllChess : inGameAllChess) {
            if (gameAllChess.getRow() == chess.getRow() + 1 && gameAllChess.getCol() == chess.getCol()) {
                throw new MoveInvalidException();
            }
        }
    }

    private void moveUpThenLeftOrRight(List<Chess> inGameAllChess, Chess chess) throws MoveInvalidException {
        for (Chess gameAllChess : inGameAllChess) {
            if (gameAllChess.getRow() == chess.getRow() - 1 && gameAllChess.getCol() == chess.getCol()) {
                throw new MoveInvalidException();
            }
        }
    }

    private void moveLeftThenUpOrDown(List<Chess> inGameAllChess, Chess chess) throws MoveInvalidException {
        for (Chess gameAllChess : inGameAllChess) {
            if (gameAllChess.getRow() == chess.getRow() && gameAllChess.getCol() == chess.getCol() - 1) {
                throw new MoveInvalidException();
            }
        }
    }

    private void moveRightThenUpOrDown(List<Chess> inGameAllChess, Chess chess) throws MoveInvalidException {
        for (Chess gameAllChess : inGameAllChess) {
            if (gameAllChess.getRow() == chess.getRow() + 1 && gameAllChess.getCol() == chess.getCol() + 1) {
                throw new MoveInvalidException();
            }
        }
    }
}
