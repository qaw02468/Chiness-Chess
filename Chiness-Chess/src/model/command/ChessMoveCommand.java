package model.command;

import model.exception.MoveInvalidException;
import model.sprite.chess.Chess;

import java.util.List;

public class ChessMoveCommand implements Command {

    private List<Chess> inGameChess;
    private Chess chess;
    private Chess deadChess;
    private int moveCol;
    private int moveRow;
    private int currentRow;
    private int currentCol;

    public ChessMoveCommand(List<Chess> inGameChess, Chess chess, int moveRow, int moveCol) {
        this.inGameChess = inGameChess;
        this.chess = chess;
        this.moveCol = moveRow;
        this.moveRow = moveCol;
        this.currentRow = chess.getRow();
        this.currentCol = chess.getCol();

    }

    public void execute() throws MoveInvalidException {
        chess.move(moveRow, moveCol, inGameChess);

        for (Chess gameChess : inGameChess) {
            if (gameChess.getRow() == chess.getRow() && gameChess.getCol() == chess.getCol() &&
                    gameChess.getColor() != chess.getColor()) {
                deadChess = gameChess;
                break;
            }
        }
    }


    public void undo() {
        if (deadChess != null) {
            inGameChess.add(deadChess);
        }

        for (Chess gameChess : inGameChess) {
            if (gameChess.equals(chess)) {
                gameChess.setRow(currentRow);
                gameChess.setCol(currentCol);
            }
        }
    }

}
