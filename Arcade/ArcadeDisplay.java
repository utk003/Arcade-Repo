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
public class ArcadeDisplay extends JComponent implements KeyListener, MouseListener, Runnable
{
    private Game currentGame;
    private Arcade arcade;
    private JFrame frame;

    // Constructs a new display for displaying the given board
    public ArcadeDisplay(Arcade myArcade)
    {
        arcade = myArcade;

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
        this.setPreferredSize(new Dimension(533, 427));
        this.addMouseListener(this);

        frame.pack();
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g)
    {
        g.drawImage(arcade.getImage(), 0, 0, getWidth(), getHeight(), null);
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
        frame.dispose();
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
        Graphics g = getGraphics();
        g.setColor(Color.RED);
        int x = e.getX();
        int y = e.getY();
        if (x > 100 && x < 250 && y > 110 && y < 170)
        {
            arcade.make2048();
        }
        // else if (x > 100 && x < 250 && y > 180 && y < 225)
        // {
            // arcade.makePacman();
            // //PacMan
        // }
        // else if (x > 100 && x < 250 && y > 235 && y < 285)
        // {
            // arcade.makeBreakout();
            // //breakout
        // }
        // else if (x > 100 && x < 250 && y > 290 && y < 360)
        // {
            // arcade.makeTetris();
            // //tetris
        // }
        // else if (x > 275 && x < 425 && y > 110 && y < 170)
        // {
            // arcade.makeSnake();
            // //snake
        // }
        // else if (x > 275 && x < 425 && y > 180 && y < 225)
        // {
            // arcade.makeFlappyBird();
            // //flappy
        // }
        // else if (x > 275 && x < 425 && y > 235 && y < 285)
        // {
            // arcade.makePong();
            // //pong
        // }
        else if (x > 275 && x < 425 && y > 290 && y < 360)
        {
            arcade.makeMineSweeper();
            //minesweeper
        }
        
    }
    
    public void run()
    {
        try{Thread.sleep(250);}
        catch(InterruptedException ex){}
        repaint();
    }
}
