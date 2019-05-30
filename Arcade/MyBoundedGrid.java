import java.util.*;
/**
 * The MyBoundedGrid creates a grid capable of storing objects of type E. The
 * grid is bounded and has a maximum number of rows and columns.
 *
 * @author  Aditya Singhvi
 * @version March 6 2019
 * 
 * @param <E> The type of object to be stored in the MyBoundedGrid.
 */
public class MyBoundedGrid<E>
{
    private int rows;
    private int cols;
    private Object[][] grid;
    
    /**
     * Creates a new MyBoundedGrid with the number of rows r and the number of 
     * cols c. 
     * 
     * @param r     The number of rows in the MyBoundedGrid.
     * @param c     The number of columns in the MyBoundedGrid. 
     * @postconditon    A new MyBoundedGrid has been created with numRows r and
     *                  numCols c. 
     */
    public MyBoundedGrid(int r, int c)
    {
        rows = r;
        cols = c;
        grid = new Object[rows][cols];
    }
    
    /**
     * Returns the number of rows in this grid. 
     * 
     * @return  The number of rows in this grid.
     */
    public int getNumRows()
    {
        return rows;
    }
    
    /**
     * Returns the number of columns in this grid. 
     * 
     * @return  The number of columns in this grid.
     */
    public int getNumCols()
    {
        return cols;
    }
    
    /**
     * Clears all objects on this grid, setting every element to null.
     * 
     * @postconditon    No objects remain on this grid. 
     */
    public void clearAll()
    {
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < cols; c++)
            {
                grid[r][c] = null;
            }
        }
    }
    
    /**
     * Checks if a location is valid in this grid by ensuring that the row and 
     * column of the location are non-negative and less than the maximum row and
     * column.
     * 
     * @param loc   The Location to be checked for validity. 
     * @return  true if the location is valid
     *          false otherwise
     */
    public boolean isValid (Location loc)
    {
        if (loc == null)
            return false;
        boolean rowValid = loc.getRow() < rows && loc.getRow() >= 0;
        boolean colValid = loc.getCol() < cols && loc.getCol() >= 0;
        return rowValid && colValid;
    }
    
    /**
     * Puts the object obj of type E at Location loc in the grid. Returns the
     * object that was previously stored at that location. 
     * 
     * @param loc    The location at which to put the obj
     * @param obj    The object to be put at Location loc
     * @return      The object previously stored at Location loc. 
     */
    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
        {
            throw new IllegalArgumentException("Invalid location");
        }
        E orig = get(loc);
        int r = loc.getRow();
        int c = loc.getCol();
        grid[r][c] = obj;
        return orig;
    }
    
    /**
     * Removes and returns the object at Location loc. 
     * 
     * @param loc   The Location from which the object is to be removed. 
     * @return      The object that was just removed from loc. 
     */
    public E remove(Location loc)
    {
        if (!isValid(loc))
        {
            throw new IllegalArgumentException("Invalid location");
        }
        E orig = get(loc);
        put(loc, null);
        return orig;
    }
    
    /**
     * Returns the object stored at Location loc in the grid. 
     * 
     * @param loc   The location from which the object is to be retrieved.
     * @return      The object stored at loc. 
     */
    public E get(Location loc)
    {
        if (!isValid(loc))
        {
            throw new IllegalArgumentException("Invalid location");
        }
        int r = loc.getRow();
        int c = loc.getCol();
        return (E) grid[r][c];
    }
    
    /**
     * Checks if a given location is empty and valid. 
     * Returns true if it is both empty and valid, false otherwise
     * 
     * @param loc   The location to be checked
     * @return      true if the location is empty and valid for this grid
     *              false otherwise
     */
    public boolean isEmpty(Location loc)
    {
        return isValid(loc) && get(loc) == null;
    }
    
    /**
     * Creates and returns an ArrayList of all occupied locations in the grid. 
     * 
     * @return  An ArrayList of all occupied Locations in the grid. 
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> arr = new ArrayList<Location>();
        for (int i = rows - 1; i >= 0; i--)
        {
            for (int j = 0; j < cols; j++)
            {
                Location loc = new Location(i, j);
                if (get(loc) != null)
                {
                    arr.add(loc);
                }
            }
        }
        return arr;
    }
}
