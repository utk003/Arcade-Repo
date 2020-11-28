package pacman;

import javax.swing.*;

public final class Pellet extends BoardPiece {
    public boolean big;

    public Pellet(String s) {
        big = s.equalsIgnoreCase("big");
        sprite = new ImageIcon("assets/pacman/pellets/" + s + ".png").getImage();
    }
}
