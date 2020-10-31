package view;

import controller.ChessGameController;
import model.sprite.Sprite;
import utils.ResourceUtils;
import view.state.SelectChessState;
import view.state.State;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public abstract class View extends JFrame implements KeyListener {
    private ChessGameController gameController;
    private Canvas gameCanvas;
    private State state = new SelectChessState(this);

    public View() {
        setSize(600, 640);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameCanvas = new Canvas();
        setResizable(false);
        setContentPane(gameCanvas);

    }

    public void launch() {
        setVisible(true);
        addMouseMotionListener(state);
        addMouseListener(state);
        addKeyListener(this);
        gameController.start();

    }

    public void onUpdate(List<Sprite> sprites) {
        gameCanvas.onUpdate(sprites);
    }

    public void onPut() {
        playSound("media/put.wav");
    }

    public void onWin(String name, String color) {
        playSound("media/checkmate.wav");
        winPanel(name, color);
    }

    public void onSameTeam(Exception e) {
        playSound("media/error.wav");
    }

    public void onNotYourChess(Exception e) {
        playSound("media/error.wav");
    }

    public void onExceedRangeException(Exception e) {
        playSound("media/error.wav");
    }

    public void playSound(String path) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(ResourceUtils.getFile(path));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();

        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException err) {
            err.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R) {
            regretChessPanel();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void winPanel(String name, String color) {
        JDialog dialog = new JDialog(this, "遊戲結束", true);
        dialog.setSize(250, 150);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(this);

        JLabel label = new JLabel();
        label.setText("玩家 " + name + "(" + color + ")" + "贏得遊戲");

        JButton okBtn = new JButton("確定");

        okBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        JPanel panel = new JPanel();

        panel.add(label);
        panel.add(okBtn);

        dialog.setContentPane(panel);
        dialog.setVisible(true);
    }


    public void regretChessPanel() {
        JDialog dialog = new JDialog(this, "悔棋", true);
        dialog.setSize(250, 150);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(this);

        JLabel label = new JLabel();
        label.setText("確定要悔棋嗎");

        JButton okBtn = new JButton("是(Y)");
        JButton noBtn = new JButton("否(N)");

        okBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getChessGameController().destroyChess();
                dialog.dispose();
            }
        });
        JPanel panel = new JPanel();

        panel.add(label);
        panel.add(okBtn);
        panel.add(noBtn);

        dialog.setContentPane(panel);
        dialog.setVisible(true);
    }

    private class Canvas extends JPanel {

        private List<Sprite> sprites = Collections.emptyList();

        public Canvas() {
            setSize(700, 700);
        }

        public void onUpdate(List<Sprite> sprites) {
            this.sprites = sprites;
            repaint();
        }

        private void drawBackground(Graphics g) {
            try {
                BufferedImage image = ImageIO.read(ResourceUtils.getFile("chessboard.png"));
                g.drawImage(image, 0, 0, 600, 600, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawBackground(g);

            sprites.forEach(sprite -> paintSprite(g, sprite));
        }

        private void paintSprite(Graphics g, Sprite sprite) {
            try {
                BufferedImage image = ImageIO.read(ResourceUtils.getFile(sprite.getImagePath()));
                g.drawImage(image, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight(), this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ChessGameController getChessGameController() {
        return gameController;
    }

    public void setChessGameController(ChessGameController gameController) {
        this.gameController = gameController;
    }

    public void setState(State state) {
        removeMouseMotionListener(this.state);
        removeMouseListener(this.state);
        this.state = state;
        addMouseMotionListener(state);
        addMouseListener(state);

    }
}
