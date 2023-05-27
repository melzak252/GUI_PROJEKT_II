package projekt.game.ui;

import projekt.game.GamePanel;
import projekt.game.SelectPanel;
import projekt.game.components.ships.players.PlayerType;
import projekt.game.levels.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class TempPanel extends JPanel {
    GamePanel gamePanel;
    SelectPanel selectPanel;
    int noPlayers;
    final static String GAME_PANEL = "GAME";
    final static String SELECT_PANEL = "SELECT";
    public TempPanel(int noPlayers) {
        this.setLayout(new CardLayout());
        this.noPlayers = noPlayers;
        init();
        setPreferredSize(new Dimension(500, 500));
        setVisible(true);
    }

    private void init() {
        CardLayout cl = (CardLayout) this.getLayout();
        selectPanel = new SelectPanel(noPlayers, (a) -> {
            for (int i = 0; i < noPlayers; i++) {
                PlayerType pType = selectPanel.getPlayerType(i);
                Level level = selectPanel.getLevel(i);
                String nick = selectPanel.getNick(i);

                gamePanel.initInstance(pType, level, nick);

            }
            for (int i = 0; i < noPlayers; i++) {
                gamePanel.startGame();
            }
            cl.show(this, GAME_PANEL);
        });
        this.add(selectPanel, SELECT_PANEL);

        gamePanel = new GamePanel(noPlayers);
        this.add(gamePanel, GAME_PANEL);
        for(KeyListener kl: gamePanel.getKeyListeners()) this.addKeyListener(kl);
        cl.first(this);
    }
}
