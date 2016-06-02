import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

/*****************
 * This class reads in a maze (as created by Maze.java)
 * as a graph, then solves it, then prints out its solution
 *
 * To run it:
 * java MazeGraph [-w] mazefile.txt
 *
 * Use -w if you want to take weights into account
 *
 * Andy Exley
 */
public class MazeGraph {

	public static void main(String[] args) {
		String fname = null;
		String startvertex = null;
		String endvertex = null;
		boolean weighted = false;

		if(args.length < 3 || (args[0].equals("-w") && args.length < 4)) {
			System.err.println("Usage:\njava MazeGraph [-w] mazefile.txt start end");
			System.exit(1);
		} else if (args[0].equals("-w")) {
			fname = args[1];
			startvertex = args[2];
			endvertex = args[3];
			weighted = true;
		} else {
			fname = args[0];
			startvertex = args[1];
			endvertex = args[2];
		}
		
		//TODO: REMOVE THIS PART
		BasicGraphADT<String> gmaze = loadMaze(fname);

		/*if(!weighted) {
			BasicGraphADT<String> gmaze = loadMaze(fname);
			List<Vertex<String>> path1 = solveMazeDepthFirst(gmaze, startvertex, endvertex);
			System.out.println("Solution using DFS:");
			for(int i = 0; path1 != null && i < path1.size(); i++) {
				System.out.println(path1.get(i));
			}

			// reload maze in case the graph needs to be reset
			BasicGraphADT<String> gmaze2 = loadMaze(fname);
			List<Vertex<String>> path2 = solveMazeBreadthFirst(gmaze2, startvertex, endvertex);
			System.out.println("Solution using BFS:");
			for(int i = 0; path2 != null && i < path2.size(); i++) {
				System.out.println(path2.get(i).getLabel());
			}
		} else {
			WeightedGraphADT<String> gmaze = loadWeightedMaze(fname);
			List<Vertex<String>> path3 = solveMaze(gmaze, startvertex, endvertex);
			System.out.println("Solution with least weight:");
			for(int i = 0; i < path3.size(); i++) {
				System.out.println(path3.get(i));
			}
		}
		*/
	}

	/*********************
	 * This method loads a maze from a given file with name fname
	 *********************/
	public static BasicGraphADT<String> loadMaze(String fname) {
		BasicGraphADT<String> mymaze = new AdjListGraph<String>(); 
		// change this to initalize your graph from the given file
		Scanner s = null;	//initialize scanner
		System.out.println("Loading Maze..."); //verbose output because this takes longer than the average opperation
		
		//load the scanner in a try/catch loop to avoid FileNotFoundExceptions 
		try { //put in try-catch block to avoid filenotfound exceptions
			s = new Scanner(new File(fname));	//scan the list of words
			
		} 
		catch(FileNotFoundException e) {			//if they don't have a maze
			System.out.println("Unable to find maze file.");	//raise warning and exit
			System.exit(0);
		}
		

		int size =  Integer.parseInt(s.next());	//find the size of the maze (held in the first word of the maze)
		String firstLineRemover = s.nextLine(); //move past the first line
		String[][] mazeArray = new String[size][size];	//create a 2d array to hold the values of the maze
		//copy the maze file into the array
		for (int r = 0; r < size ; r++){
			mazeArray[r] = s.nextLine().split(" ", size);	//convert lines to arrays by splitting at the spaces
		}

		//load vertexes into graph 
		for (int r = 0; r < size ; r++){
			for (int c = 0; c < size ; c++){
				if((mazeArray[r][c]).charAt(2)!= '0'){
					mymaze.addVertex(mazeArray[r][c].substring(0,2)); //don't add the number to the name of the vertex
					//TODO, add weight thing for weighted graph adding
				}
			} 
		}
		//add edges
		for (int r = 0; r < size ; r++){	//loop through all rows
			for (int c = 0; c < size ; c++){	//loop through all columns
				if(mazeArray[r][c].charAt(2)!= '0'){
					//Find edges by looking for all adjecent cells
					for (int yshift = -1; yshift < 2; yshift++){	//find above and below connections
						for (int xshift = -1; xshift < 2; xshift++){	//find left and right connections
							if (xshift != 0 || yshift != 0){ //as long as it is not trying to scan itself
								try{	//to avoide out of bounds errors on edges
									if(mazeArray[r+yshift][c+xshift].charAt(2)!='0'){	//if this is a valid neighbor
										mymaze.addEdge(mazeArray[r][c].substring(0,2), mazeArray[r+yshift][c+xshift].substring(0,2)); //add an edge between these two points
									}
								}
								catch(IndexOutOfBoundsException e){
									//this means that it is looking out of bounds and therefore there isn't a cell there to connect to so do nothing
								}
							}
						}
					}
				}
			} 
		}
		System.out.println(mymaze.toString());
		return mymaze;
	}

	/*********************
	 * This method loads a maze from a given file with name fname as
	 * a weighted graph
	 *********************/
	public static WeightedGraphADT<String> loadWeightedMaze(String fname) {
		WeightedGraphADT<String> mymaze = new WeightedGraph<String>(); // change this to initalize your graph
		
		

		// build your maze based on the given file
		return mymaze;
	}

	/******** 
	 * This method should use a breadth-first traversal to find a path through the 
	 * maze, then return that path.
	 ******/
	public static List<Vertex<String>> solveMazeBreadthFirst(BasicGraphADT<String> maze, String startvert, String endvert) {
		// Use a breadth-first search to find a path through the maze
		Vertex<String> start = maze.getVertex(startvert);
		Vertex<String> end = maze.getVertex(endvertex);
		Vertex<String> current = new Vertex<String>();

		LinkedList<Vertex<String>> queue = new LinkedList<Vertex<String>>();
		LinkedList<Vertex<String>> visitedlist = new LinkedList<Vertex<String>>();
		queue.enqueue(start);
  		
  		while(!queue.isEmpty()){
  			current = queue.dequeue();
    		visitedlist.add(current);

    		if (current.equals(end)){
    			return current.getPath();
    		}
    		else{
    			LinkedList<String> currentNeighbors = current.getNeighbors();
    			Vertex<String> check = currentNeighbors.first();
    			
    			for(int i = 0; i<currentNeighbors.size(); i++){
    				if(!visitedlist.contains(check)&&!queue.contains(check)){
    					check.setPath() = current.getPath().add(current);
    					queue.enqueue(check);
    				}
    				check = check.getNext();
    			} 

    		}
      
  		}

  		return null;

	}

	/******** 
	 * This method should use a depth-first traversal to find a path through the 
	 * maze, then return that path.
	 ******/
	public static List<Vertex<String>> solveMazeDepthFirst(BasicGraphADT<String> maze, String startvert, String endvert) {
		Vertex<String> start = maze.getVertex(startvert);
		Vertex<String> end = maze.getVertex(endvertex);
		Vertex<String> current = new Vertex<String>();

		Stack<Vertex<String>> vertStack = new Stack<Vertex<String>>();
		LinkedList<Vertex<String>> visitedlist = new LinkedList<Vertex<String>>();

		vertStack.push(start);

		while(!vertStack.isEmpty()){
			current = vertStack.pop();
			if(!visitedlist.contains(current)){
				visitedlist.add(current);
				if(current.equals(end)){
					return current.getPath();
				}
				else{
					LinkedList<String> currentNeighbors = current.getNeighbors();
    				Vertex<String> check = currentNeighbors.first();
    			
    				for(int i = 0; i<currentNeighbors.size(); i++){
    					check.setPath() = current.getPath().add(current); //I added this, doesn't appear in pseudocode
    					vertStack.push(check);
    					check = check.getNext();
					}
				}

			}

		}
		return null;
	}

	/******** 
	 * This method should use Dijkstra's algorithm to find the shortest cost path through the 
	 * maze, then return that path.
	 ******/
	public static List<Vertex<String>> solveMaze(WeightedGraphADT<String> maze, String startvert, String endvert) {
		// WE STILL NEED A MIN HEAP
		//create empty priorityque minHeap
		Vertex<String> start = maze.getVertex(startvert);
		Vertex<String> end = maze.getVertex(endvertex);
		Vertex<String> current = new Vertex<String>();

		minHeap.add(start);

		while(!minHeap.isEmpty()){
			current = minHeap.getMin();

			if(current.equals(end)){
				return current.getPath();
			}
			else{
				LinkedList<String> currentNeighbors = current.getNeighbors();
    			Vertex<String> check = currentNeighbors.first();
    			
    			for(int i = 0; i<currentNeighbors.size(); i++){
    				check.setDistance(current.getDistance() + maze.getEdgeWeight(current,check));
    				check.setPath() = current.getPath().add(current);
    				minHeap.add(check);
    				check = check.getNext();
    			} 
			}
		}
		return null;
	}
}
