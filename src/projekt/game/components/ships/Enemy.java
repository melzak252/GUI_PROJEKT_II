package projekt.game.components.ships;

import projekt.game.components.animations.Animation;
import projekt.game.components.animations.Explosion;
import projekt.game.components.functionality.IDrawable;
import projekt.game.components.functionality.weapons.Blaster;
import projekt.game.components.projectiles.Projectile;
import projekt.util.MoveVector;
import projekt.util.exceptions.ShipException;
import projekt.util.exceptions.WeaponException;

import javax.swing.*;
import java.awt.*;

public class Enemy extends Ship implements IDrawable {
    Image enemyImage;
    Integer tmp = 0;
    Boolean tmp2 = false;
    public Enemy(int x, int y) {
        super(x, y);
        enemyImage = new ImageIcon("src/projekt/resources/ships/enemy.png").getImage();
        weapon = new Blaster(3.0 + Math.random() * 8);
    }

    @Override
    public Projectile fire() throws ShipException {
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
        super.draw(g);
    }

    public void move() {
        double dx = 0;
        if(tmp % 15 == 0){
            dx = 1.0;
            if(tmp2)dx = -1.0;
            tmp2 = !tmp2;
        }
        movable.move(new MoveVector(dx, 0));
        tmp++;
    }

    @Override
    public void getHit(Projectile p){
        targetable.getDamage(p.getDamage());
        Animation newAnimation = new Explosion(movable.x + movable.width / 2, movable.y + movable.height / 2);
        newAnimation.startAudio();
        animations.add(newAnimation);
    }

    @Override
    public Animation getDestroyAnimation() {
        return new Explosion(movable.x + movable.width / 2, movable.y + movable.height / 2);
    }
}

