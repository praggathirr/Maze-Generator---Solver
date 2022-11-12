package project3;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
//the bfs class used to solve the maze with the bfs method
public class BFS {
	
	    //used for marking whether the cell has been visited or not
		ArrayList<Cell> visited= new ArrayList<Cell>();
		//the stack containing the shortest path of cells
		Stack<Cell> shortestPath= new Stack<Cell>();
		//the maze of cells
	    Cell[][] maze;
	    //the dimension of the maze
	    int size;
		
	    //constructor
	    public BFS(Cell[][] maze)
	    {
	    	this.maze = maze;
	    	if(maze == null)
	    		this.size = 0;
	    	else
	    		this.size = maze.length;
	    }
	    //traverses using bfs
	    public boolean bfs()
	    {
	    	if(maze == null)
	    		return false;
	    	//the starting point of the maze
	    	Cell startPoint = maze[0][0];
	    	//queue for bfs
	        Queue<Cell> queue = new LinkedList<Cell>();
	        //the end of the maze
	        Cell endPoint = maze[size-1][size-1];
	        //starting point is added to queue for exploring
	        queue.add(startPoint);
	        //will keep exploring as long as there are cells to explore
	        while(!queue.isEmpty())
	        {
	        	//dequeue an element to explore
	        	Cell elementToExplore = queue.remove();
	        	//cell used to trace back to the path
	        	Cell backtrace_element = endPoint;
	            //the cell dequeued to explore is added to visited
	        	visited.add(elementToExplore);
	        	elementToExplore.setColor(Color.GRAY);
	        	//if we have gotten to the end point return true and the path
	        	if(elementToExplore.equals(endPoint))
	        	{
	        		elementToExplore.setColor(Color.BLACK);
	        		//use the parent to trace the entire path till will reach the starting point
	        		while(!backtrace_element.equals(startPoint)) {
	        			//every cells is added to the path
	        			shortestPath.add(backtrace_element);
	        			backtrace_element = backtrace_element.getParent();	        			
	        		}
	        		//start point added to the path
	        		shortestPath.add(startPoint);
	        		return true;
	        		//print the maze w hashes
	        	}
	        	ArrayList<Cell> neighbors = getPossibleNeighbors(elementToExplore);
	        	
	        	//explore all neighbors of the element if there are any
	        	if(neighbors.size()!=0)
	        	{
	        		for(Cell neighbor : neighbors)
		        	{
		        		//if we haven't explored this neighbor already
		        		if(neighbor.getColor() == Color.WHITE)
		        		{
		        			//set the current element to the neighbors parent
		        			neighbor.setParent(elementToExplore);
		        			//set neighbors color as gray
		        			neighbor.setColor(Color.GRAY);
		        			//add the neighbor to the queue to be explored
		        			queue.add(neighbor);
		        		}
		        	}
	        	}
	        	
	        	elementToExplore.setColor(Color.BLACK);
	        }
	        //if there is no path
			return false;
	    }
	    
	    //the method that returns all open neighbors
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
	    
	    //returns the list containing all the visited cells
	    public ArrayList<Cell> getVisitedCellsPath()
	    {
	    	return visited;
	    }
	    
	    //returns the stack containing the cells of the shortest path
	    public Stack<Cell> getShortestPath()
	    {
	    	Stack<Cell> reversePath = new Stack<Cell>();
	    	while(!shortestPath.isEmpty())
	    	{
	    		reversePath.push(shortestPath.pop());
	    	}
	    	return reversePath;
	    }
	    
	    //returns the maze
	    public Cell[][] getMaze()
		{
			return maze;
		}
	   
}
