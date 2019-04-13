package anbkderivatives.SimpleMazeSolver.solvemaze;
// Node is a reference which include the content and information of the elements (grids, squares) which comprise the maze.
// An eLement contains: 
//						the current coordinates Node that indicate the position in maze
//						the parent node coordinates, whom the current node arise
public class Node {
	
	//---------------------------------------	 Variables	---------------------------------------
	
	// Current coordinates of Node (square) in the maze
	private int x, y;
		
	// Previous coordinates of Parent Node (square) in the maze
	private int px, py;

	//---------------------------------------	 Methods	---------------------------------------
	
	public Node(int x, int y, int px, int py){
		this.x = x;
		this.y = y;
		this.px = px;
		this.py = py;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getPx() {
		return px;
	}

	public void setPx(int px) {
		this.px = px;
	}

	public int getPy() {
		return py;
	}

	public void setPy(int py) {
		this.py = py;
	}

}
