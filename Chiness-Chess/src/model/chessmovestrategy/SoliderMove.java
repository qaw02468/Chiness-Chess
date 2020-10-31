package model.chessmovestrategy;

import model.exception.MoveInvalidException;
import model.sprite.Color;
import model.sprite.chess.Chess;

import java.util.List;

public class SoliderMove implements ChessMoveStrategy {
    @Override
    public void move(int moveRow, int moveCol, Chess chess, List<Chess> inGameAllChess) throws MoveInvalidException {
        int newRowAndOldRowDistance = moveRow - chess.getRow();
        int newColAndOldColDistance = moveCol - chess.getCol();

        if (Math.abs(newRowAndOldRowDistance) + Math.abs(newColAndOldColDistance) != 1) {
            throw new MoveInvalidException();
        }

        if (chess.getColor().equals(Color.BLACK)) {
            blackSoliderMove(newRowAndOldRowDistance, newColAndOldColDistance, chess);
        } else {
            redSoliderMove(newRowAndOldRowDistance, newColAndOldColDistance, chess);
        }
    }

    public void redSoliderMove(int newRowAndOldRowDistance, int newColAndOldColDistance, Chess chess) throws MoveInvalidException {
        if (newRowAndOldRowDistance == 1) {
            throw new MoveInvalidException();
        } else {
            if (chess.getRow() > 5) {
                if (newColAndOldColDistance != 0) {
                    throw new MoveInvalidException();
                } else {
                    chess.topOrBottomMove(newRowAndOldRowDistance);
                }
            } else {
                chess.topOrBottomMove(newRowAndOldRowDistance);
                chess.LeftOrRightMove(newColAndOldColDistance);
            }
        }
    }

    public void blackSoliderMove(int newRowAndOldRowDistance, int newColAndOldColDistance, Chess chess) throws MoveInvalidException {
        if (newRowAndOldRowDistance == -1) {
            throw new MoveInvalidException();
        } else {
            if (chess.getRow() < 5) {
                if (newColAndOldColDistance != 0) {
                    throw new MoveInvalidException();
                } else {
                    chess.topOrBottomMove(newRowAndOldRowDistance);
                }
            } else {
                chess.topOrBottomMove(newRowAndOldRowDistance);
                chess.LeftOrRightMove(newColAndOldColDistance);
            }
        }
    }
}
