import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ball extends Shape
{
    // instance variables - replace the example below with your own
    private double velX;
    private double velY;
    /**
     * Constructor for objects of class Ball
     */
    public Ball(double xCoord, double yCoord)
    {
        super(true, Color.WHITE, xCoord, yCoord, 2, 2);
        velX=.309;
        velY=-.951;
    }
    public void move()
    {
        setX(getX()+velX);
        setY(getY()+velY);
    }

    public double getVelocityX()
    {
        return velX;
    }

    public double getVelocityY()
    {
        return velY;
    }

    public void setVelocityX(double newVelX)
    {
        velX=newVelX;
    }

    public void setVelocityY(double newVelY)
    {
        velY=newVelY;
    }
    

}
