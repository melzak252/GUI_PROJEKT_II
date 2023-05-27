package projekt.game.levels;

import projekt.game.components.ships.configs.EnemyConfig;
import projekt.game.components.ships.configs.EnemyType;

import java.util.ArrayList;
import java.util.List;

public class Wave {
    private final EnemyType[][] enemies;
    public int width;
    public int height;

    public Wave(int width, int height, Integer[][] grid) {
        this.width = width;
        this.height = height;
        this.enemies = new EnemyType[height][width];
        for(int i = 0; i < height; i++){
            for (int j = 0; j < width; j++) {
                EnemyType eType = switch (grid[i][j]){
                    case 1 -> EnemyType.BASE;
                    case 2 -> EnemyType.TANK;
                    case 3 -> EnemyType.SNIPER;
                    default -> null;
                };
                enemies[i][j] = eType;
            }
        }
    }
    public EnemyType[][] getEnemies(){
        return enemies;
    }

    public EnemyType getEnemyType(int i, int j) {
        return enemies[i][j];
    }
}
