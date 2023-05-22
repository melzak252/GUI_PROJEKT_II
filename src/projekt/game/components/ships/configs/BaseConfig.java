package projekt.game.components.ships.configs;

public class BaseConfig extends EnemyConfig{
    public  Double health = 10.;
    public   final Double speedX = 5.;
    public  final Double speedY = 10.;
    public  final Integer width = 32;
    public  final Integer height = 32;
    public  final String imagePath = "src/projekt/resources/ships/enemy-tank.png";
    public  final EnemyType enemyType = EnemyType.TANK;
    public  final Double minReload = 6.0;
    public  final Double maxReload = 12.0;
    public  final Double moveSide = 0.66;

    public BaseConfig(Integer row, Integer col) {
        super(row, col);
    }
}
