package projekt.game.components.ships;

import projekt.game.components.animations.Animation;
import projekt.game.components.animations.Explosion;
import projekt.game.components.animations.Shield;
import projekt.game.components.functionality.IDrawable;
import projekt.game.components.functionality.weapons.MissileGun;
import projekt.game.components.projectiles.Projectile;
import projekt.util.MoveVector;
import projekt.util.exceptions.ShipException;
import projekt.util.exceptions.WeaponException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Ship implements IDrawable, KeyListener {
    private Image playerImage;
    private boolean isWSAD = true;
    private boolean goLeft = false;
    private boolean goRight = false;
    private boolean isReloading = false;
    private double reloadTime = 1.0;
    private final Object reloadLock = new Object();
    private double reloadProgress;
    public Player(int x, int y) {
        super(x, y);
        playerImage = new ImageIcon("src/projekt/resources/ships/player.png").getImage();
        weapon = new MissileGun(1.0);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.drawImage(playerImage, movable.x, movable.y, movable.width, movable.height, this);

        weapon.drawReload(g, movable.x + movable.width, movable.y);
        super.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int kCode = e.getKeyCode();

        switch (kCode) {
            case KeyEvent.VK_A -> goLeft = true;
            case KeyEvent.VK_D -> goRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int kCode = e.getKeyCode();

        switch (kCode) {
            case KeyEvent.VK_A -> goLeft = false;
            case KeyEvent.VK_D -> goRight = false;
        }
    }

    public void move() {
        MoveVector mv = new MoveVector();
        mv.x -= goLeft ? 1 : 0;
        mv.x += goRight ? 1 : 0;
        movable.move(mv);
        animations.forEach(a -> a.move(mv));
    }

    public boolean isReloading() {
        return isReloading;
    }

    @Override
    public void getHit(Projectile p) {
        targetable.getDamage(p.getDamage());
        Animation newAnimation = new Shield(movable);
        newAnimation.startAudio();
        animations.add(newAnimation);
    }

    @Override
    public Animation getDestroyAnimation() {
        return new Explosion(movable.x + movable.width / 2, movable.y + movable.height / 2);
    }

    public Projectile fire() throws ShipException {
        try {
            return weapon.fire(movable.x + movable.width / 2, movable.y);
        } catch (WeaponException e) {
            throw new ShipException("Ship is reloading");
        }
    }

    public double getHealth(){
        return targetable.health;
    }
}

