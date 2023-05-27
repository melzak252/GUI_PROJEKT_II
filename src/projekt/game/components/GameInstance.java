package projekt.game.components;

import projekt.game.components.animations.Animation;
import projekt.game.components.ships.Ship;
import projekt.game.components.ships.configs.EnemyType;
import projekt.game.components.ships.enemies.BaseEnemy;
import projekt.game.components.ships.enemies.TankEnemy;
import projekt.game.components.ships.players.*;
import projekt.game.levels.Level;
import projekt.game.levels.Wave;
import projekt.game.ui.GameBackground;
import projekt.game.ui.UIManager;
import projekt.util.MoveVector;
import projekt.util.Top10Logger;
import projekt.util.exceptions.ShipException;
import projekt.game.components.projectiles.Projectile;
import projekt.game.components.ships.enemies.Enemy;
import projekt.util.logger.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameInstance extends JPanel implements KeyListener {
    public final static int gameHeight = 800;
    public final static int gameWidth = 500;
    private final int frames = 30;
    private String playerName;
    Level level;
    Timer timer;
    boolean isWSAD = true;
    // UI
    GameBackground background;
    UIManager ui;
    PlayerType playerType = PlayerType.BASE;

    // GameState
    private GameState gameState = GameState.CREATED;
    private double score = 0.0;

    // Actors
    Player player;
    List<Enemy> enemies = new ArrayList<>();
    HashMap<int[], Enemy> enemiesToSet = new HashMap<int[], Enemy>();
    List<Projectile> playerProjectiles = new ArrayList<>();
    List<Projectile> enemiesProjectiles = new ArrayList<>();
    List<Animation> animations = new ArrayList<>();
    private long lastWaveInitTime;
    boolean enemiesSet;
    private long lastMoveDownTime;
    private long lastMoveSideTime;
    boolean enemiesGoRight;
    private boolean playerGoLeft;
    private boolean playerGoRight;
    private long startInitTime;
    private long startGameTime;
    private boolean pressedMoveButton;
    private double gameTime;

    public GameInstance(Level level, PlayerType playerType, String playerName, boolean isWSAD) {
        Logger.info("Created game instance on level:  " + level.name + "! Player Name: " + playerName);
        this.playerName = playerName;
        setSize(new Dimension(gameWidth, gameHeight));
        setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.isWSAD = isWSAD;
        this.playerType = playerType;
        this.level = level;

        init();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), gameWidth, gameHeight);
    }

    private void init() {
        initBackground();
        initUI();
        initTimer();
    }

    private void initTimer() {
        timer = new Timer(1000 / frames, (a) -> tick());
        timer.start();
    }

    private void initUI() {
        ui = new UIManager(gameWidth, gameHeight, gameState);
        this.add(ui);
        setComponentZOrder(ui, 0);
    }

    public void start() {
        gameState = GameState.INIT;
        spawnPlayer();
        spawnEnemies();
        startInitTime = System.currentTimeMillis();
    }

    private void spawnPlayer() {
        player = switch (playerType) {
            case RAPID -> new RapidPlayer(gameWidth/2 - RapidPlayer.width / 2, gameHeight - 2 * RapidPlayer.height);
            case BASE -> new BasePlayer(gameWidth/2 - BasePlayer.width / 2, gameHeight - 2 * BasePlayer.height);
            case PIRATE -> new PiratePlayer(gameWidth/2 - PiratePlayer.width / 2, gameHeight - 2 * PiratePlayer.height);
        };

        ui.addGoLeftListener((a) -> {
            playerGoLeft = true;
            pressedMoveButton = true;
        });

        ui.addGoRightListener((a) -> {
            playerGoRight = true;
            pressedMoveButton = true;
        });

        ui.addShootListener((a) -> {
            try {
                Projectile newProjectile = player.fire();
                score -= newProjectile.getCost();
                playerProjectiles.add(newProjectile);
            } catch (ShipException ex) {
                Logger.error(ex);
            }
        });
    }

    private void spawnEnemies() {
        initNextWave();
    }

    private void initNextWave() {
        if (level.getCurrentWave() >= level.getNumberOfWaves()) return;

        Wave wave = level.getNextWave();
        for (int i = 0; i < wave.height; i++) {
            int y = 50 + i * 50;
            for (int j = 0; j < wave.width; j++) {
                int x = 50 + ((gameWidth - 70) / wave.width) * j;
                EnemyType eType = wave.getEnemyType(i, j);
                if (eType != null) {
                    enemiesToSet.put(new int[]{x, y}, switch (eType) {
                        case BASE -> new BaseEnemy(x, y - ((wave.height + 1) * 50 + 50));
                        case TANK -> new TankEnemy(x, y - ((wave.height + 1) * 50 + 50));
                        case SNIPER -> new TankEnemy(x, y - ((wave.height + 1) * 50 + 50));
                    });
                }
            }
        }

        lastWaveInitTime = System.currentTimeMillis();
    }

    private void initBackground() {
        background = new GameBackground(gameWidth, gameHeight);
        add(background);
    }

    public void tick() {
        if (gameState.equals(GameState.INIT)) {
            if ((System.currentTimeMillis() - startInitTime) / 1000. > 3.0) {
                gameState = GameState.RUNNING;
                startGameTime = System.currentTimeMillis();
            }
        }

        if (gameState.equals(GameState.RUNNING)) {
            moveActors();
            playerActions();
            enemiesActions();
            collisions();
            deleteActors();
            checkWinConditions();
        }
        updateGameUI();
        repaint();
    }

    private void updateGameUI() {
        ui.setGameState(gameState);
        switch(gameState){
            case INIT -> ui.setInitParams((System.currentTimeMillis() - startInitTime) / 1000.);
            case RUNNING -> ui.setGameParams(score, (System.currentTimeMillis() - startGameTime) / 1000., player.getHealth());
            case LOSE -> ui.setGameOverParams(gameTime, score);
            case WIN -> ui.setWinGame(gameTime, score, player.getHealth(), playerName);
        }

    }

    private void enemiesActions() {
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

    private void playerActions() {
    }

    private void checkWinConditions() {
        if(enemies.stream().allMatch(Ship::isPepsi) && enemiesToSet.size() == 0){
            if(level.getCurrentWave() >= level.getNumberOfWaves()){
                gameState = GameState.WIN;
                background.stop();
                gameTime = (System.currentTimeMillis() - startGameTime) / 1000.;
                Top10Logger.manageWinner(playerName, score, player.getHealth(), gameTime);
                Logger.info("Player: " + playerName + " won with s: " + score + " and with h: " + player.getHealth());
                return;
            }
            initNextWave();
        }

        if(enemies.stream().anyMatch(e -> e.getBounds().y > gameHeight * 3 / 4)){
            gameState = GameState.LOSE;
            background.stop();
            gameTime = (System.currentTimeMillis() - startGameTime) / 1000.;
            Logger.info("Player: " + playerName + " lost! Enemies got too close!");
            return;
        }

        if(player.getHealth() <= 0){
            gameState = GameState.LOSE;
            background.stop();
            gameTime = (System.currentTimeMillis() - startGameTime) / 1000.;
            Logger.info("Player: " + playerName + " lost! Enemies killed our hero!");
        }
    }

    private void deleteActors() {
        List<Enemy> enemiesToDelete = enemies.stream().filter(Ship::isPepsi).toList();
        List<Projectile> enemyProjectilesToDelete = enemiesProjectiles.stream().filter(p -> p.isHitTarget() || (p.getBounds().y > gameHeight)).toList();
        List<Projectile> playerProjectilesToDelete = playerProjectiles.stream().filter(p -> p.isHitTarget() || (p.getBounds().y < 0)).toList();
        List<Animation> animationsToDelete = animations.stream().filter(Animation::getIsFinished).toList();
        enemiesToDelete.stream().filter(e -> !e.isAnimatedDeath()).forEach(e -> animations.add(e.getDestroyAnimation()));
        enemiesProjectiles.removeAll(enemyProjectilesToDelete);
        playerProjectiles.removeAll(playerProjectilesToDelete);
        animations.removeAll(animationsToDelete);
    }

    private void collisions() {
        playerProjectiles.forEach(p -> getAliveEnemies().forEach(e -> {
            if (e.getBounds().intersects(p.getBounds())) {
                Animation projectileHitAnimation = p.hit();
                e.getHit(p);
                if(e.isPepsi()){
                    score += e.getStartHealth();
                }
            }
        }));
        enemiesProjectiles.forEach(p -> {
            if (player.getBounds().intersects(p.getBounds())) {
                p.hit();
                player.getHit(p);
            }
        });
    }

    private void moveActors() {
        movePlayer();
        moveEnemies();
        moveProjectiles();
    }

    private void moveProjectiles() {
        playerProjectiles.forEach(Projectile::move);
        enemiesProjectiles.forEach(Projectile::move);
    }

    private void movePlayer() {
        MoveVector mv = new MoveVector();
        mv.x -= playerGoLeft ? 1 : 0;
        mv.x += playerGoRight ? 1 : 0;
        if(pressedMoveButton){
            playerGoRight = false;
            playerGoLeft = false;
            pressedMoveButton = false;
        }
        player.move(mv);
    }

    private void moveEnemies() {
        if ((System.currentTimeMillis() - lastWaveInitTime) / 1000. > level.newWaveTime) initNextWave();

        // Standard enemies
        MoveVector mv = new MoveVector();
        if ((System.currentTimeMillis() - lastMoveDownTime) / 1000. > level.waveMoveDownTime) {
            mv.y += 1.0;
            lastMoveDownTime = System.currentTimeMillis();
        }
        if ((System.currentTimeMillis() - lastMoveSideTime) / 1000. > level.waveMoveSideTime) {
            mv.x += enemiesGoRight ? 1. : -1.;
            enemiesGoRight = !enemiesGoRight;
            lastMoveSideTime = System.currentTimeMillis();
        }

        getAliveEnemies().forEach(e -> e.move(mv));
        // Enemies to set
        enemiesSet = true;
        enemiesToSet.forEach((startPoints, enemy) -> {
            enemy.moveTo(startPoints[0], startPoints[1]);
            enemiesSet = enemiesSet && enemy.getBounds().x == startPoints[0] && enemy.getBounds().y == startPoints[1];
        });
        if (enemiesSet) {
            enemies.addAll(enemiesToSet.values());
            enemiesToSet = new HashMap<>();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameState.equals(GameState.RUNNING)) return;
        int kCode = e.getKeyCode();
        if (isWSAD) {
            switch (kCode) {
                case KeyEvent.VK_A -> playerGoLeft = true;
                case KeyEvent.VK_D -> playerGoRight = true;
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
        } else {
            switch (kCode) {
                case KeyEvent.VK_LEFT -> playerGoLeft = true;
                case KeyEvent.VK_RIGHT -> playerGoRight = true;
                case KeyEvent.VK_UP -> {
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
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!gameState.equals(GameState.RUNNING)) return;
        int kCode = e.getKeyCode();
        if (isWSAD) {
            switch (kCode) {
                case KeyEvent.VK_A -> playerGoLeft = false;
                case KeyEvent.VK_D -> playerGoRight = false;
            }
        } else {
            switch (kCode) {
                case KeyEvent.VK_LEFT -> playerGoLeft = false;
                case KeyEvent.VK_RIGHT -> playerGoRight = false;
            }
        }

    }

    private List<Enemy> getAliveEnemies() {
        return enemies.stream().filter(e -> !e.isPepsi()).toList();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawActors(g);
    }

    public void drawActors(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        background.paint(g);

        if(player != null) player.draw(g);
        getAliveEnemies().forEach(e -> e.draw(g));
        enemiesToSet.forEach((k, e) -> {
            if (!e.isPepsi()) e.draw(g);
        });
        playerProjectiles.forEach(p -> p.draw(g));
        enemiesProjectiles.forEach(p -> p.draw(g));
        animations.forEach(a -> a.paintComponent(g));

        ui.paint(g);
    }
}
