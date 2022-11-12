package project3;

import java.awt.Color;
//the cell class for representing every room of the maze
public class Cell
{
	//the 4 walls represented as booleans
	private boolean north;
	private boolean south;
	private boolean east;
	private boolean west;
	//the rows and colums of the cell
	private int row;
	private int col;
	//the color property to mark the cell colors
	private Color color;
	//the parent cell
	Cell parent;

	/**
	 * the constructor for cell class
	 * @param row the row number of the cell
	 * @param col the column number of the cell
	 */
	public Cell(int row, int col)
	{
		this.row = row;
		this.col = col;
		north = south = east = west = true;
		color = Color.WHITE;
		parent = null;
	}
  
	/**
	 * the method to get the row
	 * @return the row number of the cell
	 */
	public int getRow()
	{
		return row;
	}

	/**
	 * the method to get the column
	 * @return the column number of the cell
	 */
	public int getCol()
	{
		return col;
	}

	/**
	 * checks if the north wall is there
	 * @return north as true if the wall exists
	 */
	public boolean getNorth()
	{
		return north;
	}

	/**
	 * checks if the south wall is there
	 * @return south as true if the wall exists
	 */
	public boolean getSouth()
	{
		return south;
	}
  
	/**
	 * checks if the east wall is there
	 * @return east as true if the wall exists
	 */
	public boolean getEast()
	{
		return east;
	}

	/**
	 * checks if the west wall is there
	 * @return west as true if the wall exists
	 */
	public boolean getWest()
	{
		return west;
	}

	/**
	 * checks if all walls exist
	 * @returns true if all walls exist
	 */
	public boolean allWallsIntact()
	{
		if(north && south && east && west)
			return true;
		return false;
	}
	
	/**
	 * sets north to false
	 */
	public void removeNorth()
	{
		north = false;
	}
	
	/**
	 * sets south to false
	 */
	public void removeSouth()
	{
		south = false;
	}
	
	/**
	 * sets east to false
	 */
	public void removeEast()
	{
		east = false;
	}
	
	/**
	 * sets west to false
	 */
	public void removeWest()
	{
		west = false;
	}
	
	/**
	 * uses the row and column of the cell to create its location
	 * @returns the cell location as a string
	 */
	public String getLocation()
	{
		String location = "(" + row + ", " + col + ")";
		return location;
	}
	
	/**
	 * to get the color of the cell
	 * @returns the current color of the cell
	 */
	public Color getColor()
	{
		return color;
	}
	
	/**
	 * used to set the color of the cell
	 * @param color is set as the color of the cell
	 */
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	/**
	 * used to set the parent of a cell
	 * @param cell is set as the parent of the cell
	 */
	public void setParent(Cell cell)
	{
		parent = cell;
	}
	
	/**
	 * used to get the parent of the cell
	 * @returns the parent of the cell
	 */
	public Cell getParent()
	{
		return parent;
	}
	
	/**
	 * compares two cells, is true if they are equal
	 */
	public boolean equals(Object obj)
	{
		Cell cell = (Cell)obj;
		return this.row == cell.row && this.col == cell.col;
	}

}

