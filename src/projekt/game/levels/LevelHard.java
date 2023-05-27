package projekt.game.levels;

import java.util.ArrayList;
import java.util.List;

public class LevelHard extends Level {

    private static final Integer[][][] gridWaves = new Integer[][][]
            {
                    {
                            {1, 1, 1, 1, 1, 1},
                            {1, 1, 1, 1, 1, 1},
                            {1, 1, 1, 1, 1, 1},
                            {1, 1, 1, 1, 1, 1},
                            {1, 1, 1, 1, 1, 1},
                    },
                    {
                            {1, 0, 0, 0, 1},
                            {1, 1, 0, 1, 1},
                            {2, 1, 1, 1, 2},
                            {0, 2, 1, 2, 0},
                            {0, 0, 2, 0, 0},
                    },
                    {
                            {1, 1, 1, 1, 1},
                            {1, 1, 1, 1, 1},
                            {1, 1, 1, 1, 1},
                            {2, 2, 2, 2, 2},
                            {2, 2, 2, 2, 2},
                    }
            };
    protected static List<Wave> waves = new ArrayList<>();
    public static double newWaveTime = 45.0;
    public static double waveSpeed = 15;
    public static double waveMoveDownTime = 5.0;
    public static double waveMoveSideTime = 0.66;

    public LevelHard() {
        super("Hard", getWaves(), newWaveTime, waveSpeed, waveMoveDownTime, waveMoveSideTime);
    }

    private static List<Wave> getWaves() {
        List<Wave> temp = new ArrayList<>();
        for (Integer[][] grid : gridWaves) {
            temp.add(new Wave(grid[0].length, grid.length, grid));
        }
        return temp;
    }
}