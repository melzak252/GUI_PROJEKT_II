package projekt.game;

import projekt.game.components.ships.players.PlayerType;
import projekt.game.levels.Level;
import projekt.game.levels.LevelEasy;
import projekt.game.levels.LevelHard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class SelectPanel extends JPanel {
    private final ActionListener startGameListener;
    int noPlayers;
    List<PlayerType> playersTypes = Arrays.asList(PlayerType.BASE, PlayerType.BASE);
    List<String> playerNicks = Arrays.asList("", "");
    List<Level> levels = Arrays.asList(new LevelEasy(), new LevelEasy());

    public SelectPanel(int noPlayers, ActionListener startGameListener) {
        this.startGameListener = startGameListener;
        this.noPlayers = noPlayers;
        init();
    }

    private void init() {
        for (int i = 0; i < noPlayers; i++) {
            JPanel tmp = new JPanel();
            tmp.setLayout(new BoxLayout(tmp, BoxLayout.Y_AXIS));
            JLabel label = new JLabel("Player " + (i + 1));
            label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
            tmp.add(new Box(BoxLayout.Y_AXIS));
            tmp.add(label);
            BaseSelect bs = new BaseSelect();
            int finalI = i;
            bs.addReadyListener((a) -> {
                playersTypes.set(finalI, bs.getSelectedType());
                playerNicks.set(finalI, bs.getSelectedNick());
            });
            tmp.add(bs);
            add(tmp);
            JButton startGame = new JButton("Start");
            startGame.addActionListener(startGameListener);
            add(startGame);
        }
    }

    public PlayerType getPlayerType(int i) {
        return playersTypes.get(i);
    }

    public Level getLevel(int i) {
        return levels.get(i);
    }

    public String getNick(int i) {
        return playerNicks.get(i);
    }
}
