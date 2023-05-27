package projekt.game.ui;

import projekt.frames.GameFrame;
import projekt.game.components.ships.players.PlayerType;
import projekt.game.levels.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PrepareGame extends JPanel {
    CardLayout cl;
    int noPlayers;
    ActionListener goBack;
    List<PreparePlayer> bss = new ArrayList<>();
    LevelSelect ls;
    public PrepareGame(int noPlayers, ActionListener goBack) {
        cl = new CardLayout();
        setLayout(cl);
        this.noPlayers = noPlayers;
        this.goBack = goBack;
        init();
    }

    private void init() {
        for (int i = 0; i < noPlayers; i++) {
            PreparePlayer newBs = new PreparePlayer(
                    i + 1,
                    (a) -> goNext(),
                    (i>0)? (a) -> getBack(): (a) -> goBack.actionPerformed(a)
            );
            bss.add(newBs);
            add(newBs, "PLAYER_" + (i + 1));
        }

        ls = new LevelSelect(
                this::runGame,
                (a) -> getBack()
        );

        add(ls, "LEVEL");
    }

    private void goNext() {
        cl.next(this);
    }

    private void getBack(){
        cl.previous(this);
    }

    private void runGame(ActionEvent a) {
        List<PlayerType> playerTypes = new ArrayList<>();
        List<String> playerNicks = new ArrayList<>();
        List<Level> levels = new ArrayList<>();
        String lvlName = ls.getSelectedLevel();

        for(PreparePlayer pp: bss){
            playerTypes.add(pp.getPlayerType());
            playerNicks.add(pp.getNick());
            levels.add(Level.getLevel(lvlName));
        }

        System.out.println(levels);
        SwingUtilities.invokeLater(() -> new GameFrame(playerTypes, levels, playerNicks));

        goBack.actionPerformed(a);
    }
}
