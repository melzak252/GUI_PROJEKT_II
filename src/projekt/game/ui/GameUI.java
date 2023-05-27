package projekt.game.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameUI extends JPanel {
    JLabel gameTime;
    HealthBar health;
    JLabel score;
    JButton goLeft;
    JButton goRight;
    JButton shoot;

    public GameUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255, 0));
        setOpaque(true);
        init();
    }

    private void init() {
        score = new GameLabel("");
        gameTime = new GameLabel("");
        health = new HealthBar(3.0);
        goLeft = new JButton("<-");
        goRight = new JButton("->");
        shoot = new JButton("SHOOT");
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(new Color(255, 255, 255, 0));

        topPanel.add(score);
        topPanel.add(Box.createHorizontalStrut(100));
        topPanel.add(gameTime);
        topPanel.add(Box.createHorizontalStrut(100));
        topPanel.add(health);


        this.add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(255, 255, 255, 0));
        bottomPanel.add(goLeft, BorderLayout.LINE_START);
        goLeft.setBackground(new Color(0,0,0,0));
        goLeft.setFocusable(false);

        shoot.setBackground(new Color(0,0,0,0));
        shoot.setFocusable(false);
        bottomPanel.add(shoot, BorderLayout.CENTER);
        goRight.setBackground(new Color(0,0,0,0));
        goRight.setFocusable(false);

        bottomPanel.add(goRight, BorderLayout.LINE_END);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void setScore(double score) {
        this.score.setText("Score: " + score);
    }

    public void setGameTime(double gameTime) {
        this.gameTime.setText(String.valueOf((int)Math.round(gameTime)));
    }

    public void setHealth(double health) {
        this.health.setHealth(health);
    }

    public void addShootListener(ActionListener shootListener) {
        shoot.addActionListener(shootListener);
    }

    public void addGoLeftListener(ActionListener goLeftListener) {
        goLeft.addActionListener(goLeftListener);
    }

    public void addGoRightListener(ActionListener goRightListener) {
        goRight.addActionListener(goRightListener);
    }
}
