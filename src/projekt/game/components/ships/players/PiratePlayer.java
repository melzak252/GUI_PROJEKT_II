package projekt.game.components.ships.players;

import projekt.game.components.functionality.weapons.Cannon;
import projekt.game.components.functionality.weapons.MissileGun;

public class PiratePlayer extends Player {
    private static final String imagePath = "src/projekt/resources/ships/player-pirate.png";
    private static final double reloadTime = 2.0;
    public static final int width = 25;
    public static final int height = 32;

    public PiratePlayer(int x, int y) {
        super(x, y, width, height, imagePath);
    }

    @Override
    protected void initWeapon() {
        weapon = new Cannon(reloadTime);
    }
}