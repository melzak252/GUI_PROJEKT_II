package projekt.menu;

import projekt.game.ui.GameButton;
import projekt.util.MyActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OptionsPanel extends JPanel {
    Image backgroundImage;
    public OptionsPanel(MyActionListener pressOption) {
        loadBackground();
        setLayout(null);
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(7, 1));

        JButton b1 = new GameButton("1 Player");
        b1.addActionListener((a) -> pressOption.invoke("SINGLE"));
        JButton b2 = new GameButton("2 Players");
        b2.addActionListener((a) -> pressOption.invoke("DOUBLE"));
        JButton b3 = new GameButton("CREATE LEVEL");
        b3.addActionListener((a) -> pressOption.invoke("CREATE_LEVEL"));
        JButton b4 = new GameButton("TOP 10");
        b4.addActionListener((a) -> pressOption.invoke("TOP_10"));
        JButton b5 = new GameButton("QUIT");
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        add(panel);
        panel.setBounds(25, 300, 200, 300);
    }


    private void loadBackground() {
        backgroundImage = new ImageIcon("src/projekt/resources/gameui/menu-background.png").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(backgroundImage, 0, 0, this);
    }
}
