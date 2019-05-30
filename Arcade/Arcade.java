import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;
/**
 * Write a description of class Arcade here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Arcade 
{
    ArrayList<Image> dispImages;
    ArcadeDisplay display;
    Game currentGame;

    public Arcade()
    {
        dispImages = new ArrayList<Image>();
        dispImages.add(new ImageIcon("Assets/Arcade/ArcadeDisplay-1.png").getImage());
        dispImages.add(new ImageIcon("Assets/Arcade/ArcadeDisplay-2.png").getImage());
        dispImages.add(new ImageIcon("Assets/Arcade/ArcadeDisplay-3.png").getImage());
        display = new ArcadeDisplay(this);
        Thread object = new Thread(new ArcadeThread(this)); 
        object.start(); 
    }

    // public Game makePacman()
    // {
        // return new Pacman();
    // }

    public Game makeMineSweeper()
    {
        return new MineGame();
    }

    // public Game makeBreakout()
    // {
        // return new Breakout();
    // }

    // public Game makePong()
    // {
        // return new Pong();
    // }

    // public void makeSnake()
    // {
        // System.out.println("Snake");
        // currentGame = new SnakeGame();
    // }

    public void make2048()
    {
        System.out.println("2048");
        currentGame = new TwentyGame();
    }

    public void makeFlappyBird()
    {
        System.out.println("FlappyBird");
        currentGame = new FloppyGame();
    }

    public void makeTetris()
    {
        System.out.println("Tetris");
        currentGame = new TetrisGame();
    }
    
    public void update()
    {
        display.update();
    }
    
    public Game getCurrentGame()
    {
        return currentGame;
    }
    public Image getImage()
    {
        Image temp = dispImages.remove(0);
        dispImages.add(temp);
        return temp;
    }
}
