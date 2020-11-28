package breakout;

import java.awt.*;

/**
 * Write a description of class Obstacle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Obstacle extends Shape {
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class Obstacle
     */
    public Obstacle(Color col, double x1, double y1, double w, double h) {
        super(false, col, x1, y1, w, h);
    }
    private boolean containsPoint(double x, double y) {
        return x > getX() && x < getX() + getWidth() && y > getY() && y < getY() + getHeight();
    }
    public boolean overlapsWith(Ball ball) {
        return containsPoint(ball.getX(), ball.getY() + ball.getHeight() / 2) ||
                containsPoint(ball.getX() + ball.getWidth(), ball.getY() + ball.getHeight() / 2) ||
                containsPoint(ball.getX() + ball.getWidth() / 2, ball.getY() + ball.getHeight()) ||
                containsPoint(ball.getX() + ball.getWidth() / 2, ball.getY());
    }
    public boolean handleCollision(Ball ball) {
        if (containsPoint(ball.getX(), ball.getY() + ball.getHeight() / 2) ||
                containsPoint(ball.getX() + ball.getWidth(), ball.getY() + ball.getHeight() / 2))
            ball.setVelocityX((-1) * ball.getVelocityX());
        if (containsPoint(ball.getX() + ball.getWidth() / 2, ball.getY() + ball.getHeight()) ||
                containsPoint(ball.getX() + ball.getWidth() / 2, ball.getY()))
            ball.setVelocityY((-1) * ball.getVelocityY());
        return false;
    }
}
