package project3;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;
//the dfs class used to solve the maze using the dfs algorithm
public class DFS {

	//stack to store the cells of the shortest path
	Stack<Cell> shortestPath = new Stack<Cell>();
	//list to store all the cells visited
	ArrayList<Cell> visited= new ArrayList<>();
//	String stringMaze;
	//the maze
    Cell[][] maze;
    int time;
	int size;
	//constructor for dfs
	 public DFS(Cell[][] maze)
    {
    	this.maze = maze;
    	if(maze == null)
    		this.size = 0;
    	else
    		this.size = maze.length;
    } 
    
	 //method to return the maze
	public Cell[][] getMaze()
	{
		return maze;
	}
	//the dfs method to traverse through the entire maze
	//begins from the start point of the maze
	public boolean dfs()
	{
		if(maze == null)
			return false;
		boolean search = dfsvisit(maze[0][0]);
		if(search==true)
		{
			//if a path is found
			return true;
		}
		//if a path isnt found
		return false;
	}
	

	//dfs visit method to do dfs search on each cell
    public boolean dfsvisit(Cell c)
    {
    	//the color of the cell is set to gray
    	c.setColor(Color.GRAY);
    	//the cell is added to the visited list and the path stack
    	visited.add(c);
    	shortestPath.push(c);
    	//the last cell of the maze
    	Cell last = maze[size-1][size-1];
    	//if we have reached the end point return true
    	if(c.equals(last))
    	{
    		c.setColor(Color.BLACK);
    		return true;
    	}
    	//gets all open neighbors of the cell using the method
    	ArrayList<Cell> neighbors = getPossibleNeighbors(c);
    	//if there are no open neighbors pop the path stack
    	if(neighbors.size()==0)
    	{
    		shortestPath.pop();
    	}
    	//if there are open neighbors
    	else if(neighbors.size()!=0)
    	{
    		for(Cell n : neighbors)
    		{
    			//if the neighbor has not been explored
    			if(n.getColor()==Color.WHITE)
    			{
    				//if the neighbor leads to a path
    				if(dfsvisit(n))
    				{
    					return true;
    				}
    				//remove the cell from the path
    				else
    					shortestPath.pop();

    			}
    		}
    		//cell has been explored
    		c.setColor(Color.BLACK);
    	}
    	//shouldnt get here but if path has not been found
    	return false;    	
    }
    
    //method that returns neighbors with open walls
	public ArrayList<Cell> getPossibleNeighbors(Cell cell)
    {
    	ArrayList<Cell> possibleNeigbors = new ArrayList<Cell>();
		int row = cell.getRow();
		int col = cell.getCol();
		//north
		if((!cell.getNorth()) && row > 0)
		{
			possibleNeigbors.add(maze[row-1][col]);
		}
		//east
		if((!cell.getEast()) && col < size-1 )
		{
			possibleNeigbors.add(maze[row][col+1]);
		}
		//south
		if((!cell.getSouth()) && row < size-1)
		{
			possibleNeigbors.add(maze[row+1][col]);
		}
		//west
		if((!cell.getWest()) && col > 0)
		{
			possibleNeigbors.add(maze[row][col-1]);
		}
		return possibleNeigbors;
    }
	
	//returns the stack containing cells of the path
	public Stack<Cell> getShortestPath()
    {
    	return shortestPath;
    }
	
	//returns the list containing all visited cells
	public ArrayList<Cell> getVisitedCellsPath()
	{
		return visited;
	}


}
