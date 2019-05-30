import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;
/**
 * Write a description of class Tile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tile
{
    private int value;
    private Location loc;
    private TwentyBoard board;
    
    private Image image;

    public Tile(Location myLoc, TwentyBoard myBoard)
    {
        board = myBoard;
        loc = myLoc;
        board.setTile(loc, this);
        int rand = (int) (10 * Math.random());
        value = 2;
        if (rand == 0)
            value = 4;
        image = new ImageIcon("Assets/2048/Tile-" + value + ".png").getImage();
    }

    public void doubleValue()
    {
        value *= 2;
        image = new ImageIcon("Assets/2048/Tile-" + value + ".png").getImage();
    }

    public int getValue()
    {
        return value;
    }

    public Location getLoc()
    {
        return loc;
    }

    public int shift(int direction)
    {
        int result = 0;
        int deltaR = 0;
        int deltaC = 0;
        if (direction == Location.NORTH)
            deltaR = -1;
        else if (direction == Location.SOUTH)
            deltaR = 1;
        else if (direction == Location.EAST)
            deltaC = 1;
        else if (direction == Location.WEST)
            deltaC = -1;
            
        int row = loc.getRow() + deltaR;
        int col = loc.getCol() + deltaC;
        while (board.isEmpty(new Location(row, col)))
        {
            board.setTile(loc, null);
            loc = new Location(row, col);
            board.setTile(loc, this);
            row += deltaR;
            col += deltaC;
        }
        Location nextLoc = new Location(row, col);
        if (board.isValid(nextLoc))
        {
            Tile t = board.getTile(nextLoc);
            if (t != null && t.getValue() == value)
            {
                board.setTile(loc, null);
                board.getTile(nextLoc).doubleValue();
                result += 2*value;
            }
        }
        return result;
    }
    
    public Image getImage()
    {
        return image;
    }
}
