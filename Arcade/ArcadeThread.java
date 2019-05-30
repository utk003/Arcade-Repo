import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;
/**
 * Write a description of class TwentyThread here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ArcadeThread implements Runnable
{
    private Arcade arcade;
    public ArcadeThread(Arcade arc )
    {
        arcade = arc;
    }
    
    public void run()
    {
        while (arcade.getCurrentGame() == null)
        {
            arcade.update();
            try
            {
                Thread.sleep(100);
            }
            catch ( InterruptedException e )
            {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
