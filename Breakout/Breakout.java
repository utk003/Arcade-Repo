import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
/**
 * Write a description of class Breakout here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Breakout extends KeyAdapter
{
    // instance variables - replace the example below with your own
    private ShapeDisplay disp;
    private Ball ball;
    private Paddle paddle;
    private int score;
    /**
     * Constructor for objects of class Breakout
     */
    public Breakout()
    {
        // initialise instance variables
        disp=new ShapeDisplay();
        disp.setTitle("Breakout");
        disp.addKeyListener(this);
        ball=new Ball(50,80);
        paddle = new Paddle(40, 90);
        disp.add(new Obstacle(Color.BLUE, 0, 0, 4, 100));
        disp.add(new Obstacle(Color.BLUE, 0, 0, 100, 4));
        disp.add(new Obstacle(Color.BLUE, 96, 0, 4, 100));
        disp.add(ball);
        disp.add(paddle);
        for(int i = 5;i<89;i+=7)
        {
            for(int j = 15;j<50;j+=5)
            {
                disp.add(new Brick(Color.BLUE, i, j));
            }
        }
        score = 0;
        while(play())
        {
            reset();
        }
    }

    public boolean play()
    {
        MyThread t = new MyThread(ball,this);
        t.start();
        while(isPlaying())
        {
            disp.repaint();
        }
        return true;
    }
    public void reset()
    {
        disp.setShapes(new ArrayList<Shape>());
        ball= new Ball(50,80);
        paddle = new Paddle(40, 90);
        disp.add(new Obstacle(Color.BLUE, 0, 0, 4, 100));
        disp.add(new Obstacle(Color.BLUE, 0, 0, 100, 4));
        disp.add(new Obstacle(Color.BLUE, 96, 0, 4, 100));
        disp.add(ball);
        disp.add(paddle);
        for(int i = 5;i<89;i+=7)
        {
            for(int j = 15;j<50;j+=5)
            {
                disp.add(new Brick(Color.BLUE, i, j));
            }
        }
        score = 0;
        disp.repaint();
    }

    public boolean isPlaying()
    {
        return ball.getY() < 98;
    }

    public void checkForCollisions()
    {
        for(int i = disp.getShapes().size()-1;i>=0;i--)
        {
            if(disp.getShapes().get(i) instanceof Obstacle)
            {
                if(disp.getShapes().get(i).overlapsWith(ball))
                {
                    if(disp.getShapes().get(i).handleCollision(ball))
                    {
                        disp.getShapes().remove(i);
                    }
                }
            }
        }
    }

    public void keyPressed(KeyEvent e)
    {
        int num = e.getKeyCode();
        if(num == KeyEvent.VK_LEFT)
        {
            if(paddle.getX() > 4)
            {
                paddle.setX(paddle.getX()-3);
                if(paddle.overlapsWith(ball))
                {
                    ball.setY(ball.getY()-1);
                }
            }
        }
        else if(num == KeyEvent.VK_RIGHT)
        {
            if(paddle.getX() + 20 < 96)
            {
                paddle.setX(paddle.getX()+3);
                if(paddle.overlapsWith(ball))
                {
                    ball.setY(ball.getY()-1);
                }
            }
        }   
    }
}