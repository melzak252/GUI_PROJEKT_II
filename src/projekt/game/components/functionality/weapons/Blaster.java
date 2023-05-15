package projekt.game.components.functionality.weapons;

import projekt.game.components.projectiles.EnemyMissile;
import projekt.util.exceptions.WeaponException;

public class Blaster extends Weapon<EnemyMissile> {
    private final Object lock = new Object();

    public Blaster(double reloadTime) {
        super(reloadTime);
    }

    @Override
    public EnemyMissile fire(int x, int y) throws WeaponException {

        if (isReloading()) throw new WeaponException("Weapon is reloading");

        lastTimeFire = System.currentTimeMillis();

        return new EnemyMissile(x - EnemyMissile.width / 2, y - EnemyMissile.height);

    }
}
