package tmp;

import javax.swing.*;
import java.awt.*;

public class Enemy extends JComponent {
    private final int PLAYER_WIDTH = 64;
    private final int PLAYER_SPEED = 5;

    private final Image enemyImg;
    private int xPos, yPos;

    public Enemy(int x, int y) {
        xPos = x;
        yPos = y;
        ImageIcon ii = new ImageIcon("src/resources/enemy2.png");
        enemyImg = ii.getImage();
    }

    public Rectangle getBounds() {
        int PLAYER_HEIGHT = 30;
        return new Rectangle(xPos, yPos, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(enemyImg, xPos, yPos, this);
    }
}
