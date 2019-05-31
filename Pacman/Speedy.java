 

import java.util.ArrayList;

/**
 * Speedy is the pink ghost
 * Speedy moves randomly and has the fastest move speed
 */
public class Speedy extends Ghost
{
    public Speedy(PacmanGame game)
    {
        super(game);
        loadSprites("pink");
        defaultSpeeds = new double[]{0.1, 0.045, 0.3};
        updateState(State.ALIVE);
    }

    protected void attack()
    {
        random();
    }
}
