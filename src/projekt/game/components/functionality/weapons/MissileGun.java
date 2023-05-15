package projekt.game.components.functionality.weapons;

import projekt.game.components.projectiles.PlayerMissile;
import projekt.util.exceptions.WeaponException;

public class MissileGun extends Weapon<PlayerMissile> {
    public MissileGun(double reloadTime) {
        super(reloadTime);
    }

    @Override
    public PlayerMissile fire(int x, int y) throws WeaponException {
        if (isReloading()) throw new WeaponException("Weapon is reloading");

        lastTimeFire = System.currentTimeMillis();
        return new PlayerMissile(x - PlayerMissile.width / 2, y - PlayerMissile.height);

    }
}
