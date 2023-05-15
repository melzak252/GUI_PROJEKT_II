package projekt.game.components.animations;

import projekt.game.components.functionality.Movable;
import projekt.util.MoveVector;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Animation extends JComponent {
    protected Queue<Image> sprites = new LinkedList<>();
    protected Movable movable;
    protected boolean isFinished = false;

    protected Animation(int x, int y, int width, int height, int speed) {
        loadSprites();
        movable = new Movable(x - width / 2, y - height / 2, width, height, speed);
    }

    public void move(MoveVector mv){
        movable.move(mv);
    }

    @Override
    public Rectangle getBounds() {
        return movable.getBounds();
    }

    abstract void loadSprites();

    public abstract void paintComponent(Graphics g);

    public boolean getIsFinished() {
        return isFinished;
    }

    public abstract void startAudio();
}
