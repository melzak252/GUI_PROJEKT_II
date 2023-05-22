package projekt.game.components.functionality;

public class Alive {
    public double health;
    public double startHealth;
    public Alive(double startHealth) {
        this.startHealth = startHealth;
        this.health = startHealth;
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
