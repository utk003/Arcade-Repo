import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;
/**
 * Write a description of class TimeUpdater here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TimeUpdater
{
    private MineDisplay display;
    private MineGame game;

    public TimeUpdater(MineGame myGame, MineDisplay myDisplay)
    {
        game = myGame;
        display = myDisplay;
        run();
    }

    public void run()
    {
        while (!game.hasLost() && !game.hasWon())
        {
            display.updateTime();
            try 
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {}
        }
    }
}
