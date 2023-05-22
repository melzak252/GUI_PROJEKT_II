package projekt.game.components.ships.enemies;

import projekt.game.components.animations.Animation;
import projekt.game.components.animations.EnemyShield;
import projekt.game.components.ships.configs.EnemyType;

public class TankEnemy extends Enemy {
    public static final EnemyType enemyType = EnemyType.TANK;
    public static final int width = 32;
    public static final int height = 32;

    private static final double tankHealth = 10.;
    private static final int speedX = 5;
    private static final int speedY = 25;
    private static final String imagePath = "src/projekt/resources/ships/enemy-tank.png";
    private static final double minReload = 6.0;
    private static final double maxReload = 12.0;
    private static final double moveSide = 1.33;

    public TankEnemy(int x, int y) {
        super(x, y, width, height, speedX, speedY, tankHealth, imagePath, minReload, maxReload, moveSide);
    }

    @Override
    protected Animation getHitAnimation() {
        return new EnemyShield(movable);
    }
}
