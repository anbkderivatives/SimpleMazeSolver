package anbkderivatives.SimpleMazeSolver.rest;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import anbkderivatives.SimpleMazeSolver.entity.ClientMaze;
import anbkderivatives.SimpleMazeSolver.entity.MazeProperties;
import anbkderivatives.SimpleMazeSolver.entity.MazeTextFile;
import anbkderivatives.SimpleMazeSolver.service.SimpleMazeService;

//This Controller serve REST API
@RestController
public class SimpleMazeSolverRestController {
	
	@Autowired
	private SimpleMazeService simpleMazeService;
	
	//request solving mazes from the server's corpus
	@PostMapping("/solveMaze")
	public MazeProperties solveMaze(@RequestBody MazeTextFile mazeTextFile){
		
		MazeProperties mazeProperties = new MazeProperties();
		
		mazeProperties = simpleMazeService.findMazePathSolution(mazeTextFile.getNameMazeTextFile());
		
//		System.out.println("\nMaze is solved \n" );
//		mazeProperties.printMaze();
		
		return mazeProperties;
	}
	
	//request solving mazes from Client's inputs
	@PostMapping("/solveMazeClient")
	public MazeProperties solveMazeClient(@RequestBody ClientMaze clientMaze){
		
		MazeProperties mazeProperties = new MazeProperties();
		
		mazeProperties = simpleMazeService.findMazePathSolutionClient(clientMaze);
		
		return mazeProperties;
	}
	
}










