package projekt.menu;

import projekt.game.ui.GameButton;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    OptionsPanel optionsPanel;
    Image backgroundImage;
    public MenuPanel() {
        setLayout(null);
        init();
        setSize(new Dimension(600, 600));
        setPreferredSize(new Dimension(600, 600));
    }

    private void init() {
        loadBackground();
        optionsPanel = new OptionsPanel();
        optionsPanel.setBounds(25, 300, 200, 300);
        setComponentZOrder(optionsPanel, 0);
        add(optionsPanel);
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
