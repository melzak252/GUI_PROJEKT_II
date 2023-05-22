package projekt.game.components.ships.enemies;

import projekt.game.components.animations.Animation;
import projekt.game.components.animations.Explosion;
import projekt.game.components.ships.configs.EnemyType;

public class BaseEnemy extends Enemy {
    public static final EnemyType enemyType = EnemyType.TANK;
    public static final int width = 32;
    public static final int height = 32;
    private static final double baseHealth = 1.5;
    private static final int speedX = 5;
    private static final int speedY = 20;
    private static final String imagePath = "src/projekt/resources/ships/enemy-base.png";
    private static final double minReload = 2.0;
    private static final double maxReload = 10.0;
    private static final double moveSide = 0.66;

    public BaseEnemy(int x, int y) {
        super(x, y, width, height, speedX, speedY, baseHealth, imagePath, minReload, maxReload, moveSide);
    }

    @Override
    protected Animation getHitAnimation() {
        return new Explosion((int) movable.getCenterX(), (int) movable.getCenterY());
    }
}
