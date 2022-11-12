package project3;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
/**
 * the class used to print the mazes and their solutions 
 * to a text file
 */
public class PrintMazes 
{
	Cell[][] maze;
	Stack<Cell> shortestPath;
	ArrayList<Cell> visitedCells;
	int size;
	FileWriter fw;
	PrintWriter pw;
	String printMaze;
	String printVisited;
	String printShortest;
	
	/**
	 * the constructor for print mazes
	 */
	public PrintMazes()
	{
		printMaze = printVisited = printShortest = "";
	}
	
	/**
	 * the method that sets the string representation for the maze,
	 * its shortest path and its visited cells
	 * @param m the maze
	 * @param s the stack containing cells of the shortest path
	 * @param v the list containing all the visited cells
	 */
	public void reset(Cell[][] m, Stack<Cell> s, ArrayList<Cell> v)
	{
		maze = m;
		shortestPath = s;
		visitedCells = v;
		printMaze = printVisited = printShortest = "";
		if(maze == null)
			size = 0;
		else
			size = maze.length;
	}
	
	/**
	 * method to print the maze
	 * @returns a string containing the maze
	 */
	public String getPrintMaze()
	{
		return printMaze;
	}
	
	/**
	 * method to get the visited cells
	 * @returns a string containing the visited cells
	 */
	public String getprintVisited()
	{
		return printVisited;
	}
	
	/**
	 * method to get the shortest path
	 * @returns a string containing the shortest path
	 */
	public String getprintShortest()
	{
		return printShortest;
	}
	
	/**
	 * method to create the text file Maze.txt to which the mazes are being printed
	 */
	public void createFile()
	{
		try 
		{
			// write to a file using PrintWriter
			fw = new FileWriter("src/project3/Maze.txt");
			pw = new PrintWriter(fw);
		}
		catch (IOException e) 
		{
			System.out.println("File could not be created");
		}
	}
	
	/**
	 * method to print the maze of the specified size
	 */
	public void printMaze()
	{
		if(size == 0)
		{
			printMaze += "Maze not generated: Size 0";
			pw.println("\nMaze not generated: Size 0");
			return;
		}
		pw.println("\n" + size + " X " + size);
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++ )
			{
				if(maze[i][j].getNorth())
				{
					printMaze += "+-";
				}
				else
				{
					printMaze += "+ ";
				}
			}
			printMaze += "+\n";
			for(int j = 0; j < size; j++)
			{
				if(maze[i][j].getWest())
				{
					printMaze += "| ";
				}
				else
				{
					printMaze += "  ";
				}
			}
			printMaze += "|\n";
		}
		for(int i = 0; i < size-1; i++)
		{
			printMaze += "+-";
		}
		printMaze += "+ +";
		pw.println("\n"+printMaze);
		//System.out.println(printMaze);
		
	}
	
	/**
	 * method to print the maze with hashes to represent the 
	 * cells of the shortest path
	 * @param dfs if it is true then prints the maze with the shortest path
	 */
	public void printShortestPath(boolean dfs)
	{
		
		if(size == 0)
		{
			
			pw.println();
			if(dfs)
			{
				pw.println("DFS");
			}
			else
				pw.println("BFS");
			pw.println("\nLength of path: 0");
			pw.println("Visited Cells: 0");
			return;
		}
		pw.println();
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++ )
			{
				if(maze[i][j].getNorth())
				{
					printShortest += "+-";
				}
				else
				{
					printShortest += "+ ";
				}
			}
			printShortest += "+\n";
			for(int j = 0; j < size; j++)
			{
				if(maze[i][j].getWest())
					if(shortestPath.contains(maze[i][j]))
					{
						printShortest += "|#";
					}
					else
					{
						printShortest += "| ";
					}
				else
					if(shortestPath.contains(maze[i][j]))
					{
						printShortest += " #";
					}
					else
					{
						printShortest += "  ";
					}
			}
			printShortest += "|\n";
		}
		for(int i = 0; i < size-1; i++)
		{
			printShortest += "+-";
		}
		printShortest += "+ +";
		pw.println("\n" + printShortest);
		pw.print("Path: ");
		for(int i = 0; i < shortestPath.size(); i++)
		{
			pw.print(shortestPath.get(i).getLocation());
		}
		pw.println("\nLength of path: " + shortestPath.size());
		pw.println("Visited Cells: " + visitedCells.size());
		//System.out.println(printShortest + "\n");
		
	}
	
	/** the method to print the maze with the visited cells marked as numbers
	 * @param dfs if it is true then prints the visited cells
	 */
	public void printVisitedCells(boolean dfs)
	{
		if(size == 0)
			return;
		if(dfs)
		{
			pw.println("\nDFS");
		}
		else
			pw.println("\nBFS");
		pw.println();
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++ )
			{
				if(maze[i][j].getNorth())
					printVisited += "+-";
				else
					printVisited += "+ ";
			}
			printVisited += "+\n";
			for(int j = 0; j < size; j++)
			{
				if(maze[i][j].getWest())
					if(visitedCells.contains(maze[i][j]))
						printVisited += "|" + ((visitedCells.indexOf(maze[i][j]))%10);
					else
						printVisited += "| ";
				else
					if(visitedCells.contains(maze[i][j]))
						printVisited += " " + ((visitedCells.indexOf(maze[i][j]))%10);
					else
						printVisited += "  ";
			}
			printVisited += "|\n";
		}
		for(int i = 0; i < size-1; i++)
		{
			printVisited += "+-";
		}
		printVisited += "+ +";
		pw.println("\n" + printVisited);
	}
	
	/**
	 * method to close the printreader file
	 */
	public void closeFile()
	{
		pw.close();
	}

}
