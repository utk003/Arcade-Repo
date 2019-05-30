import java.util.*;
import java.util.concurrent.*;
import java.awt.Color;
/**
 * The Tetrad class creates and stores information for Tetrads, which are 
 * collections of four blocks. A tetrad keeps track of what blocks it has,
 * where these blocks are located, and on what Tetris board. There are 7 
 * types of tetrads. 
 *
 * @author  Aditya Singhvi
 * @version Mar. 9 2019
 */
public class Tetrad
{
    // instance variables - replace the example below with your own
    private Block[] blocks;
    private MyBoundedGrid<Block> board;
    private int center;
    private Semaphore lock;
    private int type;
    
    /**
     * Constructor for objects of class Tetrad. Creates a random Tetrad on the
     * given grid. 
     * 
     * @param grid  The grid on which the Tetrad will exist.
     */
    public Tetrad(MyBoundedGrid<Block> grid)
    {
        // initialise instance variables
        blocks = new Block[4];
        board = grid;
        center = 1; //change later if unreasonable
        lock = new Semaphore(1, true);
        
        int rand = (int) (7 * Math.random());
        type = rand;
        Location[] locs = new Location[4];
        Color color;
        if (rand == 0) //Block type I
        {
            color = Color.RED;
            locs[0] = new Location(0, 3);
            locs[1] = new Location(0, 4);
            locs[2] = new Location(0, 5);
            locs[3] = new Location(0, 6); //center = 1
        }
        else if (rand == 1) //Block type T
        {
            color = Color.GRAY;
            locs[0] = new Location(0, 4);
            locs[1] = new Location(0, 5);
            locs[2] = new Location(0, 6);
            locs[3] = new Location(1, 5); //center = 1
        }
        else if (rand == 2) //Block type O
        {
            color = Color.CYAN;
            locs[0] = new Location(0, 4);
            locs[1] = new Location(0, 5);
            locs[2] = new Location(1, 4);
            locs[3] = new Location(1, 5); 
        }
        else if (rand == 3) //Block type L
        {
            color = Color.YELLOW;
            locs[0] = new Location(0, 4);
            locs[1] = new Location(1, 4);
            locs[2] = new Location(2, 4);
            locs[3] = new Location(2, 5); 
        }
        else if (rand == 4) //Block type J
        {
            color = Color.MAGENTA;
            locs[0] = new Location(0, 5);
            locs[1] = new Location(1, 5);
            locs[2] = new Location(2, 5);
            locs[3] = new Location(2, 4);
        }
        else if (rand == 5) //Block type S
        {
            color = Color.BLUE;
            locs[0] = new Location(0, 6);
            locs[1] = new Location(0, 5);
            locs[2] = new Location(1, 5);
            locs[3] = new Location(1, 4);
        }
        else //Block type Z, rand == 6
        {
            color = Color.GREEN;
            locs[0] = new Location(0, 4);
            locs[1] = new Location(0, 5);
            locs[2] = new Location(1, 5);
            locs[3] = new Location(1, 6);
        }
        for (int i = 0; i < blocks.length; i++)
        {
            blocks[i] = new Block();
            blocks[i].setColor(color);
        }
        if (areEmpty(grid, locs))
            addToLocations(grid, locs);
    }

    /**
     * Constructor for a tetrad. Creates a tetrad of type myType (represented
     * by an integer from 0 to 6) on the given grid. 
     * 
     * @param grid The grid on which the tetrad is to be created
     * @param myType    The type of the tetrad to be created.
     */
    public Tetrad(MyBoundedGrid grid, int myType)
    {
        blocks = new Block[4];
        board = grid;
        center = 1; //change later if unreasonable
        lock = new Semaphore(1, true);
        type = myType;
        
        Location[] locs = new Location[4];
        Color color;
        if (type == 0) //Block type I
        {
            color = Color.RED;
            locs[0] = new Location(0, 3);
            locs[1] = new Location(0, 4);
            locs[2] = new Location(0, 5);
            locs[3] = new Location(0, 6); //center = 1
        }
        else if (type == 1) //Block type T
        {
            color = Color.GRAY;
            locs[0] = new Location(0, 4);
            locs[1] = new Location(0, 5);
            locs[2] = new Location(0, 6);
            locs[3] = new Location(1, 5); //center = 1
        }
        else if (type == 2) //Block type O
        {
            color = Color.CYAN;
            locs[0] = new Location(0, 4);
            locs[1] = new Location(0, 5);
            locs[2] = new Location(1, 4);
            locs[3] = new Location(1, 5); 
        }
        else if (type == 3) //Block type L
        {
            color = Color.YELLOW;
            locs[0] = new Location(0, 4);
            locs[1] = new Location(1, 4);
            locs[2] = new Location(2, 4);
            locs[3] = new Location(2, 5); 
        }
        else if (type == 4) //Block type J
        {
            color = Color.MAGENTA;
            locs[0] = new Location(0, 5);
            locs[1] = new Location(1, 5);
            locs[2] = new Location(2, 5);
            locs[3] = new Location(2, 4);
        }
        else if (type == 5) //Block type S
        {
            color = Color.BLUE;
            locs[0] = new Location(0, 6);
            locs[1] = new Location(0, 5);
            locs[2] = new Location(1, 5);
            locs[3] = new Location(1, 4);
        }
        else //Block type Z, rand == 6
        {
            color = Color.GREEN;
            locs[0] = new Location(0, 4);
            locs[1] = new Location(0, 5);
            locs[2] = new Location(1, 5);
            locs[3] = new Location(1, 6);
        }
        for (int i = 0; i < blocks.length; i++)
        {
            blocks[i] = new Block();
            blocks[i].setColor(color);
        }
        if (areEmpty(grid, locs))
            addToLocations(grid, locs);
    }
    
    /**
     * Removes the tetrad from its own board, setting the locations of all its
     * blocks to null.
     * 
     * @postcondition   This tetrad has been removed from its board.
     */
    public void remove()
    {
        for (int i = 0; i < blocks.length; i++)
        {
            blocks[i].removeSelfFromGrid();
        }
        board = null;
    }
    
    /**
    * Puts the tetrad blocks onto the specified tetris board at the 
    * specified locations.
    * 
    * @precondition     The blocks are not in any grid
    *                   There are exactly 4 specified locations.
    * @postcondition The locations of the blocks in the tetrad match locs.
    * @param grid   The grid to which the tetrad is to be added.
    * @param locs   The locations of the blocks on the grid where the tetrad 
    *               is to be added.
    *
    */
    private void addToLocations(MyBoundedGrid<Block> grid, Location[] locs)
    {
        for (int i = 0; i < blocks.length; i++)
        {
            Block bl = blocks[i];
            bl.putSelfInGrid(grid, locs[i]);
        }
    }
    
    /**
     * Removes the tetrad blocks from their current grid and returns the
     * previous locations of the blocks.
     * 
     * @precondition    Blocks are in the grid.
     * @postcondition   Returns old locations of blocks;
     *                  blocks have been removed from grid.
     * @return  An array of 4 Locations that shows the previous locations of 
     *          the blocks of this tetrad.
     */
    private Location[] removeBlocks()
    {
        Location[] locs = new Location[4];
        for (int i = 0; i < blocks.length; i++)
        {
            Block bl = blocks[i];
            locs[i] = bl.getLocation();
            bl.removeSelfFromGrid();
        }
        return locs;
    }
    
    /**
     * Returns the locations of this tetrad in a Location array containing
     * four elements, with each element representing the location of one of 
     * this Tetrads blocks on its grid.
     * 
     * @return  A Location array containing four elements, with each element 
     *          representing the location of one of this Tetrads blocks on 
     *          its grid.
     */
    public Location[] getLocs()
    {
        Location[] locs = new Location[4];
        for (int i = 0; i < blocks.length; i++)
        {
            Block bl = blocks[i];
            locs[i] = bl.getLocation();
        }
        return locs;
    }
    
    /**
     * Checks if the specified locations in the specified grid are empty. 
     * Returns true if the locations are empty, false otherwise.
     * 
     * @precondition  Each Location in locs must be a valid location in grid.
     * @postcondition Returns true if each of locs is
     *                valid and empty in grid;
     *                false otherwise.
     * @param grid  The grid on which the locations are to be checked for 
     *              emptiness.
     * @param locs  The locations to be checked for emptiness.
     * @return      true if every Location in locs is empty
     *              false otherwise    
     */
    private boolean areEmpty(MyBoundedGrid<Block> grid, Location[] locs)
    {
        for (int i = 0; i < locs.length; i++)
        {
            if (!board.isEmpty(locs[i]))
                return false;
        }
        return true;
    }
    
    /**
     * Translates this tetrad right by deltaCol and down by deltaRow, if
     * possible. Returns true if it was possible and the Tetrad was translated,
     * false otherwise.
     * 
     * @postcondition Attempts to move this tetrad deltaRow rows down and 
     *                deltaCol columns to the right, if those positions 
     *                are valid and empty; 
     *                returns true if successful and false otherwise.
     * @param deltaRow  The number of rows to be shifted down by.
     * @param deltaCol  The number of columns to be shifted right by. 
     * @return  true if this Tetrad could be and was translated.
     *          false otherwise.
     */
    public boolean translate(int deltaRow, int deltaCol)
    {
        try
        {
            lock.acquire();
            Location[] locs = new Location[4];
            for (int i = 0; i < blocks.length; i++)
            {   
                Location loc = new Location(
                                blocks[i].getLocation().getRow() + deltaRow,
                                blocks[i].getLocation().getCol() + deltaCol);
                locs[i] = loc;
            }
            Location[] orig = removeBlocks();
            if (areEmpty(board, locs))
            {
                addToLocations(board, locs);
                return true;
            }
            addToLocations(board, orig);
            return false;
        }
        catch (InterruptedException e)
        {
            // did not modify the tetrad
            return false;
        }
        finally
        {
            lock.release();
        }
    }
    
    /**
     * Returns the type of this tetrad, represented as an integer between 
     * 0 and 6.
     * 
     * @return  the type of this tetrad, represented as an integer between 
     *          0 and 6. 
     */
    public int getType()
    {
        return type;
    }
    
    /**
     * Rotates this tetrad 90˚ clockwise, if possible to do so. 
     * Returns true if it was possible and the Tetrad was rotated,
     * false otherwise.
     * 
     * @postcondition Attempts to rotate this tetrad 90˚ clockwise, 
     *                if the new positions are valid and empty; 
     *                returns true if successful and false otherwise
     * @return  true if this Tetrad could be and was rotated.
     *          false otherwise.
     */
    public boolean rotate()
    {
        try
        {
            lock.acquire();
            Location[] newLocs = new Location[4];
            for (int i = 0; i < blocks.length; i++)
            {
                int newRow = blocks[i].getLocation().getCol() + 
                            blocks[center].getLocation().getRow() - 
                            blocks[center].getLocation().getCol();
                int newCol = blocks[center].getLocation().getRow() + 
                            blocks[center].getLocation().getCol() -
                            blocks[i].getLocation().getRow();
                newLocs[i] = new Location(newRow, newCol);
            }
            Location[] orig = removeBlocks();
            if (areEmpty(board, newLocs))
            {
                addToLocations(board, newLocs);
                return true;
            }
            addToLocations(board, orig);
            return false;
        }
        catch (InterruptedException e)
        {
            // did not modify the tetrad
            return false;
        }
        finally
        {
            lock.release();
        }
    }
}
