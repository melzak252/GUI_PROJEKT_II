package projekt.game;

import projekt.game.GameManager;
import projekt.game.components.ships.Player;
import projekt.game.ui.GameBackground;
import projekt.game.ui.GameUI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    int GAME_HEIGHT = 700;
    int GAME_WIDTH = 400;
    GameManager gameManager;
    GameUI gameUI;
    GameBackground gameBackground;
    Timer timer;
    int frames = 30;
    public GamePanel() {
        setLayout(new OverlayLayout(this));
        init();
    }

    private void init() {
        initGameManager();
        initGameUI();
        initBackGround();

        this.add(gameUI);
        this.add(gameBackground);
        timer = new Timer(1000 / frames, (e -> {
            gameManager.tick();
            updateGameUI();
            repaint();
        }));
        timer.start();
    }

    private void initBackGround() {
        gameBackground = new GameBackground(GAME_WIDTH, GAME_HEIGHT);
    }

    private void initGameUI() {
        gameUI = new GameUI(0.0);
    }

    private void initGameManager() {
        gameManager = new GameManager(GAME_WIDTH, GAME_HEIGHT);
        addKeyListener(gameManager.getPlayer());
        addKeyListener(gameManager);
    }

    private void updateGameUI() {
        gameUI.setScore(gameManager.getScore());
        gameUI.setHealth(gameManager.getPlayer().getHealth());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        gameManager.drawActors(g);
    }
}
