package projekt.game;

import projekt.game.components.ships.players.PlayerType;
import projekt.game.levels.Level;
import projekt.game.ui.ShipSelect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BaseSelect extends JPanel {
    ActionListener readyListener;
    ShipSelect shipsSelect;
    JTextField nickText;
    LevelSelect levelSelect;
    public BaseSelect() {
        this.readyListener = readyListener;
        setLayout(new GridLayout(3, 1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setMinimumSize(new Dimension(200, 500));
        setSize(new Dimension(200, 500));
        setPreferredSize(new Dimension(200, 500));
        init();
    }

    public void addReadyListener(ActionListener readyListener){
        this.readyListener = readyListener;
    }

    private void init() {
        shipsSelect = new ShipSelect();
        add(shipsSelect);
        levelSelect = new LevelSelect();
        levelSelect.setBackground(Color.magenta);
        add(levelSelect);
        JPanel nickField = new JPanel();
        JLabel l = new JLabel("Nick: ");
        nickField.add(l);
        nickText = new JTextField();
        nickText.setPreferredSize(new Dimension(100, 25));
        nickField.add(nickText);
        JButton ready = new JButton("Gotowy");
        ready.addActionListener((a) -> {
            readyListener.actionPerformed(a);
        });
        nickField.add(ready);
        add(nickField);
    }

    public PlayerType getSelectedType() {
        return shipsSelect.getSelectedType();
    }

    public String getSelectedNick() {
        return nickText.getText().trim();
    }

    public Level getSelectedLevel(){
        return levelSelect.getSelectedLevel();
    }
}
