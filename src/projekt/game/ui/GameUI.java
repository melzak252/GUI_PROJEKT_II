package projekt.game.ui;

import javax.swing.*;
import java.awt.*;

public class GameUI extends JPanel{
    GameLabel scoreLabel;
    HealthBar health;
    public GameUI(double score) {
        setOpaque(false);
        setSize(700, 100);
        this.scoreLabel = new GameLabel("Score: " + score);
        this.health = new HealthBar(3.0);
        add(this.scoreLabel);
        add(this.health);
    }

    public void setScore(double score){
        scoreLabel.setText("Score: " + score);
    }
    public void setHealth(double health){
        this.health.setHealth(health);
    }


}
