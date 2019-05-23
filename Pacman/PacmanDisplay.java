import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;

/**
 * Abstract class Display - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public class PacmanDisplay extends JComponent implements KeyListener, MouseListener
{
    private static final Color BACKGROUND = Color.BLACK;
    private static final Color WALL = new Color(50, 50, 50);
    private static final Color SIDEBAR = Color.GRAY;
    
    public void keyTyped(KeyEvent e)
    {
    }

    public void keyReleased(KeyEvent e)
    {
    }

    public void keyPressed(KeyEvent e)
    {
        //e.getKeyCode()
    }

    public void mouseExited(MouseEvent e)
    {
        
    }
    
    public void mouseEntered(MouseEvent e)
    {
    }
    
    public void mouseReleased(MouseEvent e)
    {
    }
    
    public void mousePressed(MouseEvent e)
    {
    }
    
    public void mouseClicked(MouseEvent e)
    {
    }
}
