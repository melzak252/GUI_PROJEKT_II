package projekt.game;

import projekt.game.GameManager;
import projekt.game.components.ships.Player;
import projekt.game.levels.Level1;
import projekt.game.ui.GameBackground;
import projekt.game.ui.GameUI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    GameManager gameManager;
    GameUI gameUI;
    Timer timer;
    int frames = 30;
    public GamePanel() {
        setLayout(new OverlayLayout(this));
        init();
    }

    private void init() {
        initGameManager();
        initGameUI();

        this.add(gameUI);
        timer = new Timer(1000 / frames, (e -> {
            gameManager.tick();
            updateGameUI();
            repaint();
        }));
//        timer.start();
    }
    private void initGameUI() {
        gameUI = new GameUI(0.0);
        JButton b = new JButton("Start game");
        b.setFocusable(false);
        b.addActionListener(a -> {
            timer.start();
            this.gameUI.remove(b);
        });
        gameUI.add(b);
    }

    private void initGameManager() {
        gameManager = new GameManager(Level1.waves, Level1.randomStart);
        addKeyListener(gameManager.getPlayer());
        addKeyListener(gameManager);
        add(gameManager);
    }

    private void updateGameUI() {
        gameUI.setScore(gameManager.getScore());
        gameUI.setHealth(gameManager.getPlayer().getHealth());
        if(gameManager.isFinished()){
            if(gameManager.isWin()) gameUI.gameWon();
            else gameUI.gameLost();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        gameManager.drawActors(g);
        gameUI.paint(g);
    }
}
