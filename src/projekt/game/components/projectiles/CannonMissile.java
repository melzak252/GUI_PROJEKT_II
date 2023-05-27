package projekt.game.components.projectiles;

import projekt.game.components.animations.Animation;
import projekt.util.MoveVector;

public class CannonMissile extends Projectile {
    public static int width = 8;
    public static int height = 8;
    public static int speedX = 5;
    public static int speedY = 20;
    public static double cost = 0.5;
    public static String imagePath ="src/projekt/resources/projectiles/player-cannon-missile.png";
    public CannonMissile(int x, int y) {
        super(x, y, width, height, speedX, speedY, cost, imagePath);
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
        return 5;
    }
}