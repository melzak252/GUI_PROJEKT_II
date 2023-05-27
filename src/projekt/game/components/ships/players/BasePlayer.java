package projekt.game.components.ships.players;

import projekt.game.components.functionality.weapons.MissileGun;

public class BasePlayer extends Player {
    private static final String imagePath = "src/projekt/resources/ships/player-base.png";
    private static final double reloadTime = 1.0;
    public static final int width = 32;
    public static final int height = 32;

    public BasePlayer(int x, int y) {
        super(x, y, width, height, imagePath);
    }

    @Override
    protected void initWeapon() {
        weapon = new MissileGun(reloadTime);
    }
}
