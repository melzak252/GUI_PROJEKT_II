package projekt.menu;

import projekt.game.ui.PrepareGame;
import projekt.game.ui.Top10View;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    OptionsPanel optionsPanel;
    CardLayout cl;
    private JPanel siglePlayer;
    private JPanel doublePlayer;
    private JPanel top10;
    private JPanel createLevel;

    public MenuPanel() {
        cl = new CardLayout();
        setLayout(cl);
        init();
        setSize(new Dimension(600, 600));
        setPreferredSize(new Dimension(600, 600));
    }

    private void init() {
        optionsPanel = new OptionsPanel(this::goToOption);
        add(optionsPanel, "OPTIONS");

        siglePlayer = new PrepareGame(1, (a) -> goToOptions());
        add(siglePlayer, "SINGLE");


        doublePlayer = new PrepareGame(2, (a) -> goToOptions());
        add(doublePlayer, "DOUBLE");

        top10 = new Top10View();
        add(top10, "TOP_10");


        createLevel = new JPanel();
        add(createLevel, "CREATE_LEVEL");
    }

    private void goToOptions(){
        cl.first(this);
    }

    private void goToOption(String key){
        cl.show(this, key);
    }

}
