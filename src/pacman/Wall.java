package pacman;

import javax.swing.*;

public final class Wall extends BoardPiece
{
    public Wall( String s )
    {
        sprite = new ImageIcon("assets/pacman/walls/" + s + ".png").getImage();
    }
}
