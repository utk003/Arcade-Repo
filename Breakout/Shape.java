import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
/**
 * Write a description of class Shape here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shape
{
    // instance variables - replace the example below with your own
    private boolean isRound;
    private Color color;
    private double x;
    private double y;
    private double width;
    private double height;
    
    public Shape(boolean round, Color col,
    double x1, double y1,
    double w, double h)
    {
        isRound=round;
        color=col;
        x=x1;
        y=y1;
        width=w;
        height=h;
    }

    public boolean isRound()
    {
        return isRound;
    }

    public Color getColor()
    {
        return color;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getWidth()
    {
        return width;
    }

    public double getHeight()
    {
        return height;
    }
    public void setX(double newX)
    {
        x=newX;
    }
    public void setY(double newY)
    {
        y=newY;
    }
}
