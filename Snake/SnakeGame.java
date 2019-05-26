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
public class SnakeGame extends Game
{
    public Display display;
    
    public Snake snake;
    public Location pellet;
    
    public static final int BOARD_DIM = 51;
    
    public int dir;
    
    public int mps = 5;

    private static int highscore;

    private static final String highscores = "SnakeHighscores.txt";

    public SnakeGame()
    {
        dir = 0;
        snake = new Snake(this);
        display = new Display(this);
        
        display.setTitle("Snake");
        
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(highscores));
            String line = in.readLine();
            highscore = Integer.parseInt(line);
            in.close();
        }
        catch (IOException ex) {}
        
        score = 1;
        
        pellet = genNewPellet();
        play();
    }
    
    private Location genNewPellet()
    {
        int r = (int) (Math.random() * BOARD_DIM), c = (int) (Math.random() * BOARD_DIM);
        return new Location(r,c);
    }

    public int getHighscore()
    {
        return highscore;
    }

    public void play()
    {
        MyThread newThread = new MyThread(this);
        newThread.start();
    }

    public void update()
    {
        snake.move(dir);
        if (snake.isDead())
        {
            try
            {
                BufferedWriter writer = 
                    new BufferedWriter(new FileWriter(highscores));
                writer.write("" + highscore);
                writer.close();
            }
            catch (IOException ex) {}
        }
        if ( ! snake.isDead() && snake.body.getFirst().equals(pellet) )
        {
            snake.grow();
            pellet = genNewPellet();
            score++;
            mps = 5 + (score - 1) / 5;
            if ( score > highscore )
                highscore = score;
        }
        display.update();
    }
    
    @Override
    public boolean hasEnded()
    {
        return snake.isDead();
    }
}
