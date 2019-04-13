package anbkderivatives.SimpleMazeSolver.entity;

public class MazeProperties {
	
	private String msg;
	private boolean solved;
	private char[][] theSolvedMaze;
	
	
	public MazeProperties() {
		this.msg = "";
		this.solved = false;
		this.theSolvedMaze = null;
	}
	
	public MazeProperties(String msg, boolean solved, char[][] theSolvedMaze) {
		this.msg = msg;
		this.solved = solved;
		this.theSolvedMaze = theSolvedMaze;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	public char[][] getTheSolvedMaze() {
		return theSolvedMaze;
	}

	public void setTheSolvedMaze(char[][] theSolvedMaze) {
		this.theSolvedMaze = theSolvedMaze;
	}

	public void printMaze() {
		int NrRows = theSolvedMaze.length;
		int NrColumns = theSolvedMaze[0].length;
		for (int r = 0; r < NrRows; r++) {
			for (int c = 0; c < NrColumns; c++) {
				System.out.print(theSolvedMaze[r][c]);
			}
			System.out.println();
		}
	}
	

}
