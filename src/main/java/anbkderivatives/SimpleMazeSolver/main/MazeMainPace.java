
//----------------------------------------------------------------------------------------------------------------
//--------------------			Simple Maze Solver				--------------------------------------------------
/*
			The approach used finding the solution path of the maze was accomplished by utilizing 
			the combination of a Greedy Algorithm and a selective traversal. The Greedy Algorithm 
			was implemented by attributing heuristic values to the Squares/Node of the maze. 
			The heuristic technique uses Manhattan Distance from the current node until the end of 
			the searched path, wherein a list with examining nodes decide where next heading 
			should go accordingly to the heuristic.   
			Thus, is succeeded a more efficient and faster way avoiding all BFS (brute force) 
			traversal.

Reference of inspiration and memory refreshment :
https://www.youtube.com/watch?v=u4WtUrliKis&t
https://www.youtube.com/watch?v=KiCBXu4P-2Y
*/
//----------------------------------------------------------------------------------------------------------------
package anbkderivatives.SimpleMazeSolver.main;

import anbkderivatives.SimpleMazeSolver.solvemaze.*;


public class MazeMainPace {
	
	private SolveTheMaze findPath;
	private boolean solved;
	private String msg;
	
	public MazeMainPace(){
		solved = false;
		msg = "";
	}
	
	//coordinate solution form server's corpus files
	public String ordinateMaze(String givenMazeFile){

		CreateMaze readFile;
		
	//------------	 Helper variables --------------------
		
		int [] coordinatesSE; // current examined coordinates of start end point
		
	//---------------------			Procedure of Creating, Initializing and Solving the Maze		---------------------	
		
			readFile = new CreateMaze();
			readFile.initializeMaze(givenMazeFile);
			
		    if(readFile.initializer)
			{
				coordinatesSE = readFile.getCordinatesSE();
				// Pass to the solver the following parameters: the maze, and the coordinates of start end point
				findPath = new SolveTheMaze( readFile.getTheMaze(),
											 coordinatesSE[0], // or readFile.getCordinatesSE()[0]
											 coordinatesSE[1],
											 coordinatesSE[2],
											 coordinatesSE[3]
											);
				findSolutionPath(findPath);
			}
	//-------------------------------------------------------------------------------------------------------------------
		    return msg;
	}
	
	//coordinate solution form Client input files
	public String ordinateMazeClient(char[][] theMaze, int xs, int ys, int xe,int ye) 
	{
		findPath = new SolveTheMaze( theMaze,
									 xs, 
									 ys,
									 xe,
									 ye);
		findSolutionPath(findPath);
		return msg;
	}
	
	
	public void findSolutionPath(SolveTheMaze findPath) {
		
		// if there is a solution
		if(findPath.solvePathMaze() == 1) {
			//System.out.println("\nMoves of squares visiting in the maze: " + (findPath.moves-1)  +".\n" );
			findPath.printMazeSolution();
			solved=true;
			msg = "\nMoves of square visiting in the maze: " + (findPath.moves-1)  +"\n";
		}
		else {
			//System.out.println("\nNo solution found for the current file.\nPlease try with another data file.\nThank You!");
			solved=false;
			msg = "\nNo solution found for the current file.\nPlease try with another data file.\nThank You!";				
		}
		
		// Clear or close things
		findPath.closeDepending();
		
	}
	
	
	
	public boolean isSolved() {
		return solved;
	}

	public String getMsg() {
		return msg;
	}

	public char[][] getTheSolvedMaze(){
		if(solved)
			return findPath.getTheMaze();
		else return null;
	}
}
