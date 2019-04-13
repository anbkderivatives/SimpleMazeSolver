package anbkderivatives.SimpleMazeSolver.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//This Controller serve pages through Thymeleaf 
@Controller
public class SimpleMazeSolverControllerMVC {
	
	
	@GetMapping("/simpleMazeSolver")
	public String simpleMazeSolver() {
		
		return "SimpleMazeSolver/SimpleMazeSolver.html";
		
	}
}
