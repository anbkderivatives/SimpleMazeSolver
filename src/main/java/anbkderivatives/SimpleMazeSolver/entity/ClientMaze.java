package anbkderivatives.SimpleMazeSolver.entity;

public class ClientMaze {
	
	//the Maze and its start/end points
	private int xs;
	private int ys;
	private int xe;
	private int ye;
	private char[][] theMaze;
	
	
	public int getXs() {
		return xs;
	}
	public void setXs(int xs) {
		this.xs = xs;
	}
	public int getYs() {
		return ys;
	}
	public void setYs(int ys) {
		this.ys = ys;
	}
	public int getXe() {
		return xe;
	}
	public void setXe(int xe) {
		this.xe = xe;
	}
	public int getYe() {
		return ye;
	}
	public void setYe(int ye) {
		this.ye = ye;
	}
	public char[][] getTheMaze() {
		return theMaze;
	}
	public void setTheMaze(char[][] theMaze) {
		this.theMaze = theMaze;
	}
	
	public void printMaze() {
			
		int r, c;
		for (r = 0; r < theMaze.length; r++) {
			for (c = 0; c < theMaze[r].length; c++) {
				System.out.print(theMaze[r][c]);
			}
			System.out.println();
		}
			
	}
	
	
}
