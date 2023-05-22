package projekt.game.components.functionality.weapons;

import projekt.game.components.projectiles.EnemyMissile;
import projekt.util.exceptions.WeaponException;

public class Blaster extends Weapon<EnemyMissile> {
    double maxCooldown;
    double minCooldown;
    public Blaster(double minCooldown, double maxCooldown) {
        super();
        this.minCooldown = minCooldown;
        this.maxCooldown = maxCooldown;
        this.reloadTime = Math.random() * (maxCooldown - minCooldown) + minCooldown;
    }

    @Override
    public EnemyMissile fire(int x, int y) throws WeaponException {
        if (isReloading()) throw new WeaponException("Weapon is reloading");

        lastTimeFire = System.currentTimeMillis();
        this.reloadTime = Math.random() * (maxCooldown - minCooldown) + minCooldown;
        return new EnemyMissile(x - EnemyMissile.width / 2, y - EnemyMissile.height);

    }
}
