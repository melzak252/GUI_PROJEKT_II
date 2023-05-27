package projekt.game.ui;

import javax.swing.*;
import java.awt.*;

public class WinView extends JPanel {
    JLabel gameOver;
    JLabel gameScore;
    JLabel gameTime;
    JLabel playerHealth;
    GameButton finishGame;
    public WinView() {
        setBackground(new Color(0, 0, 0, 63));
        gameOver = new GameLabel("WINNER", 64);
        gameOver.setHorizontalAlignment(SwingConstants.CENTER);
        gameOver.setVerticalAlignment(SwingConstants.CENTER);
        add(gameOver);
        gameScore = new GameLabel("");
        add(gameScore);
        gameTime = new GameLabel("");
        add(gameTime);

        playerHealth = new GameLabel("");
        add(playerHealth);
    }

    public void setPlayTime(double time) {
        gameTime.setText("You destroyed all enmies in:" + (int)time + "s\n");
    }

    public void setScore(double score) {
        gameScore.setText("You've collected: " + score + " points!\n");
    }

    public void setHealth(double health){
        playerHealth.setText("You've won with: " + health + " health points left!");
        playerHealth.setToolTipText("Each half of health point is worth of 15 pts!");
    }
}