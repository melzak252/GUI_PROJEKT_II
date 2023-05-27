package projekt.game.ui;

import projekt.game.components.ships.players.*;

import javax.swing.*;
import java.awt.*;

public class PlayerDisplay extends JPanel {
    private Player selected;

    public PlayerDisplay() {
        setOpaque(false);
    }

    public void setSelectedType(PlayerType pType) {
        selected = switch (pType){
            case RAPID -> new RapidPlayer(getWidth() / 2 - RapidPlayer.width * 2, getHeight() / 2 - RapidPlayer.height * 2);
            case BASE -> new BasePlayer(getWidth() / 2 - BasePlayer.width * 2, getHeight() / 2 - BasePlayer.height * 2);
            case PIRATE -> new PiratePlayer(getWidth() / 2 - PiratePlayer.width * 2, getHeight() / 2 - PiratePlayer.height * 2);
        };
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(selected == null) setSelectedType(PlayerType.BASE);

        selected.draw(g, 4);
    }


}
