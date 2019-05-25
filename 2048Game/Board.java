import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;
/**
 * Write a description of class Board here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Board
{
    private Tile[][] board;
    private TwentyGame game;

    private int size;

    public Board(TwentyGame myGame, int mySize)
    {
        game = myGame;
        size = mySize;
        board = new Tile[size][size];
    }

    public Tile getTile(Location loc)
    {
        int row = loc.getRow();
        int col = loc.getCol();
        if (isValid(loc))
            return board[row][col];
        return null;
    }

    public boolean isEmpty(Location loc)
    {
        return isValid(loc) && getTile(loc) == null;
    }

    public Tile setTile(Location loc, Tile t)
    {
        if (isValid(loc))
        {
            int row = loc.getRow();
            int col = loc.getCol();
            Tile prev = board[row][col];
            board[row][col] = t;
            return prev;
        }
        return null;
    }

    public boolean isValid(Location loc)
    {
        int row = loc.getRow();
        int col = loc.getCol();
        if (row >= board.length || col >= board[0].length || row < 0 || col < 0)
        {
            return false;
        }
        return true;
    }

    public int shiftBoard(int dir)
    {
        int total = 0;
        if (dir == Location.NORTH)
        {
            for (int r = 0; r < board.length; r++)
            {
                for (int c = 0; c < board[0].length; c++)
                {
                    Tile t = board[r][c];
                    if (t != null)
                        total += t.shift(dir);
                }
            }
        }
        else if (dir == Location.SOUTH)
        {
            for (int r = board.length - 1; r >= 0; r--)
            {
                for (int c = 0; c < board.length; c++)
                {
                    Tile t = board[r][c];
                    if (t != null)
                        total += t.shift(dir);
                }
            }
        }
        else if (dir == Location.EAST)
        {
            for (int c = board[0].length - 1; c >= 0; c--)
            {
                for (int r = 0; r < board.length; r++)
                {
                    Tile t = board[r][c];
                    if (t != null)
                        total += t.shift(dir);
                }
            }
        }
        else if (dir == Location.WEST)
        {
            for (int c = 0; c < board[0].length; c++)
            {
                for (int r = 0; r < board.length; r++)
                {
                    Tile t = board[r][c];
                    if (t != null)
                        total += t.shift(dir);
                }
            }
        }
        return total;
    }

    public int getSize()
    {
        return size;
    }

    public ArrayList<Location> getEmptySpaces()
    {
        ArrayList<Location> empty = new ArrayList<Location>();
        for (int r = 0; r < board.length; r++)
        {
            for (int c = 0; c < board[0].length; c++)
            {
                Location loc = new Location(r, c);
                if (isEmpty(loc))
                    empty.add(loc);
            }
        }
        return empty;
    }

    public boolean isDead()
    {
        for (int r = 0; r < board.length; r++)
        {
            for (int c = 0; c < board[0].length; c++)
            {
                if (board[r][c] == null)
                    return false;
                if (checkAdjacentTiles(new Location(r, c)))
                    return false;
            }
        }
        return true;
    }

    private boolean checkAdjacentTiles(Location loc)
    {
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        int r = loc.getRow();
        int c = loc.getCol();
        int val = board[r][c].getValue();
        Location up = new Location(r - 1, c);
        Location down = new Location(r + 1, c);
        Location left = new Location(r, c - 1);
        Location right = new Location(r, c + 1);
        Tile t = getTile(up);
        if (isValid(up) && (t == null || t.getValue() == val))
            return true;
        t = getTile(down);
        if (isValid(down) && (t == null || t.getValue() == val))
            return true;  
        t = getTile(left);
        if (isValid(left) && (t == null || t.getValue() == val))
            return true;
        t = getTile(right);
        if (isValid(right) && (t == null || t.getValue() == val))
            return true;
        return false;
    }
}
