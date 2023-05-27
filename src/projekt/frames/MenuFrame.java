package projekt.frames;

import projekt.game.ui.GameButton;
import projekt.menu.MenuPanel;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {
    JPanel menuPanel;
    JMenuBar menuBar;
    JMenu helpMenu;
    public MenuFrame() {
        this.init();
        this.setTitle("Ships and ships!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(600, 600));
        this.setVisible(true);
        pack();
    }



    private void init() {
        menuPanel = new MenuPanel();
        add(menuPanel);

        menuBar = new JMenuBar();
        helpMenu = new JMenu("Help");
        JMenuItem rules = new JMenuItem("Rules");
        rules.addActionListener((a) -> {
            SwingUtilities.invokeLater(HelpFrame::new);
        });
        helpMenu.add(rules);
        menuBar.add(helpMenu);
        this.setJMenuBar(menuBar);
    }


}
