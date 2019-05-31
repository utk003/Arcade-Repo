import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
/**
 * Write a description of class Paddle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Paddle extends Obstacle
{
    // instance variables - replace the example below with your own
    /**
     * Constructor for objects of class Paddle
     */
    public Paddle(double x1, double y1)
    {
        super(Color.RED, x1, y1, 20, 2);
    }

    public boolean handleCollision(Ball ball)
    {
        super.handleCollision(ball);
        int sign;
        double num = ball.getVelocityX();
        double distance = ball.getX() - (getX() + 10.0);
        double relativeX = distance/10;
        double entryAngle = Math.atan2(ball.getVelocityY(), ball.getVelocityX());
        double exitAngle = entryAngle - Math.toRadians((relativeX*40));
        ball.setVelocityX(Math.cos(exitAngle));
        ball.setVelocityY(Math.sin(exitAngle));
        return false;
    }
}
