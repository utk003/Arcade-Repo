import java.util.ArrayList;

public class PacmanGame extends Game {

    public BoardPiece[][] board;
    public BoardPlayer[] characters;

    public BoardPiece getBoardPiece(int r, int c) {
        return board[r][c];
    }

    public BoardPlayer[] getPlayers() {
        return characters;
    }

    public int getNumRows() {
        return board.length;
    }

    public int getNumCols() {
        return board[0].length;
    }

    public PacmanGame(int r, int c) {
        board = new BoardPiece[r][c];
        characters = createPlayers();
    }

    public void play() {

    }

    private BoardPlayer[] createPlayers() {
        BoardPlayer[] board = new BoardPlayer[5];
        board[0] = new Pacman(this);
        board[1] = new Shadow(this);  // Red
        board[2] = new Bashful(this); // Cyan
        board[3] = new Pokey(this);   // Orange
        board[4] = new Speedy(this);  // Pink
        return board;
    }

    public boolean isValid(Location loc) {
        double x = loc.getRow(), y = loc.getCol();
        if ( x < 0 || y < 0 )
            return false;
        if ( x >= board.length || y >= board[0].length )
            return false;
        int xInt = (int) x, yInt = (int) y;
        if ( board[xInt][yInt] instanceof Wall )
                return false;
        return true;
    }

    public Location[] getAdjacent(Location loc) {
        double x = loc.getRow(), y = loc.getCol();
        Location[] locs = new Location[4];
        Location l;

        l = new Location(x - 1, y);
        if ( isValid(l) )
            locs[0] = l;

        l = new Location(x,y + 1);
        if ( isValid(l) )
            locs[1] = l;

        l = new Location(x + 1, y);
        if ( isValid(l) )
            locs[2] = l;

        l = new Location(x,y - 1);
        if ( isValid(l) )
            locs[3] = l;

        return locs;
    }
}
