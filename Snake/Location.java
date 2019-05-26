
/**
 * Write a description of class Location here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Location
{
    private int row;
    private int col;
    /**
     * The compass direction for north.
     */
    public static final int NORTH = 0;
    /**
     * The compass direction for northeast.
     */
    public static final int NORTHEAST = 45;
    /**
     * The compass direction for east.
     */
    public static final int EAST = 90;
    /**
     * The compass direction for southeast.
     */
    public static final int SOUTHEAST = 135;
    /**
     * The compass direction for south.
     */
    public static final int SOUTH = 180;
    /**
     * The compass direction for southwest.
     */
    public static final int SOUTHWEST = 225;
    /**
     * The compass direction for west.
     */
    public static final int WEST = 270;
    /**
     * The compass direction for northwest.
     */
    public static final int NORTHWEST = 315;
    
    public Location(int myRow, int myCol)
    {
        row = myRow;
        col = myCol;
    }
    
    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return col;
    }
    
    public boolean equals(Location l)
    {
        return row == l.row && col == l.col;
    }
}
