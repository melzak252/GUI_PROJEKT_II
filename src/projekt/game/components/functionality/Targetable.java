package projekt.game.components.functionality;

import java.awt.*;

public class Targetable {
    public double health;
    public double maxHealth;
    public Targetable(double maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public double getHealth() {
        return health;
    }

    public void getDamage(double damage) {
        this.health -= damage;
    }

    public void getHeal(double heal){
        this.health += heal;
    }
}
