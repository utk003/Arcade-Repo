import java.awt.*;
import javax.swing.*;

public final class Wall extends BoardPiece
{
    public Wall( String s )
    {
        sprite = new ImageIcon("Assets/Walls/" + s + ".png").getImage();
    }
}
