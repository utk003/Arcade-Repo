import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;
/**
 * Write a description of class MSGame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MineGame extends Game
{
    private UserBoard userBoard;
    private MineBoard mineBoard;
    private MineDisplay display;

    private int flagCount;

    private boolean hasWon;
    private boolean hasLost;

    private boolean firstClick;

    private static final String highscores = "minesweeperHighscore.txt";
    private static int highscore;

    public MineGame()
    {
        super();
        mineBoard = new MineBoard(14, 18);
        userBoard = new UserBoard(this, 14, 18);
        firstClick = true;
        flagCount = 40;
        display = new MineDisplay(this);
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(highscores));
            String line = in.readLine();
            highscore = Integer.parseInt(line);
            in.close();
        }
        catch (IOException ex) {}
        new TimeUpdater(this, display);
    }

    public void play()
    {

    }

    public UserBoard getUserBoard()
    {
        return userBoard;
    }

    public MineBoard getMineBoard()
    {
        return mineBoard;
    }

    public void rightClick(Location loc)
    {
        if (!hasLost && !hasWon)
        {
            userBoard.flag(loc);
            if (flagCount == 0 && userBoard.hasUncovered())
                win();
        }
    }

    private void win()
    {
        hasWon = true;
        endTime = new Date();
        if (getTimeElapsed()/1000 < highscore)
        {
            highscore = getTimeElapsed()/1000;
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

    public void leftClick(Location loc)
    {
        if (firstClick)
        {
            mineBoard.firstClick(loc);
            startTime = new Date();
            firstClick = false;
            if (!userBoard.dig(loc))
                firstClick = true;
            expandShown(loc);    
        }
        else if (!hasLost && !hasWon)
        {
            if (userBoard.dig(loc) && !mineBoard.isSafe(loc))
            {
                hasLost = true;
                endTime = new Date();
            }
            if (flagCount == 0 && userBoard.hasUncovered())
            {
                win();
            }
            expandShown(loc);
        }
        display.update();
    }

    //Loc must have val = 0 on MineBoard
    private void expandShown(Location loc)
    {
        ArrayList<Location> adjLocs = mineBoard.getAdjacentLocs(loc);
        if (mineBoard.get(loc) == 0)
        {
            for (Location adjLoc : adjLocs)
            {
                if (mineBoard.get(adjLoc) == 0 && userBoard.get(adjLoc) == 0)
                {
                    userBoard.set(adjLoc, 1);
                    expandShown(adjLoc);
                }
                userBoard.set(adjLoc, 1);
            }
        }
    }

    public int getFlagCount()
    {
        return flagCount;
    }

    public void incrementFlag()
    {
        flagCount++;
    }

    public void decrementFlag()
    {
        flagCount--;
    }

    public boolean hasLost()
    {
        return hasLost;
    }

    public boolean hasWon()
    {
        return hasWon;
    }

    public int getHighscore()
    {
        return highscore;
    }
}
