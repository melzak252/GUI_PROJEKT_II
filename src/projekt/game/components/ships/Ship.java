package projekt.game.components.ships;

import projekt.game.components.animations.Animation;
import projekt.game.components.functionality.IDrawable;
import projekt.game.components.functionality.Movable;
import projekt.game.components.functionality.Targetable;
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
    protected Targetable targetable;
    protected List<Animation> animations = new ArrayList<>();

    public Ship(int x, int y) {
        this.movable = new Movable(x, y, 32, 32, 10);
        movable.setMapBorders(0, 400, 0, 700);
        targetable = new Targetable(3);
    }

    @Override
    public Rectangle getBounds() {
        return movable.getBounds();
    }

    public void moveTo(int x, int y) {
        movable.moveTo(x, y);
    }

    public int getSpeed() {
        return movable.speed;
    }

    public abstract Projectile fire() throws ShipException;

    public boolean isReloading() {
        return weapon.isReloading();
    }

    public abstract void getHit(Projectile p);

    public boolean isDestroyed() {
        return targetable.health <= 0;
    }

    public abstract Animation getDestroyAnimation();

    @Override
    public void draw(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        animations.forEach(a -> a.paintComponent(g));
        List<Animation> toDelete = animations.stream().filter(Animation::getIsFinished).toList();
        animations.removeAll(toDelete);
    }

    public double getMaxHealth() {
        return targetable.maxHealth;
    }
}
