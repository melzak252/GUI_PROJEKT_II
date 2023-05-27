package projekt.game;

import projekt.game.components.ships.players.PlayerType;
import projekt.game.levels.Level;
import projekt.game.levels.LevelEasy;
import projekt.game.levels.LevelHard;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LevelSelect extends JPanel {
    public static List<Level> levels = Arrays.asList(new LevelEasy(), new LevelHard());
    private int selectedLevelIndex = 0;
    private Level selectedLevel = levels.get(0);
    public LevelSelect() {setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.GREEN);

        JPanel tmp = new JPanel(new GridLayout(2, 1));
        JLabel l = new JLabel("Select your ship type:");
        JComboBox<Level> levelsComboBox = new JComboBox<>(levels.toArray(new Level[0]));
        levelsComboBox.setSelectedIndex(selectedLevelIndex);

        levelsComboBox.addActionListener(a -> {
            selectedLevel = (Level) levelsComboBox.getSelectedItem();
        });
        
        tmp.add(l);
        tmp.add(levelsComboBox);

        add(tmp, BorderLayout.CENTER);
    }

    public Level getSelectedLevel() {
        return selectedLevel;
    }
}
