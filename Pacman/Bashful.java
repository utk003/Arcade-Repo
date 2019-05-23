/**
 * Bashful is the cyan ghost
 * Bashful avoids Pacman at first but retaliates if attacked
 */
public class Bashful extends Ghost {
    private boolean attacked = false;

    public Bashful(PacmanGame game) {
        super(game);
        dir = Location.Direction.LEFT;
    }

    public void move() {
        if ( ! attacked )
            flee();
        else
            attack();
    }

    private void flee() {
        Location[] locs = game.getAdjacent(loc);
        double maxDist = -1;
        int maxDistIndex = -1;
        for ( int i = 0; i < locs.length; i++ ) {
            if ( locs[i] != null ) {
                double dist = loc.getDistanceTo(locs[i]);
                if (dist > maxDist) {
                    maxDist = dist;
                    maxDistIndex = i;
                }
            }
        }
        loc = locs[maxDistIndex];
        dir = dirs[maxDistIndex];
    }

    private void attack() {
        Location[] locs = game.getAdjacent(loc);
        double minDist = 100;
        int minDistIndex = -1;
        for ( int i = 0; i < locs.length; i++ ) {
            if ( locs[i] != null ) {
                double dist = loc.getDistanceTo(locs[i]);
                if (dist < minDist) {
                    minDist = dist;
                    minDistIndex = i;
                }
            }
        }
        loc = locs[minDistIndex];
        dir = dirs[minDistIndex];
    }
}
