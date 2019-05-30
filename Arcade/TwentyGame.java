import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;
/**
 * Write a description of class RealGame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TwentyGame extends Game
{
    private TwentyBoard board;
    private TwentyDisplay display;

    private static int highscore;

    private static final String highscores = "2048highscores.txt";

    public TwentyGame()
    {
        board = new TwentyBoard(this, 4);
        display = new TwentyDisplay(this);
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(highscores));
            String line = in.readLine();
            highscore = Integer.parseInt(line);
            in.close();
        }
        catch (IOException ex) {}
        addTile();
        addTile();
    }

    public void move(int direction)
    {
        int add = 0;
        try
        {
            add = board.shiftBoard(direction);
        }
        catch ( RuntimeException e )
        {
            if ( e.getMessage().equals("Bogus Move") )
                return;
            e.printStackTrace();
        }
        display.update();
        score += add;
        if (score > highscore)
            highscore = score;
        addTile();
    }

    public int getHighscore()
    {
        return highscore;
    }

    public void play()
    {
    }

    private void addTile()
    {
        ArrayList<Location> empty = board.getEmptySpaces();
        int rand = (int) (empty.size() * Math.random());
        new Tile(empty.get(rand), board);
        if (board.isDead())
        {
            hasEnded = true;
            try
            {
                BufferedWriter writer = 
                    new BufferedWriter(new FileWriter(highscores));
                writer.write("" + highscore);
                writer.close();
            }
            catch (IOException ex) {}
        }
        display.update();
    }

    public void update()
    {
        display.update();
    }
    
    public TwentyBoard getBoard()
    {
        return board;
    }
}
