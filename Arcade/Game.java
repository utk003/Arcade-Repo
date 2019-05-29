import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;
/**
 * Abstract class Game - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Game
{
    protected boolean isPaused;
    protected boolean hasEnded;
    
    protected int level;
    protected int score;
    
    protected Date startTime;
    protected Date endTime;
    
    protected JComponent display;
    
    public Game()
    {
        isPaused = false;
        hasEnded = false;
        level = 0;
        score = 0;
        startTime = new Date();
    }
    
    public void pause()
    {
        isPaused = !isPaused;
    }
    
    public boolean isPaused()
    {
        return isPaused;
    }
    
    public boolean hasEnded()
    {
        return hasEnded;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public int getLevel()
    {
        return level;
    }
    
    public int getTimeElapsed()
    {
        if (endTime == null)
        {
            Date now = new Date();
            return (int) (now.getTime() - startTime.getTime());
        }
        else
        {
            return (int) (endTime.getTime() - startTime.getTime());
        }
    }
    
    public abstract void play();
}

