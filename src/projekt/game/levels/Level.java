package projekt.game.levels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Level {
    public static HashMap<String, LevelConfig> levelConfigs = new HashMap<>();
    static {
        levelConfigs.put(LevelEasy.name, new LevelEasy());
        levelConfigs.put(LevelHard.name, new LevelHard());
        levelConfigs.put(TestLevel.name, new TestLevel());
    }
    private List<Wave> waves = new ArrayList<>();
    public String name;
    public double newWaveTime;
    public double waveMoveDownTime;
    public double waveMoveSideTime;
    public int currentWave = 0;

    private Level(){

    }

    private Level(LevelConfig levelConfig) {
        for(Integer[][] g: levelConfig.grids){
            waves.add(new Wave(g[0].length, g.length, g));
        }
        name = levelConfig.name;
        newWaveTime = levelConfig.newWaveTime;
        waveMoveSideTime = levelConfig.waveMoveSideTime;
        waveMoveDownTime = levelConfig.waveMoveDownTime;
    }

    public static Level getLevel(String name){
        if(!levelConfigs.containsKey(name)) return null;

        return new Level(levelConfigs.get(name));
    }

    public int getCurrentWave() {
        return currentWave;
    }

    public int getNumberOfWaves() {
        return waves.size();
    }

    public Wave getNextWave(){
        return waves.get(currentWave++);
    }
}
