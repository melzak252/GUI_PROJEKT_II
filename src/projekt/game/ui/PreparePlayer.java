package projekt.game.ui;

import projekt.game.components.ships.players.Player;
import projekt.game.components.ships.players.PlayerType;
import projekt.game.ui.LevelSelect;
import projekt.game.ui.PlayerDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PreparePlayer extends JPanel {
    ActionListener readyListener;
    JPanel shipsSelect;
    JTextField nickText;
    LevelSelect levelSelect;
    PlayerType selectedPlayerType = PlayerType.BASE;
    Player selected;
    private PlayerDisplay playerImage;
    ActionListener goNext;
    ActionListener getBack;
    int playerIdx;
    public PreparePlayer(int playerIdx, ActionListener goNext, ActionListener getBack) {
        setLayout(new BorderLayout());
        setBackground(Color.GRAY);
        setFocusable(false);
        this.playerIdx = playerIdx;
        this.goNext = goNext;
        this.getBack = getBack;
        init();
    }

    private void init() {
        JButton back = new JButton("<<");
        back.addActionListener(getBack);
        add(back, BorderLayout.WEST);

        initShip();
        JButton ready = new JButton(">>");
        ready.setPreferredSize(new Dimension(50, 50));
        ready.addActionListener(goNext);
        add(ready, BorderLayout.EAST);

        JPanel nickField = new JPanel();
        JLabel l = new JLabel("Nick: ");
        nickField.add(l);
        nickText = new JTextField("Player" + playerIdx);
        nickText.setPreferredSize(new Dimension(100, 25));
        nickField.add(nickText);
        add(nickField, BorderLayout.SOUTH);
    }


    void initShip() {
        JPanel tmp = new JPanel();
        JLabel l = new JLabel("Ships:");
        JComboBox<PlayerType> playerTypes = new JComboBox<>(PlayerType.values());
        playerTypes.setSelectedItem(selectedPlayerType);

        playerTypes.addActionListener(a -> {
            selectedPlayerType = (PlayerType) playerTypes.getSelectedItem();
            if (selectedPlayerType != null) playerImage.setSelectedType(selectedPlayerType);
        });

        tmp.add(l);
        tmp.add(playerTypes);

        add(tmp,  BorderLayout.NORTH);
        playerImage = new PlayerDisplay();
        add(playerImage, BorderLayout.CENTER);
    }

    public PlayerType getPlayerType() {
        return selectedPlayerType;
    }

    public String getNick() {
        return nickText.getText().trim();
    }
}
