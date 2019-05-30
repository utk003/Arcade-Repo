import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;
/**
 * Write a description of class FloppyGame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FloppyGame extends Game
{
    private Bird bird;
    private ArrayList<PipePair> pipes;

    private int speed;
    private int pipeDist;

    public static final double TIME_INTERVAL = 0.05;

    private static int highScore;

    private FloppyDisplay display;

    private static final String permHighScore = new String("flappyhighscore.txt");

    public FloppyGame()
    {
        super();

        bird = new Bird(this);
        pipes = new ArrayList<PipePair>();

        speed = 7;
        pipeDist = 200;

        try
        {
            BufferedReader in = new BufferedReader(new FileReader(permHighScore));
            String line = in.readLine();
            if (line != null)
            {
                highScore = Integer.parseInt(line);
            }
            in.close();
        }
        catch (IOException ex) {}

        display = new FloppyDisplay(this);
        display.setTitle("FloppyBird " + score);

        pipes.add(new PipePair(this, 250));

        while (!display.hasRendered())
        {
            try 
            {
                Thread.sleep(5);
            }
            catch (InterruptedException ex) {}
        }

        play();
    }

    //makes the pipes move, bird fall
    public void play()
    {
        while(!hasEnded)
        {
            // display.setRendered(false);
            // display.updateDisplay();
            // while (!display.hasRendered())
            // {
                // try 
                // {
                    // Thread.sleep(5);
                // }
                // catch (InterruptedException ex) {}
            // }
            // && display.hasRendered()
            if (!isPaused)
            {
                bird.fall();
                for (int i = 0; i < pipes.size(); i++)
                {
                    PipePair pipe = pipes.get(i);
                    int x = pipe.getXLoc();

                    if (x <= 45 && x >= 5)
                    {
                        int endTop = pipe.getTopY();
                        int beginBottom = pipe.getBottomY();
                        int birdY = bird.getYLoc();
                        if (birdY <= endTop || (birdY + 15) >= beginBottom)
                        {
                            try
                            {
                                BufferedWriter writer = 
                                    new BufferedWriter(new FileWriter(permHighScore));
                                writer.write("" + highScore);
                                writer.close();
                            }
                            catch (IOException ex) {}

                            hasEnded = true;
                        }
                    }
                    pipe.setXLoc(x - speed);
                    if (x < 0)
                    {
                        pipes.remove(i);
                        score++;
                        if (score % 5 == 0)
                            level++;
                        if (score > highScore)
                            highScore = score;
                        i--;
                    }
                    speed = 7 + level;
                    if  (speed > 12)
                        speed = 12;
                }

                if (pipes.size() <= 2)
                {
                    pipes.add(new PipePair(this, 
                            (pipes.get(pipes.size() - 1).getXLoc() + pipeDist)));
                }

                display.setTitle("Score: " + score);
                display.updateDisplay();
            }
            try 
            {
                Thread.sleep((int) (1000 * TIME_INTERVAL));
            }
            catch (InterruptedException ex) {}
        }
    }

    @Override
    public void pause()
    {
        if (isPaused && !hasEnded)
        {
            for (int i = 3; i >= 1 && isPaused; i--)
            {
                display.print(i + "");
                try 
                {
                    Thread.sleep((int) (500));
                }
                catch (InterruptedException ex) {}  
                display.clearText();
            }
        }
        super.pause();
    }

    public void spacePressed()
    {
        if (!isPaused)
            bird.jump();
    }

    public double getTimeInterval()
    {
        return TIME_INTERVAL;
    }

    public Bird getBird()
    {
        return bird;
    }

    public FloppyDisplay getDisplay()
    {
        return display;
    }

    public ArrayList<PipePair> getPipes()
    {
        return pipes;
    }

    public int getHighScore()
    {
        return highScore;
    }

    public void reset()
    {
        isPaused = false;
        hasEnded = false;
        level = 0;
        score = 0;
        startTime = new Date();

        bird = new Bird(this);
        pipes = new ArrayList<PipePair>();

        pipes.add(new PipePair(this, 250));
        speed = 5;
        pipeDist = 200;

        display.setGame(this);

        while (!display.hasRendered())
        {
            try 
            {
                Thread.sleep(5);
            }
            catch (InterruptedException ex) {}
        }

        play();
    }
}
