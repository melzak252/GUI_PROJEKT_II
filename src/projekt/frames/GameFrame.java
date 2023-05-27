package projekt.frames;

import projekt.game.GamePanel;
import projekt.game.SelectPanel;
import projekt.game.components.ships.players.PlayerType;
import projekt.game.levels.Level;
import projekt.game.ui.TempPanel;
import projekt.util.logger.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame{


    int noPlayers;

    public GameFrame(int noPlayers) {
        this.noPlayers = noPlayers;
        this.init();
        setSize(500, 500);
        setPreferredSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        pack();

        System.out.println("HEREEEEEE");
    }

    private void init() {
        JPanel tmp = new TempPanel(noPlayers);
        setContentPane(tmp);
//        tmp.addComponentListener(new ComponentListener() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                System.out.println("HEREE");
//
//                pack();
//            }
//
//            @Override
//            public void componentMoved(ComponentEvent e) {
//
//            }
//
//            @Override
//            public void componentShown(ComponentEvent e) {
//
//            }
//
//            @Override
//            public void componentHidden(ComponentEvent e) {
//
//            }
//        });
    }


}
