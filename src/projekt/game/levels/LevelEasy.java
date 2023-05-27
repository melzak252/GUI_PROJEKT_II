package projekt.game.levels;

import java.util.ArrayList;
import java.util.List;

public class LevelEasy extends LevelConfig {

    private static final Integer[][][] grids = new Integer[][][]
            {
                    {
                            {1, 1, 1, 1, 1, 1},
                            {1, 1, 1, 1, 1, 1},
                            {1, 1, 1, 1, 1, 1},
                    },
                    {
                            {2, 1, 1, 1, 2},
                            {0, 2, 1, 2, 0},
                            {0, 0, 2, 0, 0},
                    },
                    {
                            {2, 2, 2, 2, 2},
                            {2, 2, 2, 2, 2},
                    }
            };
    public static String name = "Easy";
    public static double newWaveTime = 45.0;
    public static double waveSpeed = 15;
    public static double waveMoveDownTime = 5.0;
    public static double waveMoveSideTime = 0.66;

    public LevelEasy() {
        super(grids, name, newWaveTime, waveMoveDownTime, waveMoveSideTime);
    }
}
