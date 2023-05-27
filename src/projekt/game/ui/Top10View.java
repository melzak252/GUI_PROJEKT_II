package projekt.game.ui;

import projekt.game.components.ships.players.Player;
import projekt.util.Top10Logger;

import javax.swing.*;

public class Top10View extends JPanel {
    public Top10View() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        init();
    }

    private void init() {

        JLabel jl = new JLabel("Top 10 players:");
        add(jl);
        DefaultListModel<Top10Logger.Player> playerListModel = new DefaultListModel<>();
        JList<Top10Logger.Player> playerJList = new JList<>(playerListModel);

        for(Top10Logger.Player p: Top10Logger.getTop10()){
            playerListModel.addElement(p);
        }
        JScrollPane sp = new JScrollPane(playerJList);
        add(sp);
    }
}
