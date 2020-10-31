package model;

import model.command.ChessMoveCommand;
import model.command.Command;
import model.exception.MoveInvalidException;
import model.exception.NoWinnerException;
import model.exception.NotYourChessException;
import model.exception.SameTeamException;
import model.player.*;
import model.prototypefactory.ChessPrototypeFactory;
import model.sprite.Color;
import model.sprite.Sprite;
import model.sprite.chess.Chess;
import model.sprite.selectIcon.SelectIcon;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class ChessGame {
    private Color turn = Color.RED;
    protected ChessPrototypeFactory chessPrototypeFactory;
    protected Chess targetChess;
    private Stack<Command> usedCommandStack = new Stack<>();
    private List<Player> players = new ArrayList<>();
    private List<Sprite> sprites = new ArrayList<>();
    private List<Chess> inGameAllChess = new ArrayList<>();


    public ChessGame(ChessPrototypeFactory chessPrototypeFactory) {
        this.chessPrototypeFactory = chessPrototypeFactory;
    }

    public abstract void initChessBoard();

    public abstract Player checkWin() throws NoWinnerException;

    public void addPlayer(String name) {
        players.add(new HumanPlayer(name, Color.RED));
        players.add(new HumanPlayer("123", Color.BLACK));
    }

    public void selectChess(int x, int y) throws NotYourChessException {
        for (Chess chess : inGameAllChess) {
            int[] rowAndCol = countColAndRow(x, y);

            if (chess.getCol() == rowAndCol[0] && chess.getRow() == rowAndCol[1] &&
                    chess.getColor().equals(turn)) {
                setTargetChess(chess);
                return;
            }
        }
        throw new NotYourChessException();
    }

    public void moveChess(int x, int y) throws SameTeamException, MoveInvalidException {
        int[] colAndRow = countColAndRow(x, y);

        for (Chess chess : inGameAllChess) {
            if (chess.getCol() == colAndRow[0] && chess.getRow() == colAndRow[1] &&
                    getTargetChess().getColor().equals(chess.getColor())) {
                targetChess = chess;
                throw new SameTeamException();
            }
        }

        if (colAndRow[0] > 8 || colAndRow[0] < 0 || colAndRow[1] < 0 || colAndRow[1] > 9) {
            throw new MoveInvalidException();
        }

        Command moveCommand = new ChessMoveCommand(inGameAllChess, getTargetChess(), colAndRow[0], colAndRow[1]);
        moveCommand.execute();
        usedCommandStack.push(moveCommand);

        removeDeadChess();
        changeTurn();
        targetChess = null;
    }

    public void regretChess() {
        if (!usedCommandStack.isEmpty()) {
            Command command = usedCommandStack.pop();
            command.undo();

            List<Sprite> undoSprites = new ArrayList<>(getInGameAllChesses());
            setSprites(undoSprites);
            changeTurn();
        }
    }

    public void mouseMove(int x, int y) {
        int[] colAndRow = countColAndRow(x, y);

        for (Sprite sprite : sprites) {
            if (sprite instanceof SelectIcon) {
                sprite.setCol(colAndRow[0]);
                sprite.setRow(colAndRow[1]);
                return;
            }
        }

        Sprite selectIcon = new SelectIcon("selected_red.png", Color.RED, 40, 43);
        selectIcon.setCol(colAndRow[0]);
        selectIcon.setRow(colAndRow[1]);
        sprites.add(selectIcon);
    }

    public void removeDeadChess() {
        for (Chess chess : inGameAllChess) {
            if (targetChess.getRow() == chess.getRow() && targetChess.getCol() == chess.getCol() &&
                    targetChess.getColor() != chess.getColor()) {
                inGameAllChess.remove(chess);
                sprites.remove(chess);
                break;
            }
        }
    }

    public void changeTurn() {
        if (turn.equals(Color.RED)) {
            turn = Color.BLACK;
            for (Sprite sprite : sprites) {
                if (sprite instanceof SelectIcon) {
                    sprite.setColor(Color.BLACK);
                    sprite.setImagePath("selected_black.png");
                    return;
                }
            }
        } else {
            turn = Color.RED;
            for (Sprite sprite : sprites) {
                if (sprite instanceof SelectIcon) {
                    sprite.setColor(Color.RED);
                    sprite.setImagePath("selected_red.png");
                    return;
                }
            }
        }
    }

    public abstract int[] countColAndRow(int x, int y);

    public List<Player> getPlayers() {
        return players;
    }

    public Chess getTargetChess() {
        return targetChess;
    }

    public void setTargetChess(Chess targetChess) {
        this.targetChess = targetChess;
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    public void setSprites(List<Sprite> sprites) {
        this.sprites = sprites;
    }

    public Color getTurn() {
        return turn;
    }

    public List<Chess> getInGameAllChesses() {
        return inGameAllChess;
    }


}
