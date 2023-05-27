package projekt.game.ui;

import projekt.game.components.ships.players.PlayerType;
import projekt.game.levels.Level;
import projekt.game.levels.LevelEasy;
import projekt.game.levels.LevelHard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LevelSelect extends JPanel {
    private int selectedLevelIndex = 0;
    private String selectedLevel = LevelEasy.name;
    public LevelSelect(ActionListener runGame, ActionListener getBack) {
        setBackground(Color.GREEN);
        setLayout(new BorderLayout());

        JButton back = new JButton("<<");
        back.addActionListener(getBack::actionPerformed);
        add(back, BorderLayout.WEST);


        JButton start = new JButton("Start");
        start.addActionListener(runGame);
        add(start, BorderLayout.EAST);


        JPanel tmp = new JPanel();
        JLabel l = new JLabel("Select level:");
        JComboBox<String> levelsComboBox = new JComboBox<>( Level.levelConfigs.keySet().toArray(new String[0]));
        levelsComboBox.setSelectedItem(selectedLevel);

        levelsComboBox.addActionListener(a -> {
            selectedLevel = (String) levelsComboBox.getSelectedItem();
        });
        
        tmp.add(l);
        tmp.add(levelsComboBox);
        add(tmp, BorderLayout.CENTER);
    }

    public String getSelectedLevel() {
        return selectedLevel;
    }
}
