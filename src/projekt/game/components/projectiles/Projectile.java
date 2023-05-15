package projekt.game.components.projectiles;

import projekt.game.components.animations.Animation;
import projekt.game.components.functionality.IDrawable;
import projekt.game.components.functionality.Movable;

import javax.swing.*;
import java.awt.*;

public abstract class Projectile extends JComponent implements IDrawable {
    protected Movable movable;
    protected Image projectileImage;
    protected String imagePath;
    public boolean hitTarget = false;
    public Projectile(int x, int y, int width, int height, int speed, String imagePath) {
        this.movable = new Movable(x, y, width, height, speed);
        this.imagePath = imagePath;
        loadImage();
    }

    private void loadImage() {
        projectileImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    public Rectangle getBounds() {
        return movable.getBounds();
    }

    @Override
    public void draw(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawRect(movable.x, movable.y, movable.width, movable.height);
        g2d.drawImage(projectileImage, movable.x, movable.y, movable.width, movable.height, this);
    }

    public abstract void move();

    public abstract Animation hit();

    public boolean isHitTarget(){
        return hitTarget;
    }

    public abstract double getDamage();
}
