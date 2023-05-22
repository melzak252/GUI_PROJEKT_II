package projekt.game.components.ships;

import projekt.game.components.animations.Animation;
import projekt.game.components.animations.Explosion;
import projekt.game.components.animations.Shield;
import projekt.game.components.functionality.IDrawable;
import projekt.game.components.functionality.weapons.MissileGun;
import projekt.game.components.projectiles.Projectile;
import projekt.game.components.ships.configs.EnemyType;
import projekt.util.MoveVector;
import projekt.util.exceptions.ShipException;
import projekt.util.exceptions.WeaponException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Ship implements IDrawable, KeyListener {
    private Image playerImage;

    public static final int width = 32;
    public static final int height = 32;

    private static final double playerHealth = 3.;
    private static final int speedX = 10;
    private static final int speedY = 10;
    private static final String imagePath = "src/projekt/resources/ships/player.png";

    private boolean isWSAD = true;
    private boolean goLeft = false;
    private boolean goRight = false;
    public Player(int x, int y) {
        super(x, y, width, height, speedX, speedY, playerHealth);
        playerImage = new ImageIcon(imagePath).getImage();
        weapon = new MissileGun(.5);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
        g2d.drawImage(playerImage, movable.x, movable.y, movable.width, movable.height, this);
        super.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int kCode = e.getKeyCode();
        System.out.println("KEY PRESSED");

        switch (kCode) {
            case KeyEvent.VK_A -> goLeft = true;
            case KeyEvent.VK_D -> goRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int kCode = e.getKeyCode();
        System.out.println("KEY RELEASED");

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

