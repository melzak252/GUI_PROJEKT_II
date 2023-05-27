package projekt.game.ui;

import javax.swing.*;
import java.awt.*;

public class WinView extends JPanel {
    JLabel gameOver;
    JLabel gameScore;
    JLabel gameTime;
    GameButton finishGame;
    public WinView() {
        setBackground(new Color(0, 0, 0, 63));
        gameOver = new GameLabel("WINNER", 64);
        gameOver.setHorizontalAlignment(SwingConstants.CENTER);
        gameOver.setVerticalAlignment(SwingConstants.CENTER);
        add(gameOver);
        gameScore = new GameLabel("Your final score: 52.0");
        add(gameScore);
        gameTime = new GameLabel("You survived: 87 s");
        add(gameTime);
    }
}