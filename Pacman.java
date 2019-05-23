public class Pacman extends BoardPlayer {

    public Pacman(PacmanGame game) {
        super(game);
        loc = new Location(-1, -1);
        dir = Location.Direction.NONE;
    }

    public void move() {

    }
}
