package project3;

import java.util.*;
//the class to generate a maze
public class GenerateMaze 
{
	//the 2D Cell array for the maze
	private Cell[][] maze;
	//the size of the maze
	private int size;
	
	//constructor for the generate maze class
	public GenerateMaze(int size)
	{
		this.size = size;
		if(size == 0)
			maze = null;
		else
		{
			maze = new Cell[size][size];
			createInitialMaze();
		}
	}
	
	/**
	 * method to create the initial maze with only the starting point open
	 */
	public void createInitialMaze()
	{
		for(int i = 0; i < maze.length; i++)
		{
			for(int j = 0; j < maze[i].length; j++)
			{
				maze[i][j] = new Cell(i,j);
			}
		}
		maze[0][0].removeNorth();
		
	}
	
	/**
	 * method to generate the perfect maze with only one solution 
	 */
	public void generate()
	{
		if(maze == null)
			return;
		Stack<Cell> cellStack = new Stack<Cell>();
		int totalCells = size*size;
		Cell currentCell = maze[0][0];
		int visitedCells = 1;
		Random random = new Random();
		random.setSeed(21);
		while(visitedCells < totalCells)
		{
			ArrayList<Cell> intactNeighbors = getIntactNeighbors(currentCell);
			if(intactNeighbors.size() >= 1)
			{
				
				int num = random.nextInt(intactNeighbors.size());
				Cell nextCell = intactNeighbors.get(num);
				removeWall(currentCell, nextCell);
				cellStack.push(currentCell);
				currentCell = nextCell;
				visitedCells++;
			}
			else
			{
				currentCell = cellStack.pop();
			}
		}

		maze[size-1][size-1].removeSouth();
		
	}
	
	/**
	 * a method to return all closed neighbors of the cell
	 * @param cell the cells whose neighbors we want
	 * @returns an arraylist containing the intact neighbors of the cell
	 */
	public ArrayList<Cell> getIntactNeighbors(Cell cell)
	{
		ArrayList<Cell> intactNeighbors = new ArrayList<Cell>();
		int row = cell.getRow();
		int col = cell.getCol();
		if(row > 0 && maze[row-1][col].allWallsIntact())
		{
			intactNeighbors.add(maze[row-1][col]);
		}
		if(row < size-1 && maze[row+1][col].allWallsIntact())
		{
			intactNeighbors.add(maze[row+1][col]);
		}
		if(col < size-1 && maze[row][col+1].allWallsIntact())
		{
			intactNeighbors.add(maze[row][col+1]);
		}
		if(col > 0 && maze[row][col-1].allWallsIntact())
		{
			intactNeighbors.add(maze[row][col-1]);
		}
		return intactNeighbors;
	}
	
	/**
	 * method to knock down the wall between 2 given cells
	 * @param cell1 the first cell
	 * @param cell2 the second cell
	 */
	public void removeWall(Cell cell1, Cell cell2)
	{
		int row1 = cell1.getRow();
		int row2 = cell2.getRow();
		int col1 = cell1.getCol();
		int col2 = cell2.getCol();
		if(row2 == row1+1)
		{
			maze[row1][col1].removeSouth();
			maze[row2][col2].removeNorth();
		}
		else if(row2 == row1-1)
		{
			maze[row1][col1].removeNorth();
			maze[row2][col2].removeSouth();
			
		}
		else if(col2 == col1+1)
		{
			maze[row1][col1].removeEast();
			maze[row2][col2].removeWest();
		}
		else if(col2 == col1-1)
		{
			maze[row1][col1].removeWest();
			maze[row2][col2].removeEast();
		}
	}
	
	/**
	 * method that gets the maze
	 * @returns the maze
	 */
	public Cell[][] getMaze()
	{
		return maze;
	}
	  
}
	
