package projekt.game.levels;

public class TestLevel extends LevelConfig {

    private static final Integer[][][] grids = new Integer[][][]
            {
                {
                    {1}
                }
            };
    public static String name = "Test";
    public static double newWaveTime = 45.0;
    public static double waveSpeed = 15;
    public static double waveMoveDownTime = 5.0;
    public static double waveMoveSideTime = 0.66;

    public TestLevel() {
        super(grids, name, newWaveTime, waveMoveDownTime, waveMoveSideTime);
    }
}
