package model.chessmovestrategy;

import model.exception.MoveInvalidException;
import model.sprite.chess.Chess;

import java.util.List;

public class CannonMove implements ChessMoveStrategy {
    @Override
    public void move(int moveRow, int moveCol, Chess chess, List<Chess> inGameAllChess) throws MoveInvalidException {
        int newRowAndOldRowDistance = moveRow - chess.getRow();
        int newColAndOldColDistance = moveCol - chess.getCol();

        if (newColAndOldColDistance == 0) {
            moveUpOrDown(newRowAndOldRowDistance, inGameAllChess, chess);
        } else {
            moveLeftOrRight(newColAndOldColDistance, inGameAllChess, chess);
        }
    }

    public void moveUpOrDown(int newRowAndOldRowDistance, List<Chess> inGameChess, Chess chess) throws MoveInvalidException {
        int moveRow = chess.getRow() + newRowAndOldRowDistance;
        int haveEnemy = 0;

        if (newRowAndOldRowDistance < 0) {
            for (int startMove = chess.getRow(); startMove > moveRow; startMove--) {
                for (Chess gameChess : inGameChess) {
                    if (gameChess.getRow() == startMove && gameChess.getCol() == chess.getCol() &&
                            !gameChess.equals(chess)) {
                        haveEnemy++;
                    }
                    if (haveEnemy >= 2) {
                        throw new MoveInvalidException();
                    }
                }
            }
        } else {
            for (int startMove = chess.getRow(); startMove < moveRow; startMove++) {
                for (Chess gameChess : inGameChess) {
                    if (gameChess.getCol() == chess.getCol() && gameChess.getRow() == moveRow &&
                            !gameChess.equals(chess)) {
                        haveEnemy++;
                    }
                    if (haveEnemy >= 2) {
                        throw new MoveInvalidException();
                    }
                }
            }
        }
        checkEnemyTopOrBottom(newRowAndOldRowDistance, inGameChess, chess, moveRow, haveEnemy);
    }

    private void checkEnemyTopOrBottom(int newRowAndOldRowDistance, List<Chess> inGameChess, Chess chess, int moveRow, int haveEnemy) throws MoveInvalidException {
        if (haveEnemy == 1) {
            for (Chess gameChess : inGameChess) {
                if (gameChess.getCol() == chess.getCol() && gameChess.getRow() == moveRow) {
                    chess.topOrBottomMove(newRowAndOldRowDistance);
                    return;
                }
            }
            throw new MoveInvalidException();
        } else {
            for (Chess gameChess : inGameChess) {
                if (gameChess.getCol() == chess.getCol() && gameChess.getRow() == moveRow) {
                    throw new MoveInvalidException();
                }
            }
            chess.topOrBottomMove(newRowAndOldRowDistance);
        }
    }


    public void moveLeftOrRight(int newColAndOldColDistance, List<Chess> inGameChess, Chess chess) throws MoveInvalidException {
        int moveCol = chess.getCol() + newColAndOldColDistance;
        int haveEnemy = 0;

        if (newColAndOldColDistance < 0) {
            for (int startMove = chess.getCol(); startMove > moveCol; startMove--) {
                haveEnemy = getHaveEnemy(inGameChess, chess, haveEnemy, startMove);
            }
        } else {
            for (int startMove = chess.getCol(); startMove < moveCol; startMove++) {
                haveEnemy = getHaveEnemy(inGameChess, chess, haveEnemy, startMove);
            }
        }
        checkEnemyLeftOrRight(newColAndOldColDistance, inGameChess, chess, moveCol, haveEnemy);
    }

    private int getHaveEnemy(List<Chess> inGameChess, Chess chess, int haveEnemy, int startMove) throws MoveInvalidException {
        for (Chess gameChess : inGameChess) {
            if (gameChess.getCol() == startMove && gameChess.getRow() == chess.getRow() &&
                    !gameChess.equals(chess)) {
                haveEnemy++;
            }
            if (haveEnemy >= 2) {
                throw new MoveInvalidException();
            }
        }
        return haveEnemy;
    }

    private void checkEnemyLeftOrRight(int newColAndOldColDistance, List<Chess> inGameChess, Chess chess, int moveCol, int haveEnemy) throws MoveInvalidException {
        if (haveEnemy == 1) {
            for (Chess gameChess : inGameChess) {
                if (gameChess.getCol() == moveCol && gameChess.getRow() == chess.getRow()) {
                    chess.LeftOrRightMove(newColAndOldColDistance);
                    return;
                }
            }
            throw new MoveInvalidException();
        } else {
            for (Chess gameChess : inGameChess) {
                if (gameChess.getCol() == moveCol && gameChess.getRow() == chess.getRow()) {
                    throw new MoveInvalidException();
                }
            }
            chess.LeftOrRightMove(newColAndOldColDistance);
        }
    }
}
