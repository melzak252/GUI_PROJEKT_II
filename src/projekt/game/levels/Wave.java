package projekt.game.levels;

import projekt.game.components.ships.configs.EnemyConfig;

import java.util.ArrayList;
import java.util.List;

public class Wave {
    private List<EnemyConfig> enemies = new ArrayList<>();
    public int width;
    public int height;

    public Wave(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void add(EnemyConfig ec){
        enemies.add(ec);
    }

    public List<EnemyConfig> getEnemies(){
        return enemies;
    }
}
