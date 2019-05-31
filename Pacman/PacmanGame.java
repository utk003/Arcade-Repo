import java.io.*;

public class PacmanGame extends Game
{
    public int[][] spawnpoints;
    
    public BoardPiece[][] board;
    public BoardPlayer[] players;
    
    public static int fps = 20;
    
    public Location.Direction dir;
    
    public int lives;

    public BoardPiece getBoardPiece(int r, int c)
    {
        return board[r][c];
    }

    public BoardPlayer[] getPlayers()
    {
        return players;
    }

    public int getNumRows()
    {
        return board.length;
    }

    public int getNumCols()
    {
        return board[0].length;
    }

    public PacmanGame()
    {
        dir = Location.Direction.NONE;
        players = createPlayers();
        createBoard();
        setSpawn();
        lives = 3;
        display = new PacmanDisplay(this);
        play();
    }
    
    public void createBoard()
    {
        String level = "";
        String[] spawnpointsString = new String[] {""};
        int r = 0, c = 0;
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("Assets/Levels/Level1"));
            String[] line = br.readLine().split(" ");
            r = Integer.parseInt(line[0]);
            c = Integer.parseInt(line[1]);
            for ( int i = 0; i < r; i++ )
                level += br.readLine();
            spawnpointsString = br.readLine().split(" ");
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        board = new BoardPiece[r][c];
        
        String[] levelRep = level.split("");
        for ( int i = 0; i < levelRep.length; i++ )
        {
            int rCoord = i / c, cCoord = i % c;
            String piece = levelRep[i];
            if ( piece.equals(" ") )
                board[rCoord][cCoord] = null;
            else if ( piece.equals(".") )
                board[rCoord][cCoord] = new Pellet("small");
            else if ( piece.equals("*") )
                board[rCoord][cCoord] = new Pellet("big");
            else
                board[rCoord][cCoord] = new Wall(piece);
        }
        
        spawnpoints = new int[spawnpointsString.length][2];
        
        for ( int i = 0; i < spawnpointsString.length; i++ )
        {
            String[] coords = spawnpointsString[i].split(",");
            spawnpoints[i][0] = Integer.parseInt( coords[0] );
            spawnpoints[i][1] = Integer.parseInt( coords[1] );
        }
    }
    
    public void setSpawn()
    {
        for ( int i = 0; i < players.length; i++ )
        {
            players[i].loc = new Location( spawnpoints[i][0], spawnpoints[i][1] );
            if ( i == 0 )
            {
                Pacman p = (Pacman) players[0];
                p.updateState(Pacman.State.ALIVE);
                p.dir = Location.Direction.NONE;
            }
            else
            {
                Ghost g = (Ghost) players[i];
                g.updateState(Ghost.State.ALIVE);
                if ( i == 1 )
                    g.dir = Location.Direction.UP;
                else if ( i == 2 )
                    g.dir = Location.Direction.LEFT;
                else if ( i == 3 )
                    g.dir = Location.Direction.RIGHT;
                else if ( i == 4 )
                    g.dir = Location.Direction.DOWN;
            }
        }
    }
    
    public boolean gameOver()
    {
        return lives <= 0;
    }
    
    public void play()
    {
        // Start Player Threads
        MyThread[] threads = new MyThread[players.length];
        for ( int i = 0; i < 1/*players.length*/; i++ )
        {
            threads[i] = new MyThread( players[i], this );
            threads[i].start();
        }
        
        while ( ! gameOver() )
        {
            display.repaint();
        }
    }

    private BoardPlayer[] createPlayers()
    {
        BoardPlayer[] board = new BoardPlayer[5];
        board[0] = new Pacman(this);
        board[1] = new Shadow(this);  // Red
        board[2] = new Bashful(this); // Cyan
        board[3] = new Pokey(this);   // Orange
        board[4] = new Speedy(this);  // Pink
        return board;
    }

    public boolean isValid(Location loc)
    {
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

    public Location[] getAdjacent(Location loc)
    {
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
