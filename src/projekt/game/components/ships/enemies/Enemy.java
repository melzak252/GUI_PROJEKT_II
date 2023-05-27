package projekt.game.components.ships.enemies;

import projekt.game.components.animations.Animation;
import projekt.game.components.animations.Explosion;
import projekt.game.components.functionality.IDrawable;
import projekt.game.components.functionality.weapons.Blaster;
import projekt.game.components.projectiles.Projectile;
import projekt.game.components.ships.Ship;
import projekt.util.MoveVector;
import projekt.util.exceptions.ShipException;
import projekt.util.exceptions.WeaponException;

import javax.swing.*;
import java.awt.*;

public abstract class Enemy extends Ship implements IDrawable {
    Image enemyImage;
    Long lastTimeMoveSide;
    Long lastTimeMoveDown;
    Long lastTimeHit;
    Boolean goRight = true;
    boolean animatedDeath = false;

    public Enemy(int x, int y, int width, int height, int speedX, int speedY, double health, String imagePath, double minReload, double maxReload) {
        super(x, y, width, height, speedX, speedY, health);

        setBlaster(minReload, maxReload);

        lastTimeMoveSide = System.currentTimeMillis();
        lastTimeMoveDown = System.currentTimeMillis();
        lastTimeHit = System.currentTimeMillis();

        enemyImage = new ImageIcon(imagePath).getImage();
    }

    public void setBlaster(double minReload, double maxReload){
        weapon = new Blaster(minReload, maxReload);
    }

    @Override
    public Projectile fire() throws ShipException {
        if(weapon == null) throw new ShipException("Blaster is not set for that Enemy!");
        try {
            return weapon.fire(movable.x + movable.width / 2, movable.y + movable.height);
        } catch (WeaponException e) {
            throw new ShipException(e.getMessage());
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(enemyImage, movable.x, movable.y, movable.width, movable.height, this);
        if((System.currentTimeMillis() - lastTimeHit) / 1000. <= 2.0 ) {
            g2d.setColor(Color.RED);
            g2d.drawLine(movable.x, movable.y + movable.height + 2, movable.x + movable.width, movable.y + movable.height + 2);
            g2d.setColor(Color.GREEN);
            g2d.drawLine(movable.x, movable.y + movable.height + 2, (int) (movable.x + movable.width * alive.health / alive.startHealth), movable.y + movable.height + 2);
        }
        super.draw(g);
    }

    public void move(MoveVector mv) {
        movable.move(mv);
        animations.forEach(a -> a.move(mv));
    }

    @Override
    public void getHit(Projectile p){
        alive.getDamage(p.getDamage());
        Animation newAnimation;
        lastTimeHit = System.currentTimeMillis();
        newAnimation = getHitAnimation();
        newAnimation.startAudio();

        animations.add(newAnimation);
    }

    @Override
    public Animation getDestroyAnimation() {
        animatedDeath = true;
        return new Explosion((int) movable.getCenterX(), (int) movable.getCenterY());
    }

    public boolean isAnimatedDeath() {
        return animatedDeath;
    }
}

