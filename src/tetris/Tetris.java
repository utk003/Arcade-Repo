package tetris;

import java.util.*;

/**
 * The Tetris class implements a tetris game using a bounded grid as a board,
 * a display, and Tetrads. It allows users to control falling tetrads to try
 * and complete rows, clearing them as they are completed. The game keeps
 * track of and displays the users score and level, and allows them to
 * restart the game.
 *
 * @author Aditya Singhvi
 * @version March 9 2019
 */
public class Tetris implements ArrowListener {
    private MyBoundedGrid board;
    private MyBoundedGrid nextBoard;
    private BlockDisplay display;

    private Tetrad nextTetrad;
    private Tetrad activeTetrad;

    private int score;
    private int numCleared;

    private boolean hasLost;
    private boolean isPaused;

    /**
     * Constructor for objects of class Tetris. Creates a new Tetris game,
     * display, and automatically begins with a falling Tetrad.
     */
    public Tetris() {
        board = new MyBoundedGrid<Block>(20, 10);
        nextBoard = new MyBoundedGrid<Block>(4, 7);

        display = new BlockDisplay(board);
        score = 0;
        numCleared = 0;
        display.setArrowListener(this);

        nextTetrad = new Tetrad(nextBoard);
        activeTetrad = new Tetrad(board);

        display.setTitle("tetris");
        display.showBlocks();

        hasLost = false;
        isPaused = false;

        play();
    }

    /**
     * Returns the board containing the next tetrad.
     *
     * @return The nextBoard, which contains the next tetrad to be dropped.
     */
    public MyBoundedGrid<Block> getNextBoard() {
        return nextBoard;
    }

    /**
     * Switches the next tetrad to be dropped and the active tetrad, decreasing
     * the user's score in the process.
     *
     * @postcondition
     */
    public void interchange() {
        int type = activeTetrad.getType();
        activeTetrad.remove();
        activeTetrad = new Tetrad(board, nextTetrad.getType());
        nextTetrad.remove();
        nextTetrad = new Tetrad(nextBoard, type);
        if (score >= 50)
            score -= 50;
    }

    /**
     * Checks whether the game of Tetris has been lost yet and returns true if
     * it has been lost, false otherwise.
     *
     * @return true if the game has been lost
     * false otherwise.
     */
    public boolean hasLost() {
        return hasLost;
    }

    /**
     * Returns the level of the user. Found by dividing the total number of rows
     * cleared so far by 10 and rounding down.
     *
     * @return The level of the user for the game.
     */
    public int getLevel() {
        return (int) (numCleared / 10);
    }

    /**
     * Returns the score of the user. The score increases every time a row
     * is cleared.
     *
     * @return The score of the user for the game.
     */
    public int getScore() {
        return score;
    }

    /**
     * Translates Tetrad tet as far down as possible on its own board.
     */
    private void hardDrop(Tetrad tet) {
        while (tet.translate(1, 0)) {
            //doNothing
        }
    }

    /**
     * Called whenever the space bar is pressed. Engages a "hard drop" for the
     * current tetrad, meaning that the tetrad is immediately moved as far down
     * as possible.
     *
     * @postcondition
     */
    public void spacePressed() {
        if (!hasLost && !isPaused && activeTetrad != null) {
            hardDrop(activeTetrad);
            display.showBlocks();
        }
    }

    /**
     * Called whenever the up arrow key is pressed. Rotates the current active
     * tetrad by 90˚ clockwise.
     *
     * @postcondition The active tetrad has been rotated by 90˚ clockwise.
     */
    public void upPressed() {
        if (!hasLost && !isPaused && activeTetrad != null) {
            activeTetrad.rotate();
            display.showBlocks();
        }
    }

    /**
     * Called whenever the down arrow key is pressed. Translates the current
     * active tetrad down by one block.
     *
     * @postcondition The active tetrad has been translated down by one block.
     */
    public void downPressed() {
        if (!hasLost && !isPaused && activeTetrad != null) {
            activeTetrad.translate(1, 0);
            display.showBlocks();
        }
    }

    /**
     * Called whenever the left arrow key is pressed. Translates the current
     * active tetrad left by one block, if possible.
     *
     * @postcondition The active tetrad has been translated left by one block.
     */
    public void leftPressed() {
        if (!hasLost && !isPaused && activeTetrad != null) {
            activeTetrad.translate(0, -1);
            display.showBlocks();
        }
    }

    /**
     * Called whenever the right arrow key is pressed. Translates the current
     * active tetrad right by one block, if possible.
     *
     * @postcondition The active tetrad has been translated right by one
     * block.
     */
    public void rightPressed() {
        if (!hasLost && !isPaused && activeTetrad != null) {
            activeTetrad.translate(0, 1);
            display.showBlocks();
        }
    }

    /**
     * Continues the game, moving the current active tetrad down by one block
     * every set number of milliseconds (that is dependent on the level).
     * If the tetrad has reached the bottom, clears all completed rows and
     * creates a new active tetrad.
     * If the game has been lost after the active tetrad has
     * been placed, ensures that calling hasLost() returns true.
     */
    public void play() {
        while (true) {
            try {
                //Pause for 1000 milliseconds.
                Thread.sleep(200 + 700 / ((int) (getLevel() * 0.40 + 1.2)));
            } catch (InterruptedException e) {
                //ignore
            }
            if (!isPaused) {
                try {
                    if (!hasLost && !activeTetrad.translate(1, 0)) {
                        clearCompletedRows();
                        activeTetrad = new Tetrad(board,
                                nextTetrad.getType());
                        nextBoard.clearAll();
                        nextTetrad = new Tetrad(nextBoard);
                    }
                } catch (NullPointerException e) {
                    hasLost = true;
                }
            }
            display.showBlocks();
        }
    }

    /**
     * Drops 4 new tetrads in random locations on the board, thus complicating
     * the game for the user. Called when the Drop4 button is pressed in the
     * menu.
     *
     * @postcondition
     */
    public void drop4() {
        hardDrop(activeTetrad);
        for (int i = 0; i < 4; i++) {
            Tetrad t = new Tetrad(board);
            while (!hasLost && t.translate(0, -1)) {
                //do nothing
            }
            int rand = (int) (8 * Math.random());
            while (rand != 0) {
                t.translate(0, 1);
                t.rotate();
                rand--;
            }
            hardDrop(t);
        }
        display.showBlocks();
    }

    /**
     * Checks if a certain row is complete, meaning that every "cell" in the row
     * is occupies by a block.
     *
     * @param row The row in the Tetris game to be checked for completion.
     * @return true if the row is complete
     * false otherwise
     * @precondition 0 <= row < number of rows
     * @postcondition Returns true if every cell in the
     * given row is occupied;
     * returns false otherwise.
     */
    private boolean isCompletedRow(int row) {
        for (int i = 0; i < board.getNumCols(); i++) {
            if (board.isEmpty(new Location(row, i)))
                return false;
        }
        return true;
    }

    /**
     * Clears the row, removing all blocks in that row and moving all blocks in
     * above rows one row down.
     *
     * @param row The row to be cleared of blocks.
     * @precondition: 0 <= row < number of rows;
     * given row is full of blocks (isCompletedRow returns true)
     * @postcondition: Every block in the given row has been
     * removed, and blocks in above rows
     * have each been moved down one row.
     */
    private void clearRow(int row) {
        for (int col = 0; col < board.getNumCols(); col++) {
            Location loc = new Location(row, col);
            Block bl = (Block) board.get(loc);
            bl.removeSelfFromGrid();
        }
        List<Location> occLocs = board.getOccupiedLocations();
        for (Location loc : occLocs) {
            Block bl = (Block) board.get(loc);
            int origRow = loc.getRow();
            if (origRow < row && origRow < board.getNumRows() - 1)
                bl.moveTo(new Location(origRow + 1, loc.getCol()));
        }
    }

    /**
     * Clears all completed rows on the game board.
     *
     * @postcondition: All completed rows have been cleared.
     */
    private void clearCompletedRows() {
        int num = 0;
        for (int i = board.getNumRows() - 1; i >= 0; i--) {
            while (isCompletedRow(i)) {
                clearRow(i);
                numCleared++;
                num++;
            }
        }
        if (num != 0)
            score += 24 * num * num + 100 * num - 24;
    }

    /**
     * Flips the value of boolean isPaused, thereby pausing/resuming gameplay.
     *
     * @postcondition
     */
    public void pause() {
        isPaused = !isPaused;
    }

    /**
     * Checks and returns whether the game is paused.
     *
     * @return true if the game is paused
     * false otherwise
     */
    public boolean isPaused() {
        return isPaused;
    }

    /**
     * Resets the game to create a new game.
     */
    public void reset() {
        board = new MyBoundedGrid<Block>(20, 10);
        nextBoard = new MyBoundedGrid<Block>(4, 7);
        display.setBoard(board);
        score = 0;
        numCleared = 0;
        hasLost = false;
        isPaused = false;
        display.setArrowListener(this);
        nextTetrad = new Tetrad(nextBoard);
        activeTetrad = new Tetrad(board);
    }

}
