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
public class Display extends JComponent implements KeyListener, MouseListener
{
    private static final Color BACKGROUND = Color.BLACK;
    private static final Color BLOCK_COLOR = Color.WHITE;
    private static final Color BORDER = Color.DARK_GRAY;
    
    private static final int MARGIN = 1;
    private static final int BLOCK_SIZE = 10;
    private static final int SCOREBOARD = 90;
    
    private int dim;

    private SnakeGame game;
    
    private JFrame frame;

    // Constructs a new display for displaying the given board
    public Display(SnakeGame myGame)
    {
        game = myGame;
        dim = game.BOARD_DIM * ( MARGIN + BLOCK_SIZE ) + MARGIN;
        
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
        // Border
        g.setColor(BORDER);
        g.fillRect(0, SCOREBOARD, dim, dim);
        
        // Score Stuff
        g.setFont(new Font("Avenir Next", Font.BOLD, 10));
        g.drawString("SCORE:", (dim - 90)/2 - 25, 10);
        g.drawString("BEST:", (dim + 90)/2 - 25, 10);
        
        g.setFont(new Font("Avenir Next", Font.BOLD, 30));
        String score = "" + game.getScore();
        int dScore = score.length() * 10 + 5;
        g.drawString( score, (dim - 90)/2 - dScore, 60);
        String highscore = "" + game.getScore();
        int dHighscore = highscore.length() * 10 + 17;
        g.drawString(game.getHighscore() + "", (dim + 90)/2 - dHighscore, 60);
        
        // Background tiles
        g.setColor(BACKGROUND);
        for ( int r = 0; r < game.BOARD_DIM; r++ )
            for ( int c = 0; c < game.BOARD_DIM; c++ )
                drawBlock(g, r, c);
        
        // Snake and Pellet
        g.setColor(BLOCK_COLOR);
        Location loc = game.pellet;
        int r = loc.getRow(), c = loc.getCol();
        if ( ! game.hasEnded() )
            drawBlock(g, r, c);
        
        LinkedList<Location> body = game.snake.body;
        for ( Location l: body )
        {
            r = l.getRow();
            c = l.getCol();
            drawBlock(g, r, c);
        }
        
        // Game Over
        if ( game.hasEnded() )
        {
            g.setFont(new Font("Avenir Next", Font.BOLD, 75));
            g.setColor(Color.WHITE);
            g.drawString("Game Over!", 60, 375);
        }
    }
    
    private void drawBlock(Graphics g, int r, int c)
    {
        int x = (MARGIN + BLOCK_SIZE) * c + MARGIN;
        int y =  (MARGIN + BLOCK_SIZE) * r + MARGIN + SCOREBOARD;
        g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
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
        if (game == null)
            return;
        
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_SPACE && game.hasEnded())
        {
            new SnakeGame();
        }
        
        int direction = -1;
        if (code == KeyEvent.VK_UP)
            direction = Location.NORTH;
        else if (code == KeyEvent.VK_DOWN)
            direction = Location.SOUTH;
        else if (code == KeyEvent.VK_LEFT)
            direction = Location.WEST;
        else if (code == KeyEvent.VK_RIGHT)
            direction = Location.EAST;
        
        if ( direction != -1 && (direction + game.dir) % 180 != 0)
            game.dir = direction;
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
