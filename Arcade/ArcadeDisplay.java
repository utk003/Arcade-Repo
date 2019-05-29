import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;
/**
 * Write a description of class GameDisplay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ArcadeDisplay extends JComponent implements KeyListener, MouseListener
{
    private static final Color BACKGROUND = Color.BLACK;
    private static final Color BLOCK_COLOR = Color.WHITE;
    private static final Color BORDER = Color.DARK_GRAY;
    
    private static final int MARGIN = 1;
    private static final int BLOCK_SIZE = 15;
    private static final int SCOREBOARD = 90;
    
    private int dim;

    private Game currentGame;
    
    private JFrame frame;

    // Constructs a new display for displaying the given board
    public ArcadeDisplay(Arcade myArcade)
    {
        
        
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    createAndShowGUI();
                }
            });
          
        //Wait until display has been drawn
        try
        {
            while (frame == null || !frame.isVisible())
                Thread.sleep(1);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private void createAndShowGUI()
    {
        //Create and set up the window.
        frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.addKeyListener(this);
        frame.addMouseListener(this);

        //Display the window.
        this.setPreferredSize(new Dimension(dim, dim + SCOREBOARD));
        this.addMouseListener(this);

        frame.pack();
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g)
    {
        
    }
    
    private void drawBlock(Graphics g, int r, int c)
    {
        
    }
    
    public void setTitle(String title)
    {
        frame.setTitle(title);
    }
    
    public void update()
    {
        repaint();
    }
    
    public void keyTyped(KeyEvent e)
    {
    }

    public void keyReleased(KeyEvent e)
    {
    }

    public void close()
    {
        frame.setVisible(false);
    }
    
    public void keyPressed(KeyEvent e)
    {
        if (currentGame == null)
            return;
        
        int code = e.getKeyCode();
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
        //ADD MOUSE CLICKED CODE FOR CLICKED GAMES
    }
}
