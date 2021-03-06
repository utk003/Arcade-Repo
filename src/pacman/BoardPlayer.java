package pacman;

import java.awt.*;

public abstract class BoardPlayer {
    protected Location.Direction dir;
    public Location loc;
    protected Location.Direction[] dirs =
            {Location.Direction.UP, Location.Direction.RIGHT, Location.Direction.DOWN, Location.Direction.LEFT};
    protected PacmanGame game;

    protected int spriteCounter;

    protected Image[] sprites;

    protected double speed;

    private static final double moveDist = 0.2;

    private int spriteToggle;

    public BoardPlayer(PacmanGame game) {
        this.game = game;
        spriteCounter = 0;
        spriteToggle = 0;
    }

    public abstract Image getImage();

    public Location getLocation() {
        return loc;
    }

    public Location.Direction getDirectionFacing() {
        return dir;
    }

    public abstract void updateDirection();

    protected void updateSpriteCounter() {
        if (spriteToggle == 100) {
            spriteCounter++;
            spriteToggle = -1;
        }
        spriteToggle++;
        if (spriteCounter == 4)
            spriteCounter = 0;
    }

    public void move() {
        Location newLoc = getMoveDest();
        if (newLoc != null)
            loc = newLoc;
    }

    protected Location getMoveDest() {
        Location newLoc = loc;
        if (dir == Location.Direction.UP || dir == Location.Direction.DOWN) {
            double r = loc.getRow();
            double c = loc.getCol();
            if (dir == Location.Direction.UP)
                r -= moveDist;
            else
                r += moveDist;
            newLoc = new Location(r, 0.5 + (int) c);
        } else if (dir == Location.Direction.LEFT || dir == Location.Direction.RIGHT) {
            double r = loc.getRow();
            double c = loc.getCol();
            if (dir == Location.Direction.LEFT)
                c -= moveDist;
            else
                c += moveDist;
            newLoc = new Location(0.5 + (int) r, c);
        }
        if (game.isValid(newLoc))
            return newLoc;
        else {
            double r = newLoc.getRow(), c = newLoc.getCol();
            if (r >= 14 && r < 16) {
                if (c < 0)
                    return new Location(r, c + 30);
                else if (c >= 30)
                    return new Location(r, c - 30);
            }
        }
        return null;
    }
}
