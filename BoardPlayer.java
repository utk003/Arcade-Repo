public abstract class BoardPlayer {
    protected Location.Direction dir;
    protected Location loc;
    protected Location.Direction[] dirs =
            {Location.Direction.UP, Location.Direction.RIGHT, Location.Direction.DOWN, Location.Direction.LEFT};
    protected PacmanGame game;

    public BoardPlayer(PacmanGame game) {
        this.game = game;
    }

    public abstract void move();

    public Location getLocation() {
        return loc;
    }
    public Location.Direction getDirectionFacing() {
        return dir;
    }
}
