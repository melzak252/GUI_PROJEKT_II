package projekt.game.levels;

import java.util.ArrayList;
import java.util.List;

public class LevelHard extends LevelConfig {

    private static final Integer[][][] grids = new Integer[][][]
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
    public static String name = "Hard";
    public static double newWaveTime = 45.0;
    public static double waveMoveDownTime = 3.0;
    public static double waveMoveSideTime = 0.66;

    public LevelHard() {
        super(grids, name, newWaveTime, waveMoveDownTime, waveMoveSideTime);
    }
}