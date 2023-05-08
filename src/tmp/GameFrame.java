package tmp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameFrame extends JFrame{

    GamePanel gamePanel;
    private Timer timer;

    public GameFrame() {
        setLayout(null);
        this.init();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void init() {
        gamePanel = new GamePanel();
        this.addKeyListener(gamePanel);
        this.setContentPane(gamePanel);
    }

}
