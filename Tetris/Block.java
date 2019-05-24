import java.awt.Color;
/**
* Class Block encapsulates a Block abstraction which can be placed into a
* Gridworld style grid
* You are expected to comment this class according to the style guide.
* 
* @author Aditya Singhvi
* @version  March 6 2019
*/
public class Block
{
    private MyBoundedGrid<Block> grid;
    private Location location;
    private Color color;
    /**
    * constructs a blue block, because blue is the greatest color ever!
    */
    public Block()
    {
        color = Color.BLUE;
        grid = null;
        location = null;
    }
    /**
    * Returns the color of the block. 
    * 
    * @return   The Color of the block.
    */
    public Color getColor()
    {
        return color;
    }
    /**
    * Sets the color of the block to newColor.
    * 
    * @param newColor   The color that the color of the block will be set to.
    */
    public void setColor(Color newColor)
    {
        color = newColor;
    }
    
    /**
    * Returns the grid that the block is contained in.
    * 
    * @return   The grid that the block is stored in.
    */
    public MyBoundedGrid<Block> getGrid()
    {
        return grid;
    }
    
    /**
    * Returns the current location of the block.
    * 
    * @return The current location of the block.
    */
    public Location getLocation()
    {
        return location;
    }
    
    /**
    * Removes the block from the grid it is currently in, setting both the 
    * block's grid and the block's location to null. Also updates the grid 
    * to remove the block from the location it was in.
    * 
    * @postcondition    The block's grid has been set to null.
    *                   The block's location has been set to null.
    *                   The original grid of the block has been updated to not
    *                   contain this block anymore.
    */
    public void removeSelfFromGrid()
    {
        grid.remove(location);
        grid = null;
        location = null;
    }
    
    /**
    * Puts the block in grid gr at Location loc. Sets the blocks grid and 
    * location to gr and loc respectively, removes the block from its 
    * previous location, and updates gr to contain the block.
    * 
    * @param gr     The new grid that the block will be in. 
    * @param loc    The new location of the block.
    * @precondition     loc must be a valid location in Grid gr. 
    * @postcondition    The blocks grid and location have been updated.
    *                   Both the original and final grids have been updated
    *                   with respect to the block.
    */
    public void putSelfInGrid(MyBoundedGrid<Block> gr, Location loc)
    {
        if (grid != null)
            removeSelfFromGrid();
        grid = gr;
        location = loc;
        Block obj = grid.get(loc);
        if (obj != null)
        {
            obj.removeSelfFromGrid();
        }
        grid.put(loc, this);
    }

    /**
    * Moves the block to Location newLocation in its current grid. Updates 
    * the grid and location of the block, as well as the grid that the block 
    * is in to convey the change of location.
    * 
    * @param newLocation    The location that the block should be moved to.
    * @precondition     newLocation is a valid location in the current grid. 
    * @postconditon     The location of the block has been set to newLocation.
    *                   The grid has been updated to reflect the new location
    *                   of the block.
    * 
    */
    public void moveTo(Location newLocation)
    {
        MyBoundedGrid<Block> gr = grid;
        removeSelfFromGrid();
        putSelfInGrid(gr, newLocation);
    }

    /**
    * returns a string with the location and color of this block
    * 
    * @return A string with the location and color of this Block. 
    */
    public String toString()
    {
        return "Block[location=" + location + ",color=" + color + "]";
    }
}