import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.*;

public abstract class Ghost extends BoardPlayer
{
    protected State state;

    // Fill in later
    protected double[] defaultSpeeds;
    
    protected int scaredDuration;
    
    protected LinkedList<Location> placesGone;

    // Sprites Array
    // U1, U2, R1, R2, D1, D2, L1, L2, ScareB1, ScareB2, ScareW1, ScareW2, EyesU, EyesR, EyesD, EyesL


    public enum State
    {
        ALIVE, SCARED, DEAD
    }

    public Ghost(PacmanGame game)
    {
        super(game);
        scaredDuration = 0;
        loc = new Location(-1, -1);
        placesGone = new LinkedList<Location>();
    }

    protected void loadSprites( String color )
    {
        sprites = new Image[16];
        String[] dirs = {"U","R","D","L"};
        String[] scareCol = {"B","W"};
        int ind = 0;
        for ( String dir: dirs )
            for ( int i = 1; i <= 2; i++ )
            {
                sprites[ind] = new ImageIcon("Assets/" + color + dir + i + ".png").getImage();
                ind++;
            }
        for ( String c: scareCol )
            for ( int i = 1; i <= 2; i++ )
            {
                sprites[ind] = new ImageIcon("Assets/scared" + c + i + ".png").getImage();
                ind++;
            }
        for ( String dir: dirs )
        {
            sprites[ind] = new ImageIcon("Assets/eyes" + dir + ".png").getImage();
            ind++;
        }
    }

    public void updateState(State s)
    {
        if (state == s)
            return;
        if (s == State.ALIVE)
        {
            speed = defaultSpeeds[0];
        }
        else if (s == State.SCARED)
        {
            speed = defaultSpeeds[1];
            scaredDuration = 0;
        }
        else
            speed = defaultSpeeds[2];
        state = s;
    }

    public Image getImage()
    {
        if ( game.pacIsDead() )
            return null;
        updateSpriteCounter();
        int diff = spriteCounter / 2;
        if ( state == State.SCARED )
        {
            scaredDuration++;
            if ( scaredDuration > 10000 ) // Scare Mode Over
            {
                updateState(State.ALIVE);
                return getImage();
            }
            else if ( scaredDuration > 8000 ) // Scare Mode About To End
            {
                if ( scaredDuration % 250 < 125 )
                    return sprites[10 + diff];
                else
                    return sprites[8 + diff];
            }
            else // Scare Mode ON
                return sprites[8 + diff];
        }
        else if ( state == State.ALIVE )
        {
            for ( int i = 0; i < 4; i++ )
                if ( dir == dirs[i] )
                    return sprites[2 * i + diff];
        }
        else if ( state == State.DEAD )
        {
            for ( int i = 0; i < 4; i++ )
                if ( dir == dirs[i] )
                    return sprites[12 + i];
        }
        return null;
    }
    
    @Override
    public void move()
    {
        super.move();
        if ( state != State.DEAD )
            placesGone.add( loc );
    }
    
    @Override
    public void updateDirection()
    {
        if ( state == State.ALIVE )
            attack();
        else if ( state == State.SCARED )
            flee();
        else if ( state == State.DEAD )
            heal();
    }
    
    protected void chase()
    {
        Location[] locs = game.getAdjacent(loc);
        Location pac = game.players[0].loc;
        int ind = 0;
        double minDist = 100;
        for ( int i = 0; i < locs.length; i++ )
        {
            if ( locs[i] != null )
            {
                double dist = locs[i].getDistanceTo(pac);
                if ( dist < minDist )
                {
                    ind = i;
                    minDist = dist;
                }
            }
        }
        dir = dirs[ind];
    }
    
    protected void run()
    {
        Location[] locs = game.getAdjacent(loc);
        Location pac = game.players[0].loc;
        int ind = 0;
        double maxDist = 0;
        for ( int i = 0; i < locs.length; i++ )
        {
            if ( locs[i] != null )
            {
                double dist = locs[i].getDistanceTo(pac);
                if ( dist > maxDist )
                {
                    ind = i;
                    maxDist = dist;
                }
            }
        }
        dir = dirs[ind];
    }
    
    protected void random()
    {
        while ( getMoveDest() == null )
            dir = dirs[(int) ( Math.random() * dirs.length )];
    }
    
    protected void flee()
    {
        run();
    }
    
    protected abstract void attack();
    
    protected void heal()
    {
        if ( placesGone.size() == 0 )
        {
            updateState( State.ALIVE );
            updateDirection();
        }
        else
            dir = loc.getDirectionTo( placesGone.removeLast() );
    }
}
