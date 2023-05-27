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

    public Weapon() {
        this.lastTimeFire = System.currentTimeMillis();
    }

    public abstract T fire(int x, int y) throws WeaponException;

    public void drawReload(Graphics g, int x, int y, int width) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x, y, x + width,  y);
        g2d.setColor(Color.YELLOW);
        g2d.drawLine(x, y, (int) (x + width * reloadRatio()),  y);
    }

    private double reloadRatio(){
        return Math.min(((System.currentTimeMillis() - lastTimeFire) / 1000.) / reloadTime, 1.0);
    }

    public boolean isReloading(){
        return (System.currentTimeMillis() - lastTimeFire) / 1000.  < reloadTime;
    }

    public double getCooldown() {
        return Math.max(reloadTime - ((System.currentTimeMillis() - lastTimeFire) / 1000.), 0);
    }
}
