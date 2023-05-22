package projekt.game.components.ships.configs;

public abstract class EnemyConfig extends ShipConfig {
    public Integer row;
    public Integer col;
    public EnemyType enemyType;
    public Double minReload;
    public Double maxReload;
    public Double moveSide;
    public final Double moveDown = 5.0;
    public Boolean startGoRight;

    public EnemyConfig(Integer row, Integer col) {
        this.row = row;
        this.col = col;
    }
}
