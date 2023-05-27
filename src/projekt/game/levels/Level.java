package projekt.game.levels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Level {
    protected List<Wave> waves = new ArrayList<>();
    public double newWaveTime;
    public double waveSpeed;
    public double waveMoveDownTime;
    public double waveMoveSideTime;
    public String name;
    private int currentWave = 0;

    protected Level(String name, List<Wave> waves, double newWaveTime, double waveSpeed, double waveMoveDownTime, double waveMoveSideTime) {
        this.name = name;
        this.waves = waves;
        this.newWaveTime = newWaveTime;
        this.waveSpeed = waveSpeed;
        this.waveMoveDownTime = waveMoveDownTime;
        this.waveMoveSideTime = waveMoveSideTime;
    }

    public Wave getNextWave() {
        return waves.get(currentWave++);
    }

    public int getNumberOfWaves() {
        return waves.size();
    }

    public int getCurrentWave() {
        return currentWave;
    }

    @Override
    public String toString() {
        return "Lvl. " + name;
    }
}
