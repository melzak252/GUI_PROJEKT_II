package projekt.game.components.projectiles;

import projekt.game.components.animations.Animation;
import projekt.util.MoveVector;

public class EnemyMissile extends Projectile{
    public static int width = 8;
    public static int height = 16;
    public EnemyMissile(int x, int y) {
        super(x, y, 8, 16, 5, 10, 0, "src/projekt/resources/projectiles/enemy-missile.png");
    }
    @Override
    public void move() {
        movable.move(new MoveVector(0, 1.0));
    }

    @Override
    public Animation hit() {
        hitTarget = true;
        return null;
    }

    @Override
    public double getDamage() {
        return 0.5;
    }
}
