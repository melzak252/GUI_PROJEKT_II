package tmp;

import javax.swing.*;
import java.awt.*;

public class Bullet extends JComponent {
    private int BULLET_WIDTH = 5;
    private int BULLET_HEIGHT = 10;
    private int xPos, yPos;
    private int BULLET_SPEED = 50;
    public Bullet(int x, int y) {
        xPos = x;
        yPos = y;
        setBounds(x, y, BULLET_WIDTH, BULLET_HEIGHT);
        setBounds(x, y, BULLET_WIDTH, BULLET_HEIGHT);
    }

    public Rectangle getBounds() {
        return new Rectangle(xPos, yPos, BULLET_WIDTH, BULLET_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.CYAN);
        g.fillRect(xPos, yPos, BULLET_WIDTH, BULLET_HEIGHT);
    }

    public void move() {
        yPos -= BULLET_SPEED;
    }
}
