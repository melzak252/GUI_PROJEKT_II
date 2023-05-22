package projekt.game.components.animations;

import projekt.game.components.functionality.Movable;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EnemyShield extends Animation {

    public EnemyShield(Movable m) {
        super((int) m.getCenterX(), (int) m.getCenterY(), m.width, m.height, m.speedX, m.speedY);
    }

    @Override
    void loadSprites() {
        sprites.add(new ImageIcon("src/projekt/resources/ships/enemy_shield/shield-frame-0.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/enemy_shield/shield-frame-1.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/enemy_shield/shield-frame-2.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/enemy_shield/shield-frame-3.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/enemy_shield/shield-frame-4.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/enemy_shield/shield-frame-5.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/enemy_shield/shield-frame-6.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/enemy_shield/shield-frame-7.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/enemy_shield/shield-frame-8.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/enemy_shield/shield-frame-9.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/enemy_shield/shield-frame-10.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/enemy_shield/shield-frame-11.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/enemy_shield/shield-frame-12.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/enemy_shield/shield-frame-13.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/enemy_shield/shield-frame-14.png").getImage());
        sprites.add(new ImageIcon("src/projekt/resources/ships/enemy_shield/shield-frame-15.png").getImage());
    }

    @Override
    public void paintComponent(Graphics g) {
        if (sprites.size() == 0) {
            isFinished = true;
            return;
        }
        Graphics2D g2d = (Graphics2D) g.create();
        Image sprite = sprites.poll();
        g2d.drawImage(sprite, movable.x, movable.y, this);
//        g2d.drawRect(x, y, getAnimationWidth(), getAnimationHeight());
    }

    public void startAudio() {
        try {

            AudioInputStream sound = AudioSystem.getAudioInputStream(new File("src/projekt/resources/ships/shield/shield-sound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-30f );
            clip.start();

        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
}
