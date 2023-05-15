package projekt.frames;

import projekt.game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame{

    GamePanel gamePanel;
    private Timer timer;

    public GameFrame() {
        this.init();


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setResizable(false);
        this.setVisible(true);
        pack();
    }

    private void init() {
        gamePanel = new GamePanel();
        this.add(gamePanel);
        for(KeyListener kl: gamePanel.getKeyListeners()) this.addKeyListener(kl);
    }
}
