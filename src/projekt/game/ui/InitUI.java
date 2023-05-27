package projekt.game.ui;

import javax.swing.*;
import java.awt.*;

public class InitUI extends JPanel {
    GameLabel timeLabel;
    public InitUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 63));
        timeLabel = new GameLabel("3", 42);
        timeLabel.setVerticalAlignment(SwingConstants.CENTER);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(timeLabel, BorderLayout.CENTER);
    }

    public void setTime(double time) {
        timeLabel.setText("" + (int)time);
    }
}
