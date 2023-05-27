package projekt.game.ui;

import projekt.game.components.ships.players.BasePlayer;
import projekt.game.components.ships.players.PiratePlayer;
import projekt.game.components.ships.players.Player;
import projekt.game.components.ships.players.PlayerType;

import javax.swing.*;
import java.awt.*;

public class ShipSelect extends JPanel {
    PlayerType selectedPlayerType = PlayerType.BASE;
    Player selected;
    private PlayerDisplay playerImage;

    public ShipSelect() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.GREEN);

        JPanel tmp = new JPanel(new GridLayout(2, 1));
        JLabel l = new JLabel("Select your ship type:");
        JComboBox<PlayerType> playerTypes = new JComboBox<>(PlayerType.values());
        playerTypes.setSelectedItem(selectedPlayerType);

        playerTypes.addActionListener(a -> {
            selectedPlayerType = (PlayerType) playerTypes.getSelectedItem();
            if(selectedPlayerType != null) playerImage.setSelectedType(selectedPlayerType);
        });
        tmp.add(l);
        tmp.add(playerTypes);

        add(tmp, BorderLayout.CENTER);
        addShip();
    }

    private void addShip() {
        playerImage = new PlayerDisplay();

        add(playerImage, BorderLayout.EAST);
    }

    public PlayerType getSelectedType() {
        return selectedPlayerType;
    }
}
