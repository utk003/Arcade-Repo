package minesweeper;

/**
 * Write a description of class UserBoard here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UserBoard extends IntBoard {
    // 0 if covered, -1 if flag, 1 if uncovered
    MineGame game;

    public UserBoard(MineGame myGame, int row, int col) {
        game = myGame;
        board = new int[row][col];
    }

    public void flag(Location loc) {
        int val = get(loc);
        if (val == -1) {
            set(loc, 0);
            game.incrementFlag();
        } else if (val == 0) {
            set(loc, -1);
            game.decrementFlag();
        }
    }

    public boolean dig(Location loc) {
        int val = get(loc);
        if (val == -1) {
            set(loc, 0);
            game.incrementFlag();
            return false;
        } else if (val == 0) {
            set(loc, 1);
            return true;
        }
        return false;
    }

    public boolean hasUncovered() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == 0)
                    return false;
            }
        }
        return true;
    }

}
