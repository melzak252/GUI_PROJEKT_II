package projekt.menu;

import projekt.game.ui.GameButton;

import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends JPanel {
    public OptionsPanel() {
        setLayout(new GridLayout(7, 1));
        setOpaque(false);
        JButton b1 = new GameButton("1 Player");
        JButton b2 = new GameButton("2 Players");
        JButton b3 = new GameButton("CREATE LEVEL");
        JButton b4 = new GameButton("TOP 10");
        JButton b5 = new GameButton("QUIT");
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
    }
}
