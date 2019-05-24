import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;
/**
 * Write a description of class Bird here.
 *
 * @author Aditya Singhvi
 * @version (a version number or a date)
 */
public class Bird
{
    private int yLoc;
    private int angle;
    private double ySpeed;
    
    private ArrayList<Image> sprites;
    private Image currentImage;
    
    private FloppyGame game;
    
    private static final int GRAVITY = 250;
    private final double TIME_INTERVAL;
    private static final int JUMP_SPEED = -150;
    
    public Bird(FloppyGame myGame)
    {
        game = myGame;
        TIME_INTERVAL = game.getTimeInterval();
        angle = 0;
        yLoc = 180;
        ySpeed = 0;
        sprites = new ArrayList<Image>();
        sprites.add(new ImageIcon("assets/bluebird-downflap.png").getImage());
        sprites.add(new ImageIcon("assets/bluebird-midflap.png").getImage());
        sprites.add(new ImageIcon("assets/bluebird-upflap.png").getImage());
        flap();
        fall();
    }
    
    public void fall()
    {
           yLoc = (int) (yLoc + (ySpeed * TIME_INTERVAL) + 
                       (GRAVITY * Math.pow(TIME_INTERVAL, 2)  * 0.5));
           ySpeed = ySpeed + (GRAVITY * TIME_INTERVAL);
           flap(); 
           // try 
           // {
               // Thread.sleep((int) (1000 * TIME_INTERVAL));
           // }
           // catch (InterruptedException ex) {}
    }
    
    private void flap()
    {
        currentImage = sprites.remove(0);
        sprites.add(currentImage);
    }
    
    public void jump()
    {
        ySpeed = JUMP_SPEED;
    }
    
    public int getYLoc()
    {
        return yLoc;
    }
    
    public int getAngle()
    {
        return angle;
    }
    
    public Image getImage()
    {
        return currentImage;
    }
    
    
}
