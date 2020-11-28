package g2048;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 * Write a description of class GameDisplay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TwentyDisplay extends JComponent implements KeyListener, MouseListener {
    private static final Color BACKGROUND = new Color(163, 157, 153);

    private static final int TILE_SIZE = 80;
    private static final int MARGIN = 10;

    private TwentyGame game;
    private Board board;

    private JFrame frame;

    // Constructs a new display for displaying the given board
    public TwentyDisplay(TwentyGame myGame) {
        game = myGame;
        board = game.getBoard();

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(this::createAndShowGUI);

        //Wait until display has been drawn
        try {
            while (frame == null || !frame.isVisible())
                Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
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
        frame.addMouseListener(this);

        //Display the window.
        this.setPreferredSize(new Dimension(4 * TILE_SIZE + 5 * MARGIN,
                5 * TILE_SIZE + 6 * MARGIN));
        this.addMouseListener(this);

        frame.pack();
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        g.setColor(BACKGROUND);
        g.fillRect(0, TILE_SIZE + MARGIN, frame.getWidth(), 4 * TILE_SIZE + 5 * MARGIN);

        g.setFont(new Font("Avenir Next", Font.BOLD, MARGIN));
        g.drawString("SCORE:", 2 * TILE_SIZE + 2 * MARGIN, MARGIN);
        g.drawString("BEST:", 3 * TILE_SIZE + 3 * MARGIN, MARGIN);

        g.setFont(new Font("Avenir Next", Font.BOLD, 3 * MARGIN));
        g.drawString(game.getScore() + "", 2 * TILE_SIZE + 2 * MARGIN, 6 * MARGIN);
        g.drawString(game.getHighscore() + "", 3 * TILE_SIZE + 3 * MARGIN, 6 * MARGIN);


        for (int r = 0; r < board.getSize(); r++) {
            for (int c = 0; c < board.getSize(); c++) {
                Location loc = new Location(r, c);
                Tile t = board.getTile(loc);
                int x = MARGIN + c * (TILE_SIZE + MARGIN);
                int y = MARGIN + (r + 1) * (TILE_SIZE + MARGIN);
                Image im;
                if (t == null) {
                    im = new ImageIcon("assets/2048/tile/blank.png").getImage();
                } else {
                    im = t.getImage();
                }
                drawTile(g, im, x, y);
            }
        }

        if (game.hasEnded()) {
            g.setFont(new Font("Avenir Next", Font.BOLD, 4 * MARGIN));
            g.setColor(Color.BLACK);
            g.drawString("Game Over!", TILE_SIZE + MARGIN, 3 * MARGIN + 3 * TILE_SIZE);
        }
    }

    private void drawTile(Graphics g, Image im, int x, int y) {
        g.drawImage(im, x, y, TILE_SIZE, TILE_SIZE, null);
    }

    public void setTitle(String title) {
        frame.setTitle(title);
    }

    public void update() {
        repaint();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void close() {
        frame.setVisible(false);
    }

    public void keyPressed(KeyEvent e) {
        if (game == null)
            return;

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_SPACE && game.hasEnded()) {
            new TwentyGame();
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

        if (direction != -1)
            game.move(direction);
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
    }
}
