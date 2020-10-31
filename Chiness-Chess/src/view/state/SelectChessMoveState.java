package view.state;

import view.View;

import java.awt.event.MouseEvent;

public class SelectChessMoveState extends State {

    public SelectChessMoveState(View view) {
        super(view);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        view.getChessGameController().moveChess(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

}
