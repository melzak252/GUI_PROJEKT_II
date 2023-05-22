package projekt.game.ui;

import javax.swing.*;
import java.awt.*;

public class GameUI extends JPanel{
    GameLabel scoreLabel;
    HealthBar health;

    JPanel endPanel;

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
    public void gameWon(){

    }

    public void gameLost(){
        if(endPanel != null) return;
        this.remove(scoreLabel);
        this.remove(health);
        System.out.println("HEREEEEEEE");
        endPanel = new JPanel();
        endPanel.setBackground(new Color(0, 0,0, 64));
        GameLabel gl = new GameLabel("Game Over");
        gl.setVerticalAlignment(SwingConstants.CENTER);
        gl.setHorizontalAlignment(SwingConstants.CENTER);
        endPanel.setPreferredSize(new Dimension(500, 800));

        endPanel.add(gl);

        this.add(endPanel);
        validate();
    }


}
