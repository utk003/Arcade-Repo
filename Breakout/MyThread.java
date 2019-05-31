
/**
 * Write a description of class MyThread here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyThread extends Thread
{
    private Ball ball;
    private Breakout b;
    public MyThread(Ball ball, Breakout breakout)
    {
        this.ball = ball;
        b = breakout;
    }
    
    public void run()
    {
        while(b.isPlaying())
        {
            ball.move();
            b.checkForCollisions();
            try
            {
                sleep(20);
            }
            catch(InterruptedException e)
            {
                
            }
        }
    }
}
