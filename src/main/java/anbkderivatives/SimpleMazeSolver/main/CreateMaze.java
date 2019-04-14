package anbkderivatives.SimpleMazeSolver.main;

//import java.io.FileReader;
import java.net.InetAddress;
import java.net.URL;

import java.io.BufferedReader;
//import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;

//Create Maze form reading file
public class CreateMaze {
	//---------------------------------------	 Variables	---------------------------------------
	
	
	//get current ip address of the hosting domain
	String ip;

	// The 2d representative Maze with the following symbols
	// 0 - traversable passage way
	// 1 or # - denote walls
	// X - path solution found
	// S,E - start, end position
	// v - visited squares
	private char[][] theMaze;
	
	// Coordinates of Start, End in the maze
	private int xs, ys, xe, ye;

	public boolean initializer = false;

	
	//---------------------------------------	 Methods	---------------------------------------
	public CreateMaze() {
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
			//System.out.println (InetAddress.getLocalHost()); //output: LAPTOP-8KMC37DN/192.168.1.215
		}catch(IOException ex) {
			System.out.println("Error reading ip ");
			ex.printStackTrace();
		}
	}

	public void initializeMaze(String givenMazeFile) {

		// Current src folder path, example
		// (C:/Users/A/eclipse-workspace/MazeTestGentrack)
//		String path = new File("").getAbsolutePath();
//		System.out.println(path);
//		path = path.replace('\\', '/');

		// Reference to the lines of file
		String line = null;
		// Counter indicating after the third line to start maze initializing
		int fillMaze = 0;

		// Max rows and columns of maze
		int height = 0, width = 0;
		
		// Coordinates of start end in the maze
		xs = ys = xe = ye = -1;

		//------------	 Helper variables --------------------
		
		int c = 0; // current column crossing
		int r = 0; // current row crossing
		int l = 0; // current letter crossing from each line of the file

		try {
						
			//get port number 
			String portNr="5000";
			String pathDomainandPort = ip+":"+portNr;
			String pathFile = "http://"+pathDomainandPort + "/FileInputs/" + givenMazeFile;
			//System.out.println(pathFile); //output example: http://192.168.1.215:5000/FileInputs/medium_input.txt
						
			// Create url object from the url string
			URL url = new URL(pathFile);
			
			// Open url connection to get the content html/txt or whatever, in our case is gotten txt file
			InputStreamReader fileReader = new InputStreamReader(url.openStream(), "UTF-8");
					
			// Wrap fileReader/txt file in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// initialization of the maze
			while ((line = bufferedReader.readLine()) != null) {
				
				c = 0;
				
				if (fillMaze == 0) {
					//separate the two number between space of the first three rows of input file
					String[] parts = line.split(" ");
					width = Integer.parseInt(parts[0]);
					height  = Integer.parseInt(parts[1]);
					theMaze = new char[height][width];
					
					//height = Character.getNumericValue(line.charAt(0));
					//width = Character.getNumericValue(line.charAt(2));
					
				} else if (fillMaze == 1) {
					String[] parts = line.split(" ");
					ys = Integer.parseInt(parts[0]); //indicate in which width position
					xs = Integer.parseInt(parts[1]); ////indicate in which height position
					
				} else if (fillMaze == 2) {
					String[] parts = line.split(" ");
					ye = Integer.parseInt(parts[0]);
					xe = Integer.parseInt(parts[1]);
				}
				 else if (r < height && c < width) { // fillMaze == 3
					
					for (l = 0; l < line.length(); l += 2) // fill each row of the maze
					{
						
						theMaze[r][c] = line.charAt(l);
						c++;
					}
					r++;
				}

				fillMaze++;
				
			}
			
			theMaze[xs][ys] = 'S';
			theMaze[xe][ye] = 'E';

			// Close file
			bufferedReader.close();
			
			initializer = true;
			
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + givenMazeFile + "'");
			initializer = false; 
		} catch (IOException ex) {
			System.out.println("Error reading file '" + givenMazeFile + "'");
			ex.printStackTrace();
			initializer = false;
		}
	}

	public void printMaze() {
		
		if(initializer == true) {
			int r, c;
			for (r = 0; r < theMaze.length; r++) {
				for (c = 0; c < theMaze[r].length; c++) {
					System.out.print(theMaze[r][c]);
				}
				System.out.println();
			}
		}
	}

	public char[][] getTheMaze() {
		return this.theMaze;
	}
	
	public int[] getCordinatesSE() {
		//int[] coordinates = new int[]{xs, ys, xe, ye};
		
		return new int[]{xs, ys, xe, ye};
	}
}
