package projekt.game.levels;

import java.util.ArrayList;
import java.util.List;

public class LevelConfig {
    public Integer[][][] grids;
    public String name;
    public double newWaveTime;
    public double waveMoveDownTime;
    public double waveMoveSideTime;

    public LevelConfig(Integer[][][] grids, String name, double newWaveTime, double waveMoveDownTime, double waveMoveSideTime) {
        this.grids = grids;
        this.name = name;
        this.newWaveTime = newWaveTime;
        this.waveMoveDownTime = waveMoveDownTime;
        this.waveMoveSideTime = waveMoveSideTime;
    }
}
