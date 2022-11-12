package project3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
//the class to test whether the mazes are printing correctly
class PrintMazesTest {

	//object to generate the maze
	GenerateMaze gm;
	//object to print the maze
	static PrintMazes pm;
	
	//sets up the print maze object and creates a new file
	@BeforeAll
	public static void setUpBeforeClass(){
		pm = new PrintMazes();
		pm.createFile();
	}

	//closes the file after the testing is over
	@AfterAll
	public static void tearDownAfterClass(){
		pm.closeFile();
	}

	//checks whether the inital maze has all its walls intact
	@Test
	public void testCreateInitialMaze()
	{
		gm = new GenerateMaze(5);
		Cell[][] maze = gm.getMaze();
		for(int i = 0; i < maze.length; i++)
		{
			for(int j = 0; j < maze.length; j++)
			{
				if(i==0 && i==j)
					assertEquals(false, maze[i][j].allWallsIntact());
				else
					assertEquals(true, maze[i][j].allWallsIntact());
			}
		}
	}
	
	//checks whether the perfect maze has been generated correctly
	//should not have all its walls
	@Test
	public void testGenerateMaze()
	{
		gm = new GenerateMaze(5);
		gm.generate();
		Cell[][] maze = gm.getMaze();
		for(int i = 0; i < maze.length; i++)
		{
			for(int j = 0; j < maze.length; j++)
			{
				assertEquals(false, maze[i][j].allWallsIntact());
			}
		}
	}
	
	//test case for checking whether the dfs algorithm is working
	@Test
	public void testDFS()
	{
		gm = new GenerateMaze(5);
		gm.generate();
		
		DFS dfs = new DFS(gm.getMaze());
		dfs.dfs();
		
		pm.reset(gm.getMaze(), dfs.getShortestPath(), dfs.getVisitedCellsPath());
		pm.printMaze();
		pm.printVisitedCells(true);
		pm.printShortestPath(true);
		String expectedDFS = "+ +-+-+-+-+\n"+
							"|0 1 2|7 6|\n"+
							"+-+-+ +-+ +\n"+
							"|5 6|3 4 5|\n"+
							"+ +-+-+-+ +\n"+
							"|4|1 0 9 8|\n"+
							"+ + +-+-+-+\n"+
							"|3 2|0 1 2|\n"+
							"+ +-+ +-+ +\n"+
							"|7 8 9|  3|\n"+
							"+-+-+-+-+ +";
		assertEquals(expectedDFS, pm.getprintVisited());
	}
	
	//test case for bfs algorithm
	@Test
	public void testBFS()
	{
		gm = new GenerateMaze(5);
		gm.generate();
		
		BFS bfs = new BFS(gm.getMaze());
		bfs.bfs();
		
		pm.reset(gm.getMaze(), bfs.getShortestPath(), bfs.getVisitedCellsPath());
		pm.printMaze();
		pm.printVisitedCells(false);
		pm.printShortestPath(false);
		String expectedBFS = "+ +-+-+-+-+\n"+
							"|0 1 2|8 6|\n"+
							"+-+-+ +-+ +\n"+
							"|6 8|3 4 5|\n"+
							"+ +-+-+-+ +\n"+
							"|4|1 0 9 7|\n"+
							"+ + +-+-+-+\n"+
							"|3 2|0 1 2|\n"+
							"+ +-+ +-+ +\n"+
							"|5 7 9|  3|\n"+
							"+-+-+-+-+ +";
		assertEquals(expectedBFS, pm.getprintVisited());
	}
	
	//test case for a size 0 maze
	@Test
	public void testSizeZero() {
		
		gm = new GenerateMaze(0);
		gm.generate();
		
		DFS dfs = new DFS(gm.getMaze());
		dfs.dfs();
		
		pm.reset(gm.getMaze(), dfs.getShortestPath(), dfs.getVisitedCellsPath());
		pm.printMaze();
		pm.printVisitedCells(true);
		pm.printShortestPath(true);
		
		gm = new GenerateMaze(0);
		gm.generate();
		
		BFS bfs = new BFS(gm.getMaze());
		bfs.bfs();
		
		pm.reset(gm.getMaze(), bfs.getShortestPath(), bfs.getVisitedCellsPath());
		pm.printVisitedCells(false);
		pm.printShortestPath(false);
	}
	
	//test case for a size 1 maze
	@Test
	public void testSizeOne() {
		gm = new GenerateMaze(1);
		gm.generate();
		
		DFS dfs = new DFS(gm.getMaze());
		dfs.dfs();
		assertEquals(dfs.getShortestPath().size(), 1);
		pm.reset(gm.getMaze(), dfs.getShortestPath(), dfs.getVisitedCellsPath());
		pm.printMaze();
		pm.printVisitedCells(true);
		pm.printShortestPath(true);
		
		String dfsSolution = pm.getprintShortest();
		
		gm = new GenerateMaze(1);
		gm.generate();
		
		BFS bfs = new BFS(gm.getMaze());
		bfs.bfs();
		
		pm.reset(gm.getMaze(), bfs.getShortestPath(), bfs.getVisitedCellsPath());
		pm.printVisitedCells(false);
		pm.printShortestPath(false);
		
		String bfsSolution = pm.getprintShortest();
		assertEquals(dfsSolution, bfsSolution);
		
	}
	
	//test case for a size 2 maze
	@Test
	public void testSizeTwo() {
		gm = new GenerateMaze(2);
		gm.generate();
		
		DFS dfs = new DFS(gm.getMaze());
		dfs.dfs();
		
		pm.reset(gm.getMaze(), dfs.getShortestPath(), dfs.getVisitedCellsPath());
		pm.printMaze();
		pm.printVisitedCells(true);
		pm.printShortestPath(true);
		
		String dfsSolution = pm.getprintShortest();
		
		gm = new GenerateMaze(2);
		gm.generate();
		
		BFS bfs = new BFS(gm.getMaze());
		bfs.bfs();
		
		pm.reset(gm.getMaze(), bfs.getShortestPath(), bfs.getVisitedCellsPath());
		pm.printVisitedCells(false);
		pm.printShortestPath(false);
		
		String bfsSolution = pm.getprintShortest();
		assertEquals(dfsSolution, bfsSolution);
		
	}
	
	//test case for a size 4 maze
	@Test
	public void testSizeFour() {
		gm = new GenerateMaze(4);
		gm.generate();
		
		DFS dfs = new DFS(gm.getMaze());
		dfs.dfs();
		
		pm.reset(gm.getMaze(), dfs.getShortestPath(), dfs.getVisitedCellsPath());
		pm.printMaze();
		pm.printVisitedCells(true);
		pm.printShortestPath(true);
		
		String dfsSolution = pm.getprintShortest();
		
		gm = new GenerateMaze(4);
		gm.generate();
		
		BFS bfs = new BFS(gm.getMaze());
		bfs.bfs();
		
		pm.reset(gm.getMaze(), bfs.getShortestPath(), bfs.getVisitedCellsPath());
		pm.printVisitedCells(false);
		pm.printShortestPath(false);
		
		String bfsSolution = pm.getprintShortest();
		assertEquals(dfsSolution, bfsSolution);
	}
	
	//test case for a size 6 maze
	@Test
	public void testSizeSix() {
		gm = new GenerateMaze(6);
		gm.generate();
		
		DFS dfs = new DFS(gm.getMaze());
		dfs.dfs();
		
		pm.reset(gm.getMaze(), dfs.getShortestPath(), dfs.getVisitedCellsPath());
		pm.printMaze();
		pm.printVisitedCells(true);
		pm.printShortestPath(true);
		
		String dfsSolution = pm.getprintShortest();
		
		gm = new GenerateMaze(6);
		gm.generate();
		
		BFS bfs = new BFS(gm.getMaze());
		bfs.bfs();
		
		pm.reset(gm.getMaze(), bfs.getShortestPath(), bfs.getVisitedCellsPath());
		pm.printVisitedCells(false);
		pm.printShortestPath(false);
		
		String bfsSolution = pm.getprintShortest();
		assertEquals(dfsSolution, bfsSolution);
	}
	
	//test case for a size 8 maze
	@Test
	public void testSizeEight() {
		gm = new GenerateMaze(8);
		gm.generate();
		
		DFS dfs = new DFS(gm.getMaze());
		dfs.dfs();
		
		pm.reset(gm.getMaze(), dfs.getShortestPath(), dfs.getVisitedCellsPath());
		pm.printMaze();
		pm.printVisitedCells(true);
		pm.printShortestPath(true);
		
		String dfsSolution = pm.getprintShortest();
		
		gm = new GenerateMaze(8);
		gm.generate();
		
		BFS bfs = new BFS(gm.getMaze());
		bfs.bfs();
		
		pm.reset(gm.getMaze(), bfs.getShortestPath(), bfs.getVisitedCellsPath());
		pm.printVisitedCells(false);
		pm.printShortestPath(false);
		
		String bfsSolution = pm.getprintShortest();
		assertEquals(dfsSolution, bfsSolution);
	}
	
	@Test
	public void testSizeTen() {
		gm = new GenerateMaze(10);
		gm.generate();
		
		DFS dfs = new DFS(gm.getMaze());
		dfs.dfs();
		
		pm.reset(gm.getMaze(), dfs.getShortestPath(), dfs.getVisitedCellsPath());
		pm.printMaze();
		pm.printVisitedCells(true);
		pm.printShortestPath(true);
		
		String dfsSolution = pm.getprintShortest();
		
		gm = new GenerateMaze(10);
		gm.generate();
		
		BFS bfs = new BFS(gm.getMaze());
		bfs.bfs();
		
		pm.reset(gm.getMaze(), bfs.getShortestPath(), bfs.getVisitedCellsPath());
		pm.printVisitedCells(false);
		pm.printShortestPath(false);
		
		String bfsSolution = pm.getprintShortest();
		assertEquals(dfsSolution, bfsSolution);
	}
	
	@Test
	public void testSize20() {
		gm = new GenerateMaze(20);
		gm.generate();
		
		DFS dfs = new DFS(gm.getMaze());
		dfs.dfs();
		
		pm.reset(gm.getMaze(), dfs.getShortestPath(), dfs.getVisitedCellsPath());
		pm.printMaze();
		pm.printVisitedCells(true);
		pm.printShortestPath(true);
		
		String dfsSolution = pm.getprintShortest();
		
		gm = new GenerateMaze(20);
		gm.generate();
		
		BFS bfs = new BFS(gm.getMaze());
		bfs.bfs();
		
		pm.reset(gm.getMaze(), bfs.getShortestPath(), bfs.getVisitedCellsPath());
		pm.printVisitedCells(false);
		pm.printShortestPath(false);
		
		String bfsSolution = pm.getprintShortest();
		assertEquals(dfsSolution, bfsSolution);
	}

}
