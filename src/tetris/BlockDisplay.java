package tetris;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;

/**
 * @author Anu Datar
 * <p>
 * Changed block size and added a split panel display for next block and Score
 * @author Ryan Adolf
 * @version 1.0
 * <p>
 * Fixed the lag issue with block rendering
 * Removed the JPanel
 */
// Used to display the contents of a game board
public class BlockDisplay extends JComponent implements KeyListener, MouseListener {
    private static final Color BACKGROUND = Color.BLACK;
    private static final Color BORDER = new Color(50, 50, 50);
    private static final Color SIDEBAR = Color.GRAY;

    private static final int OUTLINE = 1;
    private static final int BLOCKSIZE = 30;

    private MyBoundedGrid<Block> board;

    private JFrame frame;
    private Tetris listener;
    private boolean isMusicOn;

    private AudioClip clip;

    // Constructs a new display for displaying the given board
    public BlockDisplay(MyBoundedGrid<Block> board) {
        this.board = board;
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

        //Wait until display has been drawn
        try {
            while (frame == null || !frame.isVisible())
                Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }

        isMusicOn = false;
        URL url = getClass().getClassLoader().getResource("tetris/kahoot.wav");
        clip = Applet.newAudioClip(url);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.addKeyListener(this);

        //Display the window.
        this.setPreferredSize(new Dimension(
                BLOCKSIZE * (board.getNumCols() + 8),
                BLOCKSIZE * board.getNumRows()
        ));
        this.addMouseListener(this);

        frame.pack();
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, BLOCKSIZE * board.getNumCols(), getHeight());
        g.setColor(BORDER);
        g.fillRect(0, 0, BLOCKSIZE * board.getNumCols() + OUTLINE, BLOCKSIZE * board.getNumRows());
        g.setColor(SIDEBAR);
        g.fillRect(BLOCKSIZE * board.getNumCols(), 0, getWidth(), getHeight());
        for (int row = 0; row < board.getNumRows(); row++) {
            for (int col = 0; col < board.getNumCols(); col++) {
                Location loc = new Location(row, col);

                Block square = board.get(loc);

                if (square == null)
                    g.setColor(BACKGROUND);
                else
                    g.setColor(square.getColor());

                g.fillRect(col * BLOCKSIZE + OUTLINE / 2, row * BLOCKSIZE + OUTLINE / 2,
                        BLOCKSIZE - OUTLINE, BLOCKSIZE - OUTLINE);
            }
        }

        g.setColor(BORDER);
        g.fillRect(BLOCKSIZE * (board.getNumCols() + 4),
                0,
                BLOCKSIZE * 4 + OUTLINE,
                BLOCKSIZE * board.getNumRows());
        try {
            MyBoundedGrid<Block> nextTetrads = listener.getNextBoard();

            for (int row = 0; row < nextTetrads.getNumRows(); row++) {
                for (int col = 3; col < nextTetrads.getNumCols(); col++) {
                    Location loc = new Location(row, col);

                    Block square = nextTetrads.get(loc);

                    if (square == null)
                        g.setColor(BACKGROUND);
                    else
                        g.setColor(square.getColor());

                    g.fillRect(col * BLOCKSIZE + BLOCKSIZE * (board.getNumCols() + 1)
                                    + OUTLINE / 2,
                            BLOCKSIZE * row + OUTLINE / 2,
                            BLOCKSIZE - OUTLINE, BLOCKSIZE - OUTLINE);
                }
            }
        } catch (Exception E) {
            //do nothing
        }
        drawScore(g, BLOCKSIZE * board.getNumCols(), BLOCKSIZE);

        drawLevel(g, BLOCKSIZE * board.getNumCols(), 4 * BLOCKSIZE);

        drawMusic(g, BLOCKSIZE * board.getNumCols() + BLOCKSIZE, 6 * BLOCKSIZE);

        if (listener.isPaused())
            drawPlay(g, BLOCKSIZE * board.getNumCols() + BLOCKSIZE, 9 * BLOCKSIZE);
        else
            drawPause(g, BLOCKSIZE * board.getNumCols() + BLOCKSIZE, 9 * BLOCKSIZE);

        drawDrop4(g, BLOCKSIZE * board.getNumCols() + BLOCKSIZE, 12 * BLOCKSIZE);

        drawNewGame(g, BLOCKSIZE * board.getNumCols() + BLOCKSIZE, 15 * BLOCKSIZE);


        if (listener.hasLost())
            drawLoss(g);
    }

    private void drawMusic(Graphics g, int x, int y) {
        Image image = new ImageIcon("assets/tetris/music.png").getImage();
        g.drawImage(image, x, y, BLOCKSIZE * 2, BLOCKSIZE * 2, null);
    }

    private void drawPause(Graphics g, int x, int y) {
        Image image = new ImageIcon("assets/tetris/pause.png").getImage();
        g.drawImage(image, x, y, BLOCKSIZE * 2, BLOCKSIZE * 2, null);
    }

    private void drawPlay(Graphics g, int x, int y) {
        Image image = new ImageIcon("assets/tetris/play.png").getImage();
        g.drawImage(image, x, y, BLOCKSIZE * 2, BLOCKSIZE * 2, null);
        if (!listener.hasLost()) {
            g.setFont(new Font("Avenir Next", Font.BOLD, 2 * BLOCKSIZE));
            g.setColor(Color.WHITE);
            g.drawString("Paused.", BLOCKSIZE * 2, BLOCKSIZE * 10);
        }
    }

    private void drawDrop4(Graphics g, int x, int y) {
        Image image = new ImageIcon("assets/tetris/drop4.png").getImage();
        g.drawImage(image, x, y, BLOCKSIZE * 2, BLOCKSIZE * 2, null);
    }

    private void drawNewGame(Graphics g, int x, int y) {
        Image image = new ImageIcon("assets/tetris/newGame.png").getImage();
        g.drawImage(image, x, y, BLOCKSIZE * 2, BLOCKSIZE * 2, null);
    }

    private void drawLoss(Graphics g) {
        g.setFont(new Font("Avenir Next", Font.BOLD, 2 * BLOCKSIZE));
        g.setColor(Color.WHITE);
        g.drawString("You Lose!", BLOCKSIZE * 2, BLOCKSIZE * 10);
    }

    private void drawScore(Graphics g, int x, int y) {
        g.setFont(new Font("Avenir Next", Font.PLAIN, BLOCKSIZE));
        g.setColor(Color.BLACK);
        g.drawString(" Score:", x, y);
        g.drawString(("" + listener.getScore()), x + BLOCKSIZE,
                y + 5 * BLOCKSIZE / 4);
    }

    private void drawLevel(Graphics g, int x, int y) {
        g.setFont(new Font("Avenir Next", Font.PLAIN, BLOCKSIZE));
        g.setColor(Color.BLACK);
        g.drawString(" Level:", x, y);
        g.drawString(("" + listener.getLevel()), x + BLOCKSIZE,
                y + 5 * BLOCKSIZE / 4);
    }
    //Redraws the board to include the pieces and border colors.
    public void showBlocks() {
        repaint();
    }

    // Sets the title of the window.
    public void setTitle(String title) {
        frame.setTitle(title);
    }

    public void setBoard(MyBoundedGrid gr) {
        board = gr;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (listener == null)
            return;
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT)
            listener.leftPressed();
        else if (code == KeyEvent.VK_RIGHT)
            listener.rightPressed();
        else if (code == KeyEvent.VK_DOWN)
            listener.downPressed();
        else if (code == KeyEvent.VK_UP)
            listener.upPressed();
        else if (code == KeyEvent.VK_SPACE)
            listener.spacePressed();
    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        //none selected previously
        int col = e.getX() / (BLOCKSIZE);
        int row = e.getY() / (BLOCKSIZE);

        if (col >= board.getNumCols() + 1 && col <= board.getNumCols() + 4) {
            if (row >= 6 && row <= 7) {
                if (isMusicOn) {
                    clip.stop();
                    isMusicOn = false;
                } else {
                    isMusicOn = true;
                    clip.loop();
                }
            } else if (row >= 9 && row <= 10) {
                listener.pause();
            } else if (row >= 12 && row <= 13) {
                listener.drop4();
            } else if (row >= 15 && row <= 16) {
                listener.reset();
            }
        } else if (col >= board.getNumCols() + 5) {
            listener.interchange();
        }
        repaint();
    }

    public void setArrowListener(ArrowListener listener) {
        this.listener = (Tetris) listener;
    }

}
