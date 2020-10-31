package model.chessmovestrategy;

import model.exception.MoveInvalidException;
import model.sprite.Color;
import model.sprite.chess.Chess;

import java.util.List;

public class KingMove implements ChessMoveStrategy {

    @Override
    public void move(int moveRow, int moveCol, Chess chess, List<Chess> inGameAllChess) throws MoveInvalidException {
        int newRowAndOldRowDistance = moveRow - chess.getRow();
        int newColAndOldColDistance = moveCol - chess.getCol();

        if (chess.getColor().equals(Color.BLACK)) {
            blackKingMove(moveCol,moveRow,newRowAndOldRowDistance,newColAndOldColDistance,chess);
        } else {
            redKingMove(moveCol,moveRow,newRowAndOldRowDistance,newColAndOldColDistance,chess);
        }
    }

    public void blackKingMove(int moveCol,int moveRow,int newRowAndOldRowDistance,int newColAndOldColDistance,
                              Chess chess) throws MoveInvalidException {

        if (moveCol < 3 || moveCol > 5 || moveRow > 2 || moveRow < 0 ||
                Math.abs(chess.getCol() - moveCol) + Math.abs(chess.getRow() - moveRow) > 1) {
            throw new MoveInvalidException();
        } else {
            chess.topOrBottomMove(newRowAndOldRowDistance);
            chess.LeftOrRightMove(newColAndOldColDistance);
        }
    }

    public void redKingMove(int moveCol,int moveRow,int newRowAndOldRowDistance,int newColAndOldColDistance,
                            Chess chess) throws MoveInvalidException {

        if (moveCol < 3 || moveCol > 5 || moveRow > 9 || moveRow < 7 ||
                Math.abs(chess.getCol() - moveCol) + Math.abs(chess.getRow() - moveRow) > 1) {
            throw new MoveInvalidException();
        } else {
            chess.topOrBottomMove(newRowAndOldRowDistance);
            chess.LeftOrRightMove(newColAndOldColDistance);
        }
    }
}
