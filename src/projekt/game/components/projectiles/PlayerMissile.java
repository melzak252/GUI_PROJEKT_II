package projekt.game.components.projectiles;

import projekt.game.components.animations.Animation;
import projekt.util.MoveVector;

import javax.swing.*;
import java.awt.*;

public class PlayerMissile extends Projectile {
    public static int width = 8;
    public static int height = 16;
    public PlayerMissile(int x, int y) {
        super(x, y, 8, 16, 5, "src/projekt/resources/projectiles/player-missile.png");
    }
    @Override
    public void move() {
        movable.move(new MoveVector(0, -1.0));
    }

    @Override
    public Animation hit() {
        hitTarget = true;
        return null;
    }

    @Override
    public double getDamage() {
        return 1.0;
    }
}
