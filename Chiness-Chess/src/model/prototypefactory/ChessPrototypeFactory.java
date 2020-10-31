package model.prototypefactory;

import model.chessmovestrategy.*;
import model.sprite.Color;
import model.sprite.chess.Chess;
import model.sprite.chess.ChessKind;

import java.util.HashMap;
import java.util.Map;

public class ChessPrototypeFactory {

    private Map<ChessInformation, Chess> chessMap = new HashMap<>();

    public ChessPrototypeFactory() {
        init();
    }

    private void init() {
        chessMap.put(new ChessInformation(ChessKind.KING, Color.RED),
                new Chess("chess/red_general.png", Color.RED, 40, 43, new KingMove(), ChessKind.KING));
        chessMap.put(new ChessInformation(ChessKind.GUARD, Color.RED),
                new Chess("chess/red_advisor.png", Color.RED, 40, 43, new GuardMove(), ChessKind.GUARD));
        chessMap.put(new ChessInformation(ChessKind.ELEPHANT, Color.RED),
                new Chess("chess/red_elephant.png", Color.RED, 40, 43, new ElephantMove(), ChessKind.ELEPHANT));
        chessMap.put(new ChessInformation(ChessKind.HORSE, Color.RED),
                new Chess("chess/red_knight.png", Color.RED, 40, 43, new HorseMove(), ChessKind.HORSE));
        chessMap.put(new ChessInformation(ChessKind.ROOK, Color.RED),
                new Chess("chess/red_rook.png", Color.RED, 40, 43, new RookMove(), ChessKind.ROOK));
        chessMap.put(new ChessInformation(ChessKind.SOLIDER, Color.RED),
                new Chess("chess/red_soldier.png", Color.RED, 40, 43, new SoliderMove(), ChessKind.SOLIDER));
        chessMap.put(new ChessInformation(ChessKind.CANNON, Color.RED),
                new Chess("chess/red_cannon.png", Color.RED, 40, 43, new CannonMove(), ChessKind.SOLIDER));

        chessMap.put(new ChessInformation(ChessKind.KING, Color.BLACK),
                new Chess("chess/black_general.png", Color.BLACK, 40, 43, new KingMove(), ChessKind.KING));
        chessMap.put(new ChessInformation(ChessKind.GUARD, Color.BLACK),
                new Chess("chess/black_advisor.png", Color.BLACK, 40, 43, new GuardMove(), ChessKind.GUARD));
        chessMap.put(new ChessInformation(ChessKind.ELEPHANT, Color.BLACK),
                new Chess("chess/black_elephant.png", Color.BLACK, 40, 43, new ElephantMove(), ChessKind.ELEPHANT));
        chessMap.put(new ChessInformation(ChessKind.HORSE, Color.BLACK),
                new Chess("chess/black_knight.png", Color.BLACK, 40, 43, new HorseMove(), ChessKind.HORSE));
        chessMap.put(new ChessInformation(ChessKind.ROOK, Color.BLACK),
                new Chess("chess/black_rook.png", Color.BLACK, 40, 43, new RookMove(), ChessKind.ROOK));
        chessMap.put(new ChessInformation(ChessKind.SOLIDER, Color.BLACK),
                new Chess("chess/black_soldier.png", Color.BLACK, 40, 43, new SoliderMove(), ChessKind.SOLIDER));
        chessMap.put(new ChessInformation(ChessKind.CANNON, Color.BLACK),
                new Chess("chess/black_cannon.png", Color.BLACK, 40, 43, new CannonMove(), ChessKind.SOLIDER));
    }

    public Chess create(ChessKind category, Color color) {
        Chess chess;
        try {
            chess = ((Chess) chessMap.get(new ChessInformation(category, color)).clone());
            return chess;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException();
    }

}
