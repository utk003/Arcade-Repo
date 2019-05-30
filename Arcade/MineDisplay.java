import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;
/**
 * Write a description of class MSDisplay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MineDisplay extends JComponent implements KeyListener, MouseListener
{
    private MineGame game;
    private UserBoard userBoard;
    private MineBoard mineBoard;

    private static final Color LIGHT_BACK = new Color(195, 217, 247);
    private static final Color DARK_BACK = new Color(176, 201, 242);
    private static final Color LIGHT_CLEARED = new Color(242, 232, 222);
    private static final Color DARK_CLEARED = new Color(223, 216, 217);

    private static final Image redFlag = new ImageIcon("Assets/MineSweeper/flag-red.png").getImage();
    private static final Image grayFlag = new ImageIcon("Assets/MineSweeper/flag-gray.png").getImage();
    private static final Image mine = new ImageIcon("Assets/MineSweeper/exploded.png").getImage();
    private static final Image redX = new ImageIcon("Assets/MineSweeper/x-red.png").getImage();
    private static final Image flower = new ImageIcon("Assets/MineSweeper/flower.png").getImage();
    private static final Image stopwatch = new ImageIcon("Assets/MineSweeper/stopwatch.png").getImage();
    private static final Image highscore = new ImageIcon("Assets/MineSweeper/highscore.png").getImage();

    private static final int TILE_SIZE = 40;

    private JFrame frame;

    /**
     * Constructor for objects of class MSDisplay
     */
    public MineDisplay(MineGame myGame)
    {
        game = myGame;
        userBoard = game.getUserBoard();
        mineBoard = game.getMineBoard();

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
        this.setPreferredSize(new Dimension(userBoard.getNumCols() * TILE_SIZE, 
                (userBoard.getNumRows() + 2)* TILE_SIZE));
        this.addMouseListener(this);
        frame.pack();
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g)
    {
        g.setColor(DARK_BACK.darker());
        g.fillRect(0, 0,  frame.getWidth(), 2 * TILE_SIZE);

        drawRedFlag(g, 0, TILE_SIZE/2);
        drawStopwatch(g, frame.getWidth() - 7 * TILE_SIZE, TILE_SIZE/2);
        drawHighscore(g, frame.getWidth() - 4 * TILE_SIZE, 2*TILE_SIZE/3);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Avenir Next", Font.BOLD, 30));
        g.drawString(game.getFlagCount() + "", TILE_SIZE, 5*TILE_SIZE/4);
        g.drawString(game.getTimeElapsed()/1000 + "", frame.getWidth() - 6*TILE_SIZE, 5 * TILE_SIZE/4);
        g.drawString(game.getHighscore() + "", frame.getWidth() - 3*TILE_SIZE, 5*TILE_SIZE/4);

        for(int r = 0; r < userBoard.getNumRows(); r++)
        {
            for(int c = 0; c < userBoard.getNumCols(); c++)
            {
                int state = userBoard.get(new Location(r, c));
                int val = mineBoard.get(new Location(r, c));
                int x = c * TILE_SIZE;
                int y = r * TILE_SIZE + 2*TILE_SIZE;
                if (state == 0 || state == -1)
                {
                    if ((r + c) % 2 == 0)
                    {
                        g.setColor(LIGHT_BACK);
                        g.fillRect(x, y,  TILE_SIZE, TILE_SIZE);
                    }
                    else
                    {
                        g.setColor(DARK_BACK);
                        g.fillRect(x, y,  TILE_SIZE, TILE_SIZE);
                    }
                }
                else //state == 1
                {
                    if ((r + c) % 2 == 0)
                    {
                        g.setColor(LIGHT_CLEARED);
                        g.fillRect(x, y,  TILE_SIZE, TILE_SIZE);
                    }

                    else
                    {
                        g.setColor(DARK_CLEARED);
                        g.fillRect(x, y,  TILE_SIZE, TILE_SIZE);
                    }
                    g.setFont(new Font("Avenir Next", Font.BOLD, 30));
                    if (val != 0)
                    {
                        if (val == 1)
                            g.setColor(Color.BLUE);
                        else if (val == 2)
                            g.setColor(Color.GREEN.darker());
                        else if (val == 3)
                            g.setColor(Color.ORANGE);
                        else if (val == 4)
                            g.setColor(Color.RED.darker());
                        else if (val == 5)
                            g.setColor(Color.RED);
                        else if (val == 6)
                            g.setColor(Color.MAGENTA);
                        else if (val == 7)
                            g.setColor(Color.BLUE.brighter());
                        else if (val == 8)
                            g.setColor(Color.BLACK);
                        g.drawString(val + "", x + TILE_SIZE/4, y + 3 * TILE_SIZE/4);
                    }
                }

                if (game.hasWon())
                {
                    if (state == -1)
                    {
                        drawFlower(g, x, y);
                    }
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Avenir Next", Font.BOLD, 30));
                    g.drawString("You Win!", 7*TILE_SIZE, 5*TILE_SIZE/4);
                }
                else if (game.hasLost())
                {
                    if (val == -1)
                        drawMine(g, x, y);
                    if (val != -1 && state == -1)
                        drawX(g, x, y);
                    else if (state == -1)
                        drawGrayFlag(g, x, y);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Avenir Next", Font.BOLD, 30));
                    g.drawString("Whoops!", 7*TILE_SIZE, 5*TILE_SIZE/4);
                }
                else if (state == -1)
                    drawRedFlag(g, x, y);
            }
        }
    }

    private void drawRedFlag(Graphics g, int x, int y)
    {
        g.drawImage(redFlag, x, y, TILE_SIZE, TILE_SIZE, null);
    }

    private void drawGrayFlag(Graphics g, int x, int y)
    {
        g.drawImage(grayFlag, x, y, TILE_SIZE, TILE_SIZE, null);
    }

    private void drawX(Graphics g, int x, int y)
    {
        g.drawImage(redX, x, y, TILE_SIZE, TILE_SIZE, null);
    }

    private void drawMine(Graphics g, int x, int y)
    {
        g.drawImage(mine, x, y, TILE_SIZE, TILE_SIZE, null);
    }

    private void drawFlower(Graphics g, int x, int y)
    {
        g.drawImage(flower, x, y, TILE_SIZE, TILE_SIZE, null);
    }

    private void drawStopwatch(Graphics g, int x, int y)
    {
        g.drawImage(stopwatch, x, y, TILE_SIZE, TILE_SIZE, null);
    }

    private void drawHighscore(Graphics g, int x, int y)
    {
        g.drawImage(highscore, x, y, TILE_SIZE, TILE_SIZE, null);
    }

    public void setTitle(String title)
    {
        frame.setTitle(title);
    }

    public void update()
    {
        repaint();
    }

    public void updateTime()
    {
        Graphics g = getGraphics();
        g.setColor(DARK_BACK.darker());
        g.fillRect(0, 0,  frame.getWidth(), 2 * TILE_SIZE);

        drawRedFlag(g, 0, TILE_SIZE/2);
        drawStopwatch(g, frame.getWidth() - 7 * TILE_SIZE, TILE_SIZE/2);
        drawHighscore(g, frame.getWidth() - 4 * TILE_SIZE, 2*TILE_SIZE/3);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Avenir Next", Font.BOLD, 30));
        g.drawString(game.getFlagCount() + "", TILE_SIZE, 5*TILE_SIZE/4);
        g.drawString(game.getTimeElapsed()/1000 + "", frame.getWidth() - 6*TILE_SIZE, 5 * TILE_SIZE/4);
        g.drawString(game.getHighscore() + "", frame.getWidth() - 3*TILE_SIZE, 5*TILE_SIZE/4);
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
        if (game == null)
            return;
        int code = e.getButton();

        int row = e.getY() / TILE_SIZE - 2;
        int col = e.getX() / TILE_SIZE;
        Location loc = new Location(row, col);

        if (code == MouseEvent.BUTTON1) //left-click
        {
            game.leftClick(loc);
        }   
        else if (code == MouseEvent.BUTTON3 || code == MouseEvent.BUTTON2) //right-click
        {
            game.rightClick(loc);
        }
        repaint();
    }

}