package pacman;

/**
 * Shadow is the Red ghost
 * Shadow just follows the player
 */
public class Shadow extends Ghost {
    private int attackCounter = -1;

    public Shadow(PacmanGame game) {
        super(game);
        loadSprites("red");
        defaultSpeeds = new double[]{0.075, 0.045, 0.3};
        updateState(State.ALIVE);
    }

    @Override
    protected void attack() {
        attackCounter++;
        attackCounter %= 40;
        if (attackCounter < 5)
            flee();
        else
            chase();
    }
}
