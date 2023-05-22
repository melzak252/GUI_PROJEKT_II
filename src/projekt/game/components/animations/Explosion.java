package projekt.game.components.animations;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Explosion extends Animation {
    double angle;

    public Explosion(int x, int y) {
        super(x, y, 64, 64, 0, 0);
        angle = (Math.random() * 2 - 1) * Math.PI;
    }
    @Override
    void loadSprites() {
        sprites.add(new ImageIcon("src/projekt/resources/ships/ship-explosion/explosion-frame-0.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/ship-explosion/explosion-frame-1.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/ship-explosion/explosion-frame-2.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/ship-explosion/explosion-frame-3.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/ship-explosion/explosion-frame-4.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/ship-explosion/explosion-frame-5.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/ship-explosion/explosion-frame-6.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/ship-explosion/explosion-frame-7.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/ship-explosion/explosion-frame-8.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/ship-explosion/explosion-frame-9.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/ship-explosion/explosion-frame-10.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/ship-explosion/explosion-frame-11.png").getImage());
    }

    @Override
    public void paintComponent(Graphics g) {
        if (sprites.size() == 0) {
            isFinished = true;
            return;
        }
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(angle, movable.getCenterX(), movable.getCenterY());
        Image sprite = sprites.poll();
        g2d.drawImage(sprite, movable.x, movable.y, movable.width, movable.height, this);
//        g2d.drawRect(x, y, getAnimationWidth(), getAnimationHeight());
    }

    public void startAudio() {
        try {

            AudioInputStream sound = AudioSystem.getAudioInputStream(new File("src/projekt/resources/ships/ship-explosion/explosion-sound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-40f);
            clip.start();

        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
}
