package tmp;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Player extends JComponent {
    public final int PLAYER_WIDTH = 32;
    public final int PLAYER_HEIGHT = 32;
    private final int PLAYER_SPEED = 10;
    private double reloadTime = 1.;
    private final Image playerImg;
    private int xPos, yPos;
    private List<Bullet> bullets = new ArrayList<>();
    Boolean isReloading = false;
    public Player(int x, int y) {
        xPos = x;
        yPos = y;
        setBounds(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
        ImageIcon ii = new ImageIcon("src/resources/player.png");
        playerImg = ii.getImage();
        System.out.println(playerImg);
        setVisible(true);
    }

    public Rectangle getBounds() {
        return new Rectangle(xPos, yPos, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(playerImg, xPos, yPos, this);
        g.drawRect(xPos, yPos, PLAYER_WIDTH, PLAYER_HEIGHT);
        g.setColor(isReloading? Color.RED: Color.GREEN);
        g.fillOval(xPos + PLAYER_WIDTH, yPos, 5, 5);
        bullets.forEach(b -> b.paintComponent(g));
    }

    public void move(double[] moveVector, int boardWidth, int boardHeight) {
        if (moveVector.length < 2) return;
        double dX = moveVector[0];
        double dY = moveVector[1];

        xPos += PLAYER_SPEED * dX;
        xPos = Math.min(boardWidth - 2 * PLAYER_WIDTH, xPos);
        xPos = Math.max(PLAYER_WIDTH / 2, xPos);

        yPos += PLAYER_SPEED * dY;
        yPos = Math.min(boardHeight - 3 * PLAYER_HEIGHT, yPos);
        yPos = Math.max(PLAYER_HEIGHT / 2, yPos);
    }

    public Boolean getIsReloading(){
        return isReloading;
    }

    public int getXPos(){
        return xPos;
    }

    public int getYPos(){
        return yPos;
    }

    public void shot(){
        if(isReloading) return;
        Bullet newBullet = new Bullet(xPos + PLAYER_WIDTH/2, yPos);
        add(newBullet);
        bullets.add(newBullet);
        reload();
    }

    public void reload() {
        isReloading = true;
        Thread r = new Thread(() -> {
            try {
                Thread.sleep((long) (reloadTime * 1000L));
                isReloading = false;
            } catch (InterruptedException e) {
                isReloading = false;
            }
        });
        r.start();
    }

    public List<Bullet> getBullets() {
        return bullets;
    }
}
