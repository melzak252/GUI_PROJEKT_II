package projekt.game.components.functionality;

import projekt.game.ui.HealthBar;
import projekt.util.MoveVector;

import java.awt.*;

public class Movable {
    public int x;
    public int y;
    public int speedY;
    public int speedX;
    public int width;
    public int height;
    private int maxMapWidth;
    private int minMapWidth;
    private int maxMapHeight;
    private int minMapHeight;
    private boolean maxDimensionsSet = false;

    public Movable(int x, int y, int width, int height, int speedX, int speedY) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.width = width;
        this.height = height;
    }

    public void setMapBorders(int minW, int maxW, int minH, int maxH) {
        this.maxMapHeight = maxH;
        this.minMapHeight = minH;
        this.maxMapWidth = maxW;
        this.minMapWidth = minW;
        this.maxDimensionsSet = true;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void move(MoveVector mv) {
        x += mv.x * speedX;
        y += mv.y * speedY;

        if (this.maxDimensionsSet) {
            x = Math.min(maxMapWidth - width, x);
            x = Math.max(minMapWidth, x);

            y = Math.min(maxMapHeight - height, y);
            y = Math.max(minMapHeight, y);
        }
    }

    public void moveTo(int x, int y) {
        int dx = x - this.x;
        int dy = y - this.y;
        double l = Math.sqrt(dx * dx + dy * dy);
        double aX = 1. * dx / speedX;
        double aY = 1. * dy / speedY;
        this.x += speedX * dx / l;
        this.y += speedY * dy / l;
        if (aX <= 1.0) {
            this.x = x;
        }

        if (aY <= 1.0){
            this.y = y;
        }
    }

    public double getCenterX(){
        return x + width / 2.;
    }


    public double getCenterY(){
        return y + height / 2.;
    }
}
