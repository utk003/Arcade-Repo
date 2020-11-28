package minesweeper;

import java.util.*;

/**
 * Write a description of class MineBoard here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MineBoard extends IntBoard {
    private ArrayList<Location> mines;

    public MineBoard(int row, int col) {
        board = new int[row][col];
    }

    private int countAdjacentMines(Location loc) {
        if (get(loc) == -1)
            return -1;
        else {
            int count = 0;
            ArrayList<Location> locs = getAdjacentLocs(loc);
            for (Location l : locs)
                if (get(l) == -1)
                    count++;
            return count;
        }
    }

    public void firstClick(Location clicked) {
        board = new int[board.length][board[0].length];
        int clickedRow = clicked.getRow();
        int clickedCol = clicked.getCol();
        ArrayList<Location> locs = new ArrayList<Location>();
        mines = new ArrayList<Location>();

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (Math.abs(r - clickedRow) > 1 ||
                        Math.abs(c - clickedCol) > 1) {
                    Location loc = new Location(r, c);
                    locs.add(loc);
                }
            }
        }

        for (int i = 0; i < 40; i++) {
            int rand = (int) (locs.size() * Math.random());
            Location loc = locs.remove(rand);
            mines.add(loc);
            set(loc, -1);
        }

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = countAdjacentMines(new Location(r, c));
            }
        }
    }

    public boolean isSafe(Location loc) {
        return get(loc) != -1;
    }

    public ArrayList<Location> getMines() {
        return mines;
    }
}
