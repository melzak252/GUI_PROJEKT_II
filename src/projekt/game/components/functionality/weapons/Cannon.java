package projekt.game.components.functionality.weapons;

import projekt.game.components.projectiles.CannonMissile;
import projekt.game.components.projectiles.PlayerMissile;
import projekt.util.exceptions.WeaponException;

public class Cannon extends Weapon<CannonMissile> {
    public Cannon(double reloadTime) {
        super(reloadTime);
    }

    @Override
    public CannonMissile fire(int x, int y) throws WeaponException {
        if (isReloading()) throw new WeaponException("Weapon is reloading");

        lastTimeFire = System.currentTimeMillis();
        return new CannonMissile(x - CannonMissile.width / 2, y - CannonMissile.height);

    }
}