import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
/**
 * Write a description of class Breakout here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Breakout
{
    // instance variables - replace the example below with your own
    private ShapeDisplay disp;
    private Ball ball;

    /**
     * Constructor for objects of class Breakout
     */
    public Breakout()
    {
        // initialise instance variables
        disp=new ShapeDisplay();
        disp.setTitle("Breakout");
        ball=new Ball(50,80);
        disp.add(new Obstacle(Color.BLUE, 0, 0, 4, 100));
        disp.add(new Obstacle(Color.BLUE, 0, 0, 100, 4));
        disp.add(new Obstacle(Color.BLUE, 96, 0, 4, 100));
        disp.add(ball);
        play();
    }

    public void play()
    {
        while(true)
        {
            try { Thread.sleep(25);
                ball.move();
                disp.repaint();}
            catch(InterruptedException e) { /* ignore it */ }

        }
    }
    private void checkForCollisions()

}
