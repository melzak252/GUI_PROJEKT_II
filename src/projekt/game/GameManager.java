package projekt.game;

import projekt.game.components.animations.Animation;
import projekt.game.components.ships.Ship;
import projekt.util.exceptions.ShipException;
import projekt.game.components.projectiles.Projectile;
import projekt.game.components.ships.Enemy;
import projekt.game.components.ships.Player;
import projekt.util.logger.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GameManager implements KeyListener {
    private final int gameHeight;
    private final int gameWidth;
    Player player;
    List<Projectile> playerProjectiles = new ArrayList<>();
    List<Projectile> enemiesProjectiles = new ArrayList<>();
    List<Enemy> enemies = new ArrayList<>();
    List<int[]> enemiesStartPoints = new ArrayList<>();
    List<Animation> animations = new ArrayList<>();
    private boolean enemiesSet = false;
    private double score = 1.0;

    public GameManager(int gameWidth, int gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        init();
    }

    private void init() {
        player = new Player(gameWidth / 2 - 16, gameHeight - 64);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                enemies.add(new Enemy((int) Math.round(Math.random() * 2) * gameWidth / 2, -32));
                enemiesStartPoints.add(new int[]{(2 +  gameWidth * i / 10), 64 * (1 + j)});
            }
        }
    }

    public void tick() {
        moveActors();
        collisions();
        deleteActors();
    }

    private void deleteActors() {
        List<Enemy> enemiesToDelete = enemies.stream().filter(Ship::isDestroyed).toList();
        List<Projectile> enemyProjectilesToDelete = enemiesProjectiles.stream().filter(p -> p.isHitTarget() || (p.getBounds().y > gameHeight)).toList();
        List<Projectile> playerProjectilesToDelete  = playerProjectiles.stream().filter(p -> p.isHitTarget() || (p.getBounds().y < 0)).toList();
        List<Animation> animationsToDelete  = animations.stream().filter(Animation::getIsFinished).toList();
        enemies.removeAll(enemiesToDelete);
        enemiesProjectiles.removeAll(enemyProjectilesToDelete);
        playerProjectiles.removeAll(playerProjectilesToDelete);
        animations.removeAll(animationsToDelete);
    }

    private void collisions() {
        playerProjectiles.forEach(p -> enemies.forEach(e -> {
            if(e.getBounds().intersects(p.getBounds())){
                Animation projectileHitAnimation = p.hit();
                e.getHit(p);
                score += Math.min(e.getMaxHealth(), p.getDamage());
            }
        }));
        enemiesProjectiles.forEach(p -> {
            if(player.getBounds().intersects(p.getBounds())){
                p.hit();
                player.getHit(p);
            }
        });
    }

    private void moveActors() {
        player.move();
        moveEnemies();
        playerProjectiles.forEach(Projectile::move);
        enemiesProjectiles.forEach(Projectile::move);
    }

    private void moveEnemies() {
        if(!enemiesSet){
            enemiesSet = true;
            for (int i = 0; i < enemies.size(); i++) {
                enemies.get(i).moveTo(enemiesStartPoints.get(i)[0], enemiesStartPoints.get(i)[1]);
                enemiesSet = enemiesSet && enemies.get(i).getBounds().x == enemiesStartPoints.get(i)[0] && enemies.get(i).getBounds().y == enemiesStartPoints.get(i)[1];
            }
        } else enemies.forEach(e -> e.move());

        enemies.forEach(e -> {
            if(!e.isReloading()) {
                try {
                    Projectile newProjectile = e.fire();
                    enemiesProjectiles.add(newProjectile);
                } catch (ShipException ex) {

                }
            }
        });
    }

    public void drawActors(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        playerProjectiles.forEach(p -> p.draw(g));
        enemiesProjectiles.forEach(p -> p.draw(g));
        player.draw(g);
        enemies.forEach(e -> e.draw(g));
        animations.forEach(a -> a.paintComponent(g));
    }


    public Player getPlayer() {
        return player;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int kCode = e.getKeyCode();

        switch (kCode) {
            case KeyEvent.VK_SPACE -> {
                try {
                    Projectile newProjectile = player.fire();
                    playerProjectiles.add(newProjectile);
                } catch (ShipException ex) {
                    Logger.error(ex);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public double getScore() {
        return score;
    }
}
