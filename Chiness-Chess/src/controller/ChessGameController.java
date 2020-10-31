package controller;

import model.ChessGame;
import model.exception.MoveInvalidException;
import model.exception.NoWinnerException;
import model.exception.NotYourChessException;
import model.exception.SameTeamException;
import model.player.Player;
import model.sprite.Color;
import view.View;
import view.state.NotPlayerTurn;
import view.state.SelectChessMoveState;
import view.state.SelectChessState;

public class ChessGameController {

    private View view;

    private ChessGame chessGame;

    public ChessGameController(View view, ChessGame chessGame) {
        this.view = view;
        this.chessGame = chessGame;
    }

    public void start() {
        chessGame.initChessBoard();
        addPlayer("player1");

        new Thread(() -> {
            while (true) {
                view.onUpdate(chessGame.getSprites());
                checkWinner();
            }
        }).start();
    }

    public void addPlayer(String name) {
        chessGame.addPlayer(name);
    }

    public void selectChess(int x, int y) {
        try {
            getChessGame().selectChess(x, y);
            getView().setState(new SelectChessMoveState(getView()));

        } catch (NotYourChessException e) {
            view.onNotYourChess(e);
        }
    }

    public void moveChess(int x, int y) {
        try {
            getChessGame().moveChess(x, y);
            getView().setState(new NotPlayerTurn(getView()));
            turnPlayer();
            view.onPut();

        } catch (SameTeamException ignored) {

        } catch (MoveInvalidException e) {
            view.onExceedRangeException(e);
        }
    }

    public void checkWinner() {
        try {
            Player player = chessGame.checkWin();
            if (player.getColor().equals(Color.RED)) {
                view.onWin(player.getName(), "紅色");
            } else {
                view.onWin(player.getName(), "黑色");
            }
            System.exit(0);
        } catch (NoWinnerException ignored) {

        }
    }

    public void turnPlayer() {
        for (Player player : chessGame.getPlayers()) {
            if (player.getColor().equals(chessGame.getTurn())) {
                view.setState(new SelectChessState(view));
            }
        }
    }

    public void destroyChess() {
        chessGame.regretChess();
    }

    public void mouseMove(int x, int y) {
        chessGame.mouseMove(x, y);
    }

    public View getView() {
        return view;
    }

    public ChessGame getChessGame() {
        return chessGame;
    }

}
