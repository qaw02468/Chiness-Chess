package model.sprite.chess;

import model.chessmovestrategy.ChessMoveStrategy;
import model.exception.MoveInvalidException;
import model.sprite.Color;
import model.sprite.Sprite;

import java.util.List;


public class Chess extends Sprite implements Cloneable {

    private ChessMoveStrategy chessMoveStrategy;
    private ChessKind chessKind;

    public Chess(String imagePath, Color color, int width, int height, ChessMoveStrategy chessMoveStrategy,
                 ChessKind chessKind) {
        super(imagePath, color, width, height);
        this.chessMoveStrategy = chessMoveStrategy;
        this.chessKind = chessKind;
    }


    public void move(int x, int y, List<Chess> inGameChess) throws MoveInvalidException {
        chessMoveStrategy.move(x, y, this, inGameChess);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Color getColor() {
        return color;
    }

    public void topOrBottomMove(int distance) {
        setRow(row + distance);
    }

    public void LeftOrRightMove(int distance) {
        setCol(col + distance);
    }

    public ChessMoveStrategy getChessMoveStrategy() {
        return chessMoveStrategy;
    }

    public ChessKind getChessKind() {
        return chessKind;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
