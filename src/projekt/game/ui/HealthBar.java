package projekt.game.ui;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

public class HealthBar extends JComponent {
    double health;
    Image heart;
    Image halfHeart;
    Image emptyHeart;

    public HealthBar(double health) {
        this.health = health;
        loadImages();
        setSize((int) (Math.max(3.0, health) * 50), 50);
        setPreferredSize(new Dimension((int) (Math.max(3.0, health) * 50), 50));
        setOpaque(true);
    }

    private void loadImages() {
        heart = new ImageIcon("src/projekt/resources/gameui/heart.png").getImage();
        halfHeart = new ImageIcon("src/projekt/resources/gameui/half-heart.png").getImage();
        emptyHeart = new ImageIcon("src/projekt/resources/gameui/empty-heart.png").getImage();
    }

    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        for (int i = 0; i < Math.max(3.0, health); i++) {
            if (health - i >= 1.0) g2d.drawImage(heart, i * 31 + 50, 15, this);
            else if (health - i > 0) g2d.drawImage(halfHeart, i * 31 + 50, 15, this);
            else g2d.drawImage(emptyHeart, i * 31 + 50, 15, this);
        }
//        g2d.fillRect(0, 0, (int) (health * 41), 31);
    }
}
