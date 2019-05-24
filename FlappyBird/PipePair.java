import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;
/**
 * Write a description of class PipePair here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PipePair
{
    private int gap;
    private int topY;
    private int bottomY;
    
    private int xLoc;
    private static final int WIDTH = 25;
    
    private Image topPipe; 
    private Image bottomPipe;
    
    private FloppyGame game;
    
    public PipePair(FloppyGame myGame, int myXLoc)
    {
        game = myGame;
        gap = 65;
        // if (gap < 45)
            // gap = 45;
            
        int minTop = 0;
        int maxTop = game.getDisplay().getHeight() - gap;
        int buffer = (int) (maxTop - (maxTop/2))/2;
        minTop += buffer;
        maxTop -= buffer;
        
        topY = (int) ((maxTop - minTop) * Math.random() + minTop);
        bottomY = topY + gap;
        
        xLoc = myXLoc;
        
        topPipe = new ImageIcon("assets/top-pipe.png").getImage();
        bottomPipe = new ImageIcon("assets/bottom-pipe.png").getImage();
    }
    
    public int getXLoc()
    {
        return xLoc;
    }
    
    public Image getTopPipe()
    {
        return topPipe;
    }
    
    public Image getBottomPipe()
    {
        return bottomPipe;
    }
    
    public void setXLoc(int newLoc)
    {
        xLoc = newLoc;
    }
    
    public int getTopY()
    {
        return topY;
    }
    
    public int getBottomY()
    {
        return bottomY;
    }
    
    public int getWidth()
    {
        return WIDTH;
    }
}
