import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;

public abstract class BoardPiece {
    protected BoardPiece.Direction dir;

    public enum Direction {
        PELLET, UP_DOWN, LEFT_RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT
    }

    public Image getImage()
    {
        throw new RuntimeException("IMPLEMENT ME");
    }
    
    public Direction getDirection() {
        return dir;
    }
}
