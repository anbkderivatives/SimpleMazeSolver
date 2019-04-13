package anbkderivatives.SimpleMazeSolver.service;

import org.springframework.stereotype.Service;

import anbkderivatives.SimpleMazeSolver.main.MazeMainPace;
import anbkderivatives.SimpleMazeSolver.entity.ClientMaze;
import anbkderivatives.SimpleMazeSolver.entity.MazeProperties;;

@Service
public class SimpleMazeService {
	
	
	private MazeMainPace mazeMainPace;
	
	public MazeProperties findMazePathSolution(String givenMazeFile) {
		
		mazeMainPace = new MazeMainPace();
		MazeProperties mazeProperties = new MazeProperties();
		mazeMainPace.ordinateMaze(givenMazeFile);
		
		mazeProperties.setMsg(mazeMainPace.getMsg());
		mazeProperties.setSolved(mazeMainPace.isSolved());
		if(mazeMainPace.isSolved()) mazeProperties.setTheSolvedMaze(mazeMainPace.getTheSolvedMaze());
		else mazeProperties.setTheSolvedMaze(null);
		
		//System.out.println("Maze is solved "+ mazeMainPace.isSolved());
		
		return mazeProperties;
	}
	
	public MazeProperties findMazePathSolutionClient(ClientMaze clientMaze) {
		
		mazeMainPace = new MazeMainPace();
		MazeProperties mazeProperties = new MazeProperties();
		mazeMainPace.ordinateMazeClient(clientMaze.getTheMaze(),
										clientMaze.getXs(),
										clientMaze.getYs(),
										clientMaze.getXe(),
										clientMaze.getYe()
										);
		
		mazeProperties.setMsg(mazeMainPace.getMsg());
		mazeProperties.setSolved(mazeMainPace.isSolved());
		if(mazeMainPace.isSolved()) mazeProperties.setTheSolvedMaze(mazeMainPace.getTheSolvedMaze());
		else mazeProperties.setTheSolvedMaze(null);
				
		return mazeProperties;
	}

}
