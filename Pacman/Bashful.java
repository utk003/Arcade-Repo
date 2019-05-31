 

/**
 * Bashful is the cyan ghost
 * Bashful avoids Pacman at first but retaliates if attacked
 */
public class Bashful extends Ghost
{
    private int attackCounter = -1;

    public Bashful(PacmanGame game)
    {
        super(game);
        loadSprites("cyan");
        defaultSpeeds = new double[]{0.075, 0.045, 0.3};
        updateState(State.ALIVE);
    }

    protected void attack()
    {
        attackCounter++;
        attackCounter %= 80;
        if ( attackCounter < 40 )
            run();
        else
            chase();
    }
}