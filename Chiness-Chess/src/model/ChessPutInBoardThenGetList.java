package model;

import model.prototypefactory.ChessPrototypeFactory;
import model.sprite.Color;
import model.sprite.chess.Chess;
import model.sprite.chess.ChessKind;

import java.util.LinkedList;
import java.util.List;

public class ChessPutInBoardThenGetList {
    private ChessPrototypeFactory chessPrototypeFactory;

    public ChessPutInBoardThenGetList(ChessPrototypeFactory chessPrototypeFactory) {
        this.chessPrototypeFactory = chessPrototypeFactory;
    }

    public List<Chess> get() {
        Chess newChess;

        List<Chess> chessList = new LinkedList<>();
        newChess = chessPrototypeFactory.create(ChessKind.KING, Color.RED);
        newChess.setRowAndCol(9, 4);
        chessList.add(newChess);

        newChess = chessPrototypeFactory.create(ChessKind.KING, Color.BLACK);
        newChess.setRowAndCol(0, 4);
        chessList.add(newChess);

        for (int i = 3; i <= 5; i += 2) {
            newChess = chessPrototypeFactory.create(ChessKind.GUARD, Color.RED);
            newChess.setRowAndCol(9, i);
            chessList.add(newChess);

            newChess = chessPrototypeFactory.create(ChessKind.GUARD, Color.BLACK);
            newChess.setRowAndCol(0, i);
            chessList.add(newChess);

        }

        for (int i = 2; i <= 6; i += 4) {
            newChess = chessPrototypeFactory.create(ChessKind.ELEPHANT, Color.RED);
            newChess.setRowAndCol(9, i);
            chessList.add(newChess);

            newChess = chessPrototypeFactory.create(ChessKind.ELEPHANT, Color.BLACK);
            newChess.setRowAndCol(0, i);
            chessList.add(newChess);
        }

        for (int i = 1; i <= 7; i += 6) {
            newChess = chessPrototypeFactory.create(ChessKind.HORSE, Color.RED);
            newChess.setRowAndCol(9, i);
            chessList.add(newChess);

            newChess = chessPrototypeFactory.create(ChessKind.HORSE, Color.BLACK);
            newChess.setRowAndCol(0, i);
            chessList.add(newChess);

        }

        for (int i = 0; i <= 8; i += 8) {
            newChess = chessPrototypeFactory.create(ChessKind.ROOK, Color.RED);
            newChess.setRowAndCol(9, i);
            chessList.add(newChess);

            newChess = chessPrototypeFactory.create(ChessKind.ROOK, Color.BLACK);
            newChess.setRowAndCol(0, i);
            chessList.add(newChess);
        }

        for (int i = 0; i <= 8; i += 2) {
            newChess = chessPrototypeFactory.create(ChessKind.SOLIDER, Color.RED);
            newChess.setRowAndCol(6, i);
            chessList.add(newChess);

            newChess = chessPrototypeFactory.create(ChessKind.SOLIDER, Color.BLACK);
            newChess.setRowAndCol(3, i);
            chessList.add(newChess);
        }

        for (int i = 1; i <= 7; i += 6) {
            newChess = chessPrototypeFactory.create(ChessKind.CANNON, Color.RED);
            newChess.setRowAndCol(7, i);
            chessList.add(newChess);

            newChess = chessPrototypeFactory.create(ChessKind.CANNON, Color.BLACK);
            newChess.setRowAndCol(2, i);
            chessList.add(newChess);
        }
        return chessList;
    }
}
