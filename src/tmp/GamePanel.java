package tmp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener {
    int GAME_HEIGHT = 800;
    int GAME_WIDTH = 600;
    Player player;
    double[] moveVector = {0.0, 0.0};
    Timer timer;

    java.util.List<Enemy> enemies = new ArrayList<>();

    public GamePanel() {
        setBackground(Color.BLACK);
        setLayout(null);
        setBounds(0, 0, GAME_WIDTH, GAME_HEIGHT);
        setSize(GAME_WIDTH, GAME_HEIGHT);
        init();
    }

    private void init() {
        initPlayer();
        initEnemies();
        initTimer();
    }

    private void initEnemies() {
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 2; j++) {
                Enemy newEnemy = new Enemy(60 * i + 60, 60 * j + 60);
                this.add(newEnemy);

                enemies.add(newEnemy);
            }
        }
    }

    private void initPlayer() {
        player = new Player(GAME_WIDTH/2, GAME_HEIGHT/2);
        add(player);

    }

    private void initTimer() {
        timer = new Timer(1000 /  30, e -> {
            move();
            collisions();
            repaint();
        });
        timer.start();
    }

    private void collisions() {

    }

    private void move() {
        player.move(moveVector, GAME_WIDTH, GAME_HEIGHT);
        player.getBullets().forEach(Bullet::move);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT -> moveVector[0] += (moveVector[0] > -1)? -1.: 0;
            case KeyEvent.VK_RIGHT -> moveVector[0] += (moveVector[0] < 1)? 1.: 0;
            case KeyEvent.VK_UP -> moveVector[1] += (moveVector[1] > -1)? -1.: 0;
            case KeyEvent.VK_DOWN -> moveVector[1] += (moveVector[1] < 1)? 1.: 0;
            case KeyEvent.VK_SPACE -> player.shot();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT -> moveVector[0] -= -1.;
            case KeyEvent.VK_RIGHT -> moveVector[0] -= 1.;
            case KeyEvent.VK_UP -> moveVector[1] -= -1.;
            case KeyEvent.VK_DOWN -> moveVector[1] -= 1.;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.paintComponent(g);
        enemies.forEach(e -> e.paintComponent(g));
    }
}
