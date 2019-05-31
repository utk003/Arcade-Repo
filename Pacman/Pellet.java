import java.awt.*;
import javax.swing.*;

public final class Pellet extends BoardPiece
{
    public boolean big;
    
    public Pellet( String s )
    {
        big = s.equalsIgnoreCase("big");
        sprite = new ImageIcon("Assets/p" + s + ".png").getImage();
    }
}
