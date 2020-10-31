package model.chessmovestrategy;

import model.exception.MoveInvalidException;
import model.sprite.chess.Chess;

import java.util.List;

public class RookMove implements ChessMoveStrategy {
    @Override
    public void move(int moveRow, int moveCol, Chess chess, List<Chess> inGameAllChess) throws MoveInvalidException {
        int newRowAndOldRowDistance = moveRow - chess.getRow();
        int newColAndOldColDistance = moveCol - chess.getCol();

        if (newRowAndOldRowDistance != 0 && newColAndOldColDistance != 0) {
            throw new MoveInvalidException();
        }

        if (newColAndOldColDistance == 0) {
            moveUpOrDown(newRowAndOldRowDistance, inGameAllChess, chess);
        } else {
            moveLeftOrRight(newColAndOldColDistance, inGameAllChess, chess);
        }
    }

    public void moveUpOrDown(int newRowAndOldRowDistance, List<Chess> inGameChess, Chess chess) throws MoveInvalidException {
        int moveRow = chess.getRow() + newRowAndOldRowDistance;

        if (newRowAndOldRowDistance < 0) {
            for (int startMove = chess.getRow(); startMove > moveRow; startMove--) {
                for (Chess gameChess : inGameChess) {
                    if (gameChess.getRow() == startMove && gameChess.getCol() == chess.getCol() &&
                            !gameChess.equals(chess)) {
                        throw new MoveInvalidException();
                    }
                }
            }
        } else {
            for (int startMove = chess.getRow(); startMove < moveRow; startMove++) {
                for (Chess gameChess : inGameChess) {
                    if (gameChess.getRow() == startMove && gameChess.getCol() == chess.getCol() &&
                            !gameChess.equals(chess)) {
                        throw new MoveInvalidException();
                    }
                }
            }
        }
        chess.topOrBottomMove(newRowAndOldRowDistance);
    }

    public void moveLeftOrRight(int newColAndOldColDistance, List<Chess> inGameChess, Chess chess) throws MoveInvalidException {
        int moveCol = chess.getCol() + newColAndOldColDistance;

        if (newColAndOldColDistance < 0) {
            for (int startMove = chess.getCol(); startMove > moveCol; startMove--) {
                for (Chess gameChess : inGameChess) {
                    if (gameChess.getCol() == startMove && gameChess.getRow() == chess.getRow() &&
                            !gameChess.equals(chess)) {
                        throw new MoveInvalidException();
                    }
                }
            }
        } else {
            for (int startMove = chess.getCol(); startMove < moveCol; startMove++) {
                for (Chess gameChess : inGameChess) {
                    if (gameChess.getCol() == startMove && gameChess.getRow() == chess.getRow() &&
                            !gameChess.equals(chess)) {
                        throw new MoveInvalidException();
                    }
                }
            }
        }
        chess.LeftOrRightMove(newColAndOldColDistance);
    }

}

