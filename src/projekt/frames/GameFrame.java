package projekt.frames;

import projekt.game.ui.GamePanel;
import projekt.game.components.ships.players.PlayerType;
import projekt.game.levels.Level;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.util.List;

public class GameFrame extends JFrame{


    int noPlayers;
    GamePanel gamePanel;
    public GameFrame(List<PlayerType> shipTypes, List<Level> levels, List<String> nicks) {
        this.init(shipTypes, levels, nicks);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
    }

    private void init(List<PlayerType> shipTypes, List<Level> levels, List<String> nicks) {
        gamePanel = new GamePanel(noPlayers);
        this.setContentPane(gamePanel);
        for(int i = 0; i < shipTypes.size(); i++){
            gamePanel.initInstance(shipTypes.get(i), levels.get(i), nicks.get(i));
        }
        for(KeyListener kl: gamePanel.getKeyListeners()) this.addKeyListener(kl);
        gamePanel.startGame();
    }



}
