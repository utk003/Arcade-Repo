package minesweeper;

import java.util.*;

/**
 * Abstract class Board - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class IntBoard {
    protected int[][] board;

    protected void set(Location loc, int n) {
        int r = loc.getRow();
        int c = loc.getCol();
        if (isValid(loc))
            board[r][c] = n;
    }

    public int get(Location loc) {
        int r = loc.getRow();
        int c = loc.getCol();
        if (isValid(loc))
            return board[r][c];
        return Integer.MIN_VALUE;
    }

    public boolean isValid(Location loc) {
        int r = loc.getRow();
        int c = loc.getCol();
        if (r >= 0 && r < board.length && c >= 0 && c < board[0].length)
            return true;
        return false;
    }

    public ArrayList<Location> getAdjacentLocs(Location loc) {
        ArrayList<Location> locs = new ArrayList<Location>();
        int r = loc.getRow();
        int c = loc.getCol();
        locs.add(new Location(r - 1, c));
        locs.add(new Location(r - 1, c - 1));
        locs.add(new Location(r - 1, c + 1));
        locs.add(new Location(r, c - 1));
        locs.add(new Location(r, c + 1));
        locs.add(new Location(r + 1, c - 1));
        locs.add(new Location(r + 1, c));
        locs.add(new Location(r + 1, c + 1));
        for (int i = locs.size() - 1; i >= 0; i--) {
            if (!isValid(locs.get(i)))
                locs.remove(i);
        }
        return locs;
    }

    public int getNumRows() {
        return board.length;
    }

    public int getNumCols() {
        return board[0].length;
    }
}
