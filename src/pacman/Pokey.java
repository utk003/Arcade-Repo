package pacman;

/**
 * Pokey is the orange ghost
 * Pokey moves randomly and has the slowest move speed
 */
public class Pokey extends Ghost {
    public Pokey(PacmanGame game) {
        super(game);
        loadSprites("orange");
        defaultSpeeds = new double[]{0.05, 0.045, 0.3};
        updateState(State.ALIVE);
    }

    protected void attack() {
        random();
    }
}
