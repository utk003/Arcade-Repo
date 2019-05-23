public abstract class Ghost extends BoardPlayer {
    protected double speed;
    protected State state;

    // Fill in later
    protected double[] defaultSpeeds;

    protected enum State {
        Alive, Scared, Dead
    }

    public Ghost(PacmanGame game) {
        super(game);
        loc = new Location(-1,-1);
        state = State.Alive;
    }

    public void updateState(State s) {
        if ( state == s )
            return;
        if ( s == State.Alive)
            speed = defaultSpeeds[0];
        else if ( s == State.Scared )
            speed = defaultSpeeds[1];
        else
            speed = defaultSpeeds[2];

    }
}
