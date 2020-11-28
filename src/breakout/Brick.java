package breakout;

import java.awt.*;

/**
 * Write a description of class Brick here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Brick extends Obstacle {

    public Brick(Color color, double x1, double y1) {
        super(color, x1, y1, 6.5, 4.5);
    }

    public boolean handleCollision(Ball ball) {
        super.handleCollision(ball);
        return true;
    }
}
