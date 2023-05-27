package projekt.game.components.ships.players;

import projekt.game.components.animations.Animation;
import projekt.game.components.animations.Explosion;
import projekt.game.components.animations.Shield;
import projekt.game.components.functionality.IDrawable;
import projekt.game.components.functionality.weapons.MissileGun;
import projekt.game.components.projectiles.Projectile;
import projekt.game.components.ships.Ship;
import projekt.game.components.ships.configs.EnemyType;
import projekt.util.MoveVector;
import projekt.util.exceptions.ShipException;
import projekt.util.exceptions.WeaponException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class Player extends Ship implements IDrawable{
    protected Image playerImage;
    private static final double playerHealth = 3.;
    private static final int speedX = 10;
    private static final int speedY = 10;
    private boolean isWSAD = true;
    private boolean goLeft = false;
    private boolean goRight = false;
    public Player(int x, int y, int width, int height, String imagePath) {
        super(x, y, width, height, speedX, speedY, playerHealth);
        playerImage = new ImageIcon(imagePath).getImage();
        initWeapon();
    }

    protected abstract void initWeapon();
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(playerImage, movable.x, movable.y, movable.width, movable.height, this);
        super.draw(g);
    }

    public void draw(Graphics g, double scale) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(playerImage, movable.x, movable.y, (int) (movable.width * scale), (int) (movable.height * scale), this);
        super.draw(g);
    }
    public void move(MoveVector mv) {
        movable.move(mv);
        animations.forEach(a -> a.move(mv));
    }

    @Override
    public void getHit(Projectile p) {
        alive.getDamage(p.getDamage());
        if(alive.health > 0) {
            Animation newAnimation = getHitAnimation();
            newAnimation.startAudio();
            animations.add(newAnimation);
        }
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
        return alive.health;
    }

    @Override
    protected Animation getHitAnimation() {
        return new Shield(movable);
    }
}

