import java.awt.*;
import javax.swing.*;
import java.util.*;

public abstract class Ghost extends BoardPlayer
{
    protected State state;

    // Fill in later
    protected double[] defaultSpeeds;
    
    protected int scaredDuration;
    
    protected java.util.List<Location> placesGone;

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
            if ( state == State.DEAD )
                placesGone = new LinkedList<Location>();
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
        updateSpriteCounter();
        int diff = spriteCounter / 2;
        if ( state == State.SCARED )
        {
            scaredDuration++;
            if ( scaredDuration > game.fps * 5 ) // Scare Mode Over
            {
                updateState(State.ALIVE);
                for ( int i = 0; i < 4; i++ )
                    if ( dir == dirs[i] )
                        return sprites[2 * i + diff];
            }
            else if ( scaredDuration > game.fps * 4 ) // Scare Mode About To End
            {
                if ( scaredDuration % 8 < 4 )
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
        placesGone.add( loc );
    }
    
    protected void flee()
    {
        
    }
    
    protected void attack()
    {
        
    }
    
    protected void heal()
    {
        
    }
}
