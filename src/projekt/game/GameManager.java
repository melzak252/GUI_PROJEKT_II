package projekt.game;

import projekt.game.components.animations.Animation;
import projekt.game.components.animations.EnemyShield;
import projekt.game.components.ships.Ship;
import projekt.game.components.ships.enemies.BaseEnemy;
import projekt.game.components.ships.enemies.TankEnemy;
import projekt.game.ui.GameBackground;
import projekt.util.exceptions.ShipException;
import projekt.game.components.projectiles.Projectile;
import projekt.game.components.ships.enemies.Enemy;
import projekt.game.components.ships.Player;
import projekt.util.logger.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class GameManager extends JPanel implements KeyListener {
    private final int gameHeight = 800;
    private final int gameWidth = 500;
    Player player;
    Integer[][][] waves;
    private int currentWave;
    List<Projectile> playerProjectiles = new ArrayList<>();
    List<Projectile> enemiesProjectiles = new ArrayList<>();
    List<Enemy> enemies = new ArrayList<>();
    List<int[]> enemiesStartPoints = new ArrayList<>();
    List<Animation> animations = new ArrayList<>();
    GameBackground background;
    private boolean enemiesSet = false;
    private boolean playerSet = false;
    private double score = 0.0;
    private boolean randomStart;
    private boolean win = false;
    private boolean lose = false;
    private boolean pause = false;


    public GameManager(Integer[][][] waves, boolean randomStart) {
        setSize(new Dimension(gameWidth, gameHeight));
        setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.waves = waves;
        this.randomStart = randomStart;
        init();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), gameWidth, gameHeight);
    }

    private void init() {
        background = new GameBackground(gameWidth, gameHeight);

        player = new Player(gameWidth / 2 - 16, gameHeight - 64);
//        player = new Player(gameWidth / 2 - 16, gameHeight + 64);
        initWave();
    }

    private void initWave() {
//        enemiesStartPoints = new ArrayList<>();
        Integer[][] wave = waves[currentWave++];
        for(int i = 0; i < wave.length; i++){
            Integer[] row = wave[i];
            double widthPerEnemy = gameWidth * 1. / row.length;
            for (int j = 0; j < row.length; j++) {
                Enemy e = switch (row[j]){
                    case 1 -> new BaseEnemy(-BaseEnemy.width, -BaseEnemy.height);
                    case 2 -> new TankEnemy(-TankEnemy.width, -TankEnemy.height);
                    default -> null;
                };

                if(e != null){
                    int x = (int) (j * widthPerEnemy + widthPerEnemy/2 - e.getWidth());
                    int y = (int) (i * (e.getHeight() + 16) + 50);
                    enemies.add(e);
                    enemiesStartPoints.add(new int[]{x, y});
                }
            }
        }
        enemiesSet = false;
    }

    public void tick() {
        if(isFinished()) return;
        moveActors();
        collisions();
        deleteActors();
        checkWinConditions();
    }

    private void checkWinConditions() {
        if(enemies.stream().allMatch(Ship::isPepsi)){
            if(currentWave >= waves.length){
                win = true;
                background.stop();
                return;
            }
            initWave();
        }
        
        if(enemies.stream().anyMatch(e -> e.getBounds().y > gameHeight * 3 / 4)){
            lose = true;
            background.stop();
            return;
        }

        if(player.getHealth() <= 0){
            background.stop();
            lose = true;
        }
    }

    public boolean isFinished(){
        return win || lose;
    }

    public boolean isWin(){
        return win;
    }

    public boolean isLose(){
        return lose;
    }

    private void deleteActors() {
        List<Enemy> enemiesToDelete = enemies.stream().filter(Ship::isPepsi).toList();
        List<Projectile> enemyProjectilesToDelete = enemiesProjectiles.stream().filter(p -> p.isHitTarget() || (p.getBounds().y > gameHeight)).toList();
        List<Projectile> playerProjectilesToDelete  = playerProjectiles.stream().filter(p -> p.isHitTarget() || (p.getBounds().y < 0)).toList();
        List<Animation> animationsToDelete  = animations.stream().filter(Animation::getIsFinished).toList();
        enemiesToDelete.stream().filter(e -> !e.isAnimatedDeath()).forEach(e -> animations.add(e.getDestroyAnimation()));
        enemiesProjectiles.removeAll(enemyProjectilesToDelete);
        playerProjectiles.removeAll(playerProjectilesToDelete);
        animations.removeAll(animationsToDelete);
    }

    private void collisions() {
        playerProjectiles.forEach(p -> getAliveEnemies().forEach(e -> {
            if(e.getBounds().intersects(p.getBounds())){
                Animation projectileHitAnimation = p.hit();
                score += Math.min(e.getHealth(), p.getDamage());
                e.getHit(p);
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
                Enemy e = enemies.get(i);
                e.moveTo(enemiesStartPoints.get(i)[0], enemiesStartPoints.get(i)[1]);
                enemiesSet = enemiesSet && enemies.get(i).getBounds().x == enemiesStartPoints.get(i)[0] && enemies.get(i).getBounds().y == enemiesStartPoints.get(i)[1];
            }

        } else getAliveEnemies().forEach(Enemy::move);

        getAliveEnemies().forEach(e -> {
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
        background.paint(g);
        playerProjectiles.forEach(p -> p.draw(g));
        enemiesProjectiles.forEach(p -> p.draw(g));
        player.draw(g);
        getAliveEnemies().forEach(e -> e.draw(g));
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
        if(isFinished()) return;
        int kCode = e.getKeyCode();

        switch (kCode) {
            case KeyEvent.VK_SPACE -> {
                try {
                    Projectile newProjectile = player.fire();
                    score -= newProjectile.getCost();
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

    private List<Enemy> getAliveEnemies(){
        return enemies.stream().filter(e -> !e.isPepsi()).toList();
    }
}
