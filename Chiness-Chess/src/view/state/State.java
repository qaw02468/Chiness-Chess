package view.state;

import view.View;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class State implements MouseMotionListener, MouseListener {
    protected View view;

    public State(View view) {
        this.view = view;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        view.getChessGameController().mouseMove(e.getX(), e.getY());
    }


}
