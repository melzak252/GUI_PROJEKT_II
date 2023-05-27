package projekt.game.ui;

import javax.swing.*;
import java.awt.*;

public class GameLabel extends JLabel {
    public GameLabel(String text) {
        super(text);
        setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        setForeground(Color.cyan);
        setVerticalAlignment(SwingUtilities.TOP);
    }

    public GameLabel(String text, int size) {
        super(text);
        setFont(new Font(Font.MONOSPACED, Font.BOLD, size));
        setForeground(Color.cyan);
    }
}
