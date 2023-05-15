package projekt.game.components.functionality.weapons;

import projekt.game.components.projectiles.Projectile;
import projekt.util.exceptions.WeaponException;

import java.awt.*;
import java.time.LocalDateTime;

public abstract class Weapon<T extends Projectile> {
    public double reloadTime;
    protected long lastTimeFire;

    public Weapon(double reloadTime) {
        this.reloadTime = reloadTime;
        this.lastTimeFire = System.currentTimeMillis();
    }

    public abstract T fire(int x, int y) throws WeaponException;

    public void drawReload(Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(isReloading()? Color.RED: Color.GREEN);
        g2d.fillOval(x, y, 10, 10);
    }

    public boolean isReloading(){
        return (System.currentTimeMillis() - lastTimeFire) / 1000.  < reloadTime;
    }
}
