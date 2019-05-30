import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;

/**
 * 
 */
public class FloppyDisplay extends JComponent implements KeyListener, MouseListener
{
    private static final Image BACKGROUND = 
            new ImageIcon("Assets/FlappyBird/background-day.png").getImage();
    
    private static final int BIRDSIZE = 15;
    private static final int PIPEWIDTH = 25;

    private FloppyGame game;
    private Bird bird;
    private ArrayList<PipePair> pipes;
    
    private JFrame frame;
    
    private boolean hasRendered;

    // Constructs a new display for displaying the given board
    public FloppyDisplay(FloppyGame myGame)
    {
        game = myGame;
        bird = game.getBird();
        pipes = game.getPipes();
        
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
        this.setPreferredSize(new Dimension(288, 512));
        this.addMouseListener(this);

        frame.pack();
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g)
    {
        // g.setColor(BACKGROUND);
        // g.fillRect(0, 0,  frame.getWidth(), frame.getHeight());
        g.drawImage(BACKGROUND, 0, 0, frame.getWidth(), frame.getHeight(), null);
        drawBird(g, bird.getImage(), 30, bird.getYLoc());

        for (PipePair pipe : pipes)
        {
            int x = pipe.getXLoc();
            
            int bottomY = pipe.getBottomY();
            int topHeight = pipe.getTopY();
            
            drawPipe(g, pipe.getTopPipe(), x, 0, topHeight);
            drawPipe(g, pipe.getBottomPipe(), x, bottomY, 320);
        }
        
        if (game.hasEnded())
        {
            g.setFont(new Font("ArcadeClassic", Font.PLAIN, 45));
            g.setColor(Color.WHITE);
            g.drawString("You   Lost!", 50, 170);
            g.drawString("Score: " + game.getScore(), 45, 250);
            g.setFont(new Font("ArcadeClassic", Font.PLAIN, 35));
            g.drawString("Highscore: " + game.getHighScore(), 30, 310);
        }
        else
        {
            g.setFont(new Font("ArcadeClassic", Font.PLAIN, 20));
            g.setColor(Color.WHITE);
            g.drawString("Score: " + game.getScore(), 80, 400);
            g.drawString("Highscore: " + game.getHighScore(), 80, 450);
        }
        hasRendered = true;
    }

    public void print(String text)
    {
        Graphics g = getGraphics();
        g.setFont(new Font("ArcadeClassic", Font.PLAIN, 80));
        g.setColor(Color.WHITE);
        g.drawString(text, 100, 200);
    }
    
    public void clearText()
    {
        Graphics g = getGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());
        paintComponent(g);
    }
    
    private void drawBird(Graphics g, Image im, int x, int y)
    {
        g.drawImage(im, x, y, BIRDSIZE, BIRDSIZE, null);
    }
    
    private void drawPipe(Graphics g, Image im, int x, int y, int h)
    {
        g.drawImage(im, x, y, PIPEWIDTH, h, null);
    }
    
    public void setTitle(String title)
    {
        frame.setTitle(title);
    }

    public void setGame(FloppyGame myGame)
    {
        hasRendered = false;
        game = myGame;
        bird = game.getBird();
        pipes = game.getPipes();
        paintComponent(getGraphics());
        //hasRendered = false;
        // try 
            // {
                // Thread.sleep(3000);
            // }
            // catch (InterruptedException ex) {}
        // hasRendered = true;    
    }
    
    public void updateDisplay()
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
        if (game == null)
            return;
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_SPACE)
        {
            game.spacePressed();
        }
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
    
    public boolean hasRendered()
    {
        return hasRendered;
    }
    public void setRendered(boolean bool)
    {
        hasRendered = bool;
    }
    public void mouseClicked(MouseEvent e)
    {
        if (!game.hasEnded())
        {
            game.pause();
        }
        else
        {
            game.reset();
        }
    }
}
