package projekt.game.ui;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JPanel {
    JLabel gameOver;
    JLabel gameScore;
    JLabel gameTime;
    GameButton finishGame;
    public GameOver() {
        setBackground(new Color(0, 0, 0, 63));
        gameOver = new GameLabel("GAME OVER", 64);
        gameOver.setHorizontalAlignment(SwingConstants.CENTER);
        gameOver.setVerticalAlignment(SwingConstants.CENTER);
        add(gameOver);
        gameScore = new GameLabel("");
        add(gameScore);
        gameTime = new GameLabel("");
        add(gameTime);
    }

    public void setPlayTime(double time) {
        gameTime.setText("Enemies had to siege u over:" + (int)time + "s");
    }

    public void setScore(double score) {
        gameScore.setText("You've collected: " + score + " points");
    }
}
