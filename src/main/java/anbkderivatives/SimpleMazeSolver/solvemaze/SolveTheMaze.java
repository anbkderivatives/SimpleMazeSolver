package anbkderivatives.SimpleMazeSolver.solvemaze;

import java.util.ArrayList;
import java.util.LinkedList;

//Main class of coordinating the solution of the maze 
public class SolveTheMaze {
	
	//Maze, Coordinates of Start/End point
	private char [][] theMaze;
	private int xs, ys, xe, ye;
	private int NrRows,NrColumns;
	
	// Linked List chosen for visiting next nodes because mostly we have to do with add and remove operations.
	LinkedList<Node> toVisit;
	
	// Visited Array which contain the nodes
	// Array List chosen for visited nodes because mostly we have to do with get operations.
	ArrayList<Node> visitedNode;
	
	// Vectors where include the addition combined number for directing the expansion North, South, West, East.
	private int[] directionVectorX = { -1, +1,  0,  0 };
	private int[] directionVectorY = {  0,  0, -1, +1 };
	
	public int moves; // moves we need to visit all necessarily nodes till finding the solution path

	
	// Transfer the maze to this class to find a solution
	public SolveTheMaze(char[][] theMaze, int xs, int ys, int xe,int ye){
		
		this.theMaze = theMaze;
		this.xs = xs; 
		this.ys = ys;
		this.xe = xe; 
		this.ye = ye;
		
		toVisit = new LinkedList<Node>();
		visitedNode = new ArrayList<Node>();
		NrRows = theMaze.length;
		NrColumns = theMaze[0].length;
		moves = 0;
	}
	
	public int solvePathMaze(){
				
		//------------	 Helper variables ------------------------------------------------------------
		
		Node tempNode; //current node we examine
		int x,y; //current coordinate we examine
		int heuristicValue; // Manhattan Distance for every Node
		int tempHeurValue;  // helps to find least heuristic value in toVisit list
		int positionBestHeuValue; // Node position in toVisit List with the least/best heuristic value
		int l; // current element in toVisit list
		//--------------------------------------------------------------------------------------------
		
		// First Node we should visit which is the Starting point. -1 declare that has no parent
		tempNode = new Node(xs,ys,-1,-1);
		toVisit.add(tempNode);
		
		//--------------------------------------------------------------------------------------------
		// The exploration of the Maze ends if we have crossed all the squares
		while (toVisit.size() > 0) {
			
			//initialize with null
			heuristicValue = -1; 
			tempHeurValue = -1; 
			positionBestHeuValue = -1;
			
			// Find in list toVisit, which node has the most minimum heuristic value
			for(l = 0; l < toVisit.size(); l++) {
				
				x = toVisit.get(l).getX();
				y = toVisit.get(l).getY();
				tempHeurValue = distanceManhattan(x,y);
				
				if( heuristicValue < 0 || tempHeurValue < heuristicValue) {
					heuristicValue = tempHeurValue;
					positionBestHeuValue = l;
				}
			}
			
			// Get coordinates of the square/Node with the beast heuristic value
			x = toVisit.get(positionBestHeuValue).getX();
			y = toVisit.get(positionBestHeuValue).getY();
						
			// Add to visited list the Node with the best current Heuristic value
			visitedNode.add(toVisit.get(positionBestHeuValue));
			theMaze[x][y] = 'v'; //for visited
			moves++;
						
			// If we reached the End point
			if( x == xe && y == ye ) { //or theMaze[x][y] == 'E'
				return 1; // success
			}
			
			// remove the visited Node with best heuristic value form toVisit list since is already examined
			toVisit.remove(positionBestHeuValue);
			
			expandNode(x,y);
			
			//System.out.println("Size LinkedList: "+toVisit.size());
		}
		//--------------------------------------------------------------------------------------------
		
		return -1;//no solution success
	}
	
	// Searching next Nodes (neighbours) pursuing the End point
	private int new_x,new_y;
	public void expandNode(int x, int y) {
		
		// Move North, South, West, East
		for(int d=0; d < 4; d++) {
			new_x = x + directionVectorX[d];
			new_y = y + directionVectorY[d];
			
			// check wrapping if it is out of bounds
			if(new_x == -1) {
				new_x = NrRows - 1; //opposite site
			}
			if(new_x == NrRows) {
				new_x = 0; 
			}
			if(new_y == -1) {
				new_y = NrColumns - 1; //opposite site
			}
			if(new_y == NrColumns ) {
				new_y = 0; 
			}
			
			//Skip visited location or walls
			if(theMaze[new_x][new_y] == 'v') continue;
			if(theMaze[new_x][new_y] == '1') continue;
			if(theMaze[new_x][new_y] == 'S') continue;
						
			// add the new child Node to the List toVisit, informing who are the parent
			toVisit.add(new Node(new_x,new_y,x,y));
			
			// The expansion and the valid moved checked had to be marked as visited/checked for not having endless loop
			theMaze[new_x][new_y] = 'v';
		}
		
	}
	
	// Heuristic Manhattan Distance : |Xcurrent - Xend| + |Ycurrent - Yend|
	public int distanceManhattan(int x, int y) {
		return Math.abs(x - xe) + Math.abs(y - ye);
	}
	
	//print in the maze all changes
	public void printMazeSolution() {
		
		int r, c,l;
		// Current parents examining
		int parentX;
		int parentY;
		
		//--------------------------------------------------------------------------------------------------------------------------
		// Backward Solution printing Path (with 'X' characters) from visitedNode List looking the parents till the starting point
		
		// Get the parent of the ending Node 
		parentX = visitedNode.get(visitedNode.size() - 1).getPx(); 
		parentY = visitedNode.get(visitedNode.size() - 1).getPy();
		
		// compare the parents with each visited node
		l = visitedNode.size() -1;
		while(l >= 0){
			
			for(int k=l ;k >=0; k--) {
				
				if( visitedNode.get(k).getX() == parentX && 
					visitedNode.get(k).getY() == parentY) 
				{
					theMaze[parentX][parentY] = 'X';
					l = k;
					break;
				}
			}
			
			parentX = visitedNode.get(l).getPx(); 
			parentY = visitedNode.get(l).getPy();
			
			// Reached the start point where has no parent (=null or -1)
			if(parentX == -1 && parentY == -1 ) break;
		}
		// Backward Solution printing Path
		//--------------------------------------------------------------------------------------------------------------------------
		
		
		// Print the rest Maze
		// Note: the printed Maze is completed as it requested, first we start with columns and after with rows
		theMaze[xs][ys] = 'S';
		theMaze[xe][ye] = 'E';
		
		for (r = 0; r < NrRows; r++) {
			for (c = 0; c < NrColumns; c++) {
				if(theMaze[r][c] == '1') theMaze[r][c] = '#';
				if(theMaze[r][c] == 'v') theMaze[r][c] = ' ';
				if(theMaze[r][c] == '0') theMaze[r][c] = ' ';
				//System.out.print(theMaze[r][c]);
			}
			//System.out.println();
		}
	}

	public void closeDepending() {
		toVisit.clear();
		visitedNode.clear();
	} 
	
	public char[][] getTheMaze() {
		return this.theMaze;
	}
}
