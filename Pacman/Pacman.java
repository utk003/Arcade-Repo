 import java.awt.*;
 import javax.swing.*;

public class Pacman extends BoardPlayer
{
    public State state;
    private int deadCounter;
    private int spriteToggle = -1;
    
    public enum State
    {
        ALIVE, DEAD
    }
    
    // Sprites Array
    // U1, U2, R1, R2, D1, D2, L1, L2, pac, dead 0..10
    
    public Pacman(PacmanGame game)
    {
        super(game);
        loadSprites();
        speed = 0.1;
    }
    
    protected void loadSprites()
    {
        sprites = new Image[20];
        String[] dirs = {"U","R","D","L"};
        String[] scareCol = {"B","W"};
        int ind = 0;
        for ( String dir: dirs )
            for ( int i = 1; i <= 2; i++ )
            {
                sprites[ind] = new ImageIcon("Assets/pac" + dir + i + ".png").getImage();
                ind++;
            }
        sprites[ind] = new ImageIcon("Assets/pac.png").getImage();
        ind++;
        for ( int i = 0; i <= 10; i++ )
        {
            sprites[ind] = new ImageIcon("Assets/death" + i + ".png").getImage();
            ind++;
        }
    }
    
    public void updateDirection()
    {
        dir = game.dir;
    }
    
    public void updateState(State s)
    {
        if (state == s)
            return;
        if (s == State.ALIVE)
            deadCounter = 0;
        state = s;
    }
    
    public Image getImage()
    {
        updateSpriteCounter();
        if ( state == State.ALIVE )
        {
            if ( dir == Location.Direction.NONE )
                return sprites[8];
            int spriteShift = spriteCounter / 2;
            for ( int i = 0; i < 4; i++ )
                if ( dir == dirs[i] )
                    return sprites[2*i + spriteShift];
        }
        else if ( state == State.DEAD )
        {
            spriteToggle++;
            if ( spriteToggle == 100 )
            {
                deadCounter++;
                spriteToggle = -1;
            }
            if ( deadCounter <= 11 )
                return sprites[8 + deadCounter];
            else
                respawn();
        }
        return null;
    }
    
    public void respawn()
    {
        game.setSpawn();
        game.lives--;
    }
}
