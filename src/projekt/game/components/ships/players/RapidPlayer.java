package projekt.game.components.ships.players;

import projekt.game.components.functionality.weapons.MissileGun;

public class RapidPlayer extends Player {
    private static final String imagePath = "src/projekt/resources/ships/player-rapid.png";
    private static final double reloadTime = 0.5;
    public static final int width = 32;
    public static final int height = 32;

    public RapidPlayer(int x, int y) {
        super(x, y, width, height, imagePath);
    }

    @Override
    protected void initWeapon() {
        weapon = new MissileGun(reloadTime);
    }
}