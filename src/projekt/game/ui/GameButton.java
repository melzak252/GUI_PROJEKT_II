package projekt.game.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameButton extends JButton {
    public GameButton(String text) {
        super(text);
//        setBackground(?new Color(255, 255, 255, 0));
//        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusable(false);
        setMinimumSize(new Dimension(100, 30));
        setSize(new Dimension(100, 30));
        setPreferredSize(new Dimension(100, 30));
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(new Color(0, 179, 211));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(Color.BLACK);
            }
        });
    }
}
