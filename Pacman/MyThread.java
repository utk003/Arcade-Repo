
public class MyThread extends Thread
{
    public BoardPlayer player;
    public PacmanGame game;
    private static final double pacSpeed = 0.1;
    private int refreshRate;
    
    public MyThread( BoardPlayer p, PacmanGame g )
    {
        player = p;
        game = g;
        refreshRate = (int) (( 1000.0 / game.fps ) / ( player.speed / pacSpeed ));
        if ( pacSpeed != g.players[0].speed )
            throw new RuntimeException("Someone changed the code :(");
    }
    
    @Override
    public void run()
    {
        while ( ! game.gameOver() )
        {
            player.updateDirection();
            player.move();
            try
            {
                Thread.sleep( refreshRate );
            }
            catch ( InterruptedException e )
            {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
