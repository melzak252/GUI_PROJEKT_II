package projekt.game.ui;

import javax.swing.*;
import java.awt.*;

public class GameBackground extends JPanel {
    Image background;
    int current1Y;
    int gameWidth = 400;
    int gameHeight = 700;

    public GameBackground(int gameWidth, int gameHeight) {
        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;
        current1Y = 0;
        background = new ImageIcon("src/projekt/resources/gameui/game-background.png").getImage();
        setSize(gameWidth, gameHeight);
        setPreferredSize(new Dimension(gameWidth, gameHeight));
        setOpaque(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(background, 0, current1Y, gameWidth, gameHeight, this);
        g2d.drawImage(background, 0, current1Y - gameHeight, gameWidth, gameHeight, this);
        current1Y += 2;
        current1Y %= gameHeight;
    }

}
