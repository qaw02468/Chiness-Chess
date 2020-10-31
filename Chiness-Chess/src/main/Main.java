package main;

import controller.ChessGameController;
import model.ChessGame;
import model.StandardChessGame;
import model.prototypefactory.ChessPrototypeFactory;
import view.StandardView;
import view.View;


public class Main {
    public static void main(String[] args) {
        ChessPrototypeFactory chessPrototypeFactory = new ChessPrototypeFactory();
        ChessGame game = new StandardChessGame(chessPrototypeFactory);
        View standardView = new StandardView();
        ChessGameController chessGameController = new ChessGameController(standardView, game);
        standardView.setChessGameController(chessGameController);
        standardView.launch();
    }
}
