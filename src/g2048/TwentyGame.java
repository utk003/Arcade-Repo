package g2048;

import netscape.security.UserTarget;

import java.io.*;
import java.util.ArrayList;

/**
 * Write a description of class RealGame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TwentyGame extends Game {
    private Board board;
    private TwentyDisplay display;

    private static int highscore;

    private static final String highscores = "assets/2048/highscore.txt";

    public TwentyGame() {
        board = new Board( 4);
        display = new TwentyDisplay(this);
        try {
            BufferedReader in = new BufferedReader(new FileReader(highscores));
            String line = in.readLine();
            highscore = Integer.parseInt(line);
            in.close();
        } catch (IOException ex) {
            System.err.println("Unable to load high score.");
            highscore = -1;
        }
        addTile();
        addTile();
    }

    public void move(int direction) {
        int add = 0;
        try {
            add = board.shiftBoard(direction);
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Bogus Move"))
                return;
            e.printStackTrace();
        }
        display.update();
        score += add;
        if (score > highscore)
            highscore = score;
        addTile();
    }

    public int getHighscore() {
        return highscore;
    }

    public void play() {
    }

    private void addTile() {
        ArrayList<Location> empty = board.getEmptySpaces();
        int rand = (int) (empty.size() * Math.random());
        new Tile(empty.get(rand), board);
        if (board.isDead()) {
            hasEnded = true;
            try {
                BufferedWriter writer =
                        new BufferedWriter(new FileWriter(highscores));
                writer.write("" + highscore);
                writer.close();
            } catch (IOException ex) {
            }
        }
        display.update();
    }

    public Board getBoard() {
        return board;
    }
}
