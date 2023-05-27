package projekt.game.ui;

import projekt.game.components.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UIManager extends JPanel{
    JPanel createdPanel;
    InitUI initPanel;
    EmptyUI pausePanel;
    GameUI gamePanel;
    EmptyUI emptyUI;
    GameOver gameOver;
    GameState gameState;
    ActionListener goLeftListener;
    ActionListener goRightListener;
    ActionListener shotListener;

    public UIManager(int gameWidth, int gameHeight, GameState gameState) {
        setLayout(new CardLayout());
        setSize(new Dimension(gameWidth, gameHeight));
        setPreferredSize(new Dimension(gameWidth, gameHeight));

        setBackground(new Color(255, 255, 255, 0));
        this.gameState = gameState;
        initPanels();
    }

    private void initPanels() {
        createdPanel = new EmptyUI();
        initPanel = new InitUI();
        pausePanel = new EmptyUI();
        gamePanel = new GameUI();
        emptyUI = new EmptyUI();
        gameOver = new GameOver();
        add(createdPanel, GameState.CREATED.name());
        add(initPanel, GameState.INIT.name());
        add(pausePanel, GameState.PAUSE.name());
        add(gamePanel, GameState.RUNNING.name());
        add(emptyUI, GameState.WIN.name());
        add(gameOver, GameState.LOSE.name());
    }

    private void changeUIPanel(GameState gameState){
        CardLayout cl = (CardLayout) getLayout();
        cl.show(this, gameState.name());
        this.gameState = gameState;
    }

    public void setInitParams(double time){
        initPanel.setTime(Math.round(3.0 - time));
    }

    public void addGoRightListener(ActionListener goRightListener){
        gamePanel.addGoRightListener(goRightListener);
    }

    public void addGoLeftListener(ActionListener goLeftListener){
        gamePanel.addGoLeftListener(goLeftListener);
    }

    public void addShootListener(ActionListener shootListener) {
        gamePanel.addShootListener(shootListener);
    }

    public void setGameState(GameState gameState) {
        if(this.gameState != gameState) changeUIPanel(gameState);
    }

    public void setGameParams(double score, double gameTime, double health) {
        gamePanel.setScore(score);
        gamePanel.setGameTime(gameTime);
        gamePanel.setHealth(health);
    }


}
