package projekt.game.components.ships;

import projekt.game.components.animations.Animation;
import projekt.game.components.functionality.IDrawable;
import projekt.game.components.functionality.Movable;
import projekt.game.components.functionality.Alive;
import projekt.game.components.functionality.weapons.Weapon;
import projekt.game.components.projectiles.Projectile;
import projekt.util.exceptions.ShipException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Ship extends JComponent implements IDrawable {
    protected Movable movable;
    protected Weapon weapon;
    protected Alive alive;
    protected List<Animation> animations = new ArrayList<>();

    public Ship(int x, int y, int width, int height, int speedX, int speedY, double health) {
        this.movable = new Movable(x, y, width, height, speedX, speedY);
        movable.setMapBorders(0, 500, 0, 800);
        alive = new Alive(health);
    }

    @Override
    public Rectangle getBounds() {
        return movable.getBounds();
    }

    public void moveTo(int x, int y) {
        movable.moveTo(x, y);
    }

    public abstract Projectile fire() throws ShipException;

    public boolean isReloading() {
        return weapon.isReloading();
    }

    public abstract void getHit(Projectile p);

    public abstract Animation getDestroyAnimation();

    @Override
    public void draw(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        animations.forEach(a -> a.paintComponent(g));
        List<Animation> toDelete = animations.stream().filter(Animation::getIsFinished).toList();
        animations.removeAll(toDelete);
        weapon.drawReload(g, movable.x, movable.y + movable.height + 5, movable.width);
    }

    public double getStartHealth() {
        return alive.startHealth;
    }

    public double getHealth() {
        return Math.max(alive.health, 0);
    }
    protected abstract Animation getHitAnimation();

    @Override
    public int getWidth() {
        return movable.width;
    }
    @Override
    public int getHeight() {
        return movable.height;
    }

    public boolean isPepsi() {
        return alive.health <= 0;
    }

    public double getCooldown() {
        return weapon.getCooldown();
    }
}
