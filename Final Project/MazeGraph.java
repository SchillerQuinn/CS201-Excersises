import java.util.*;
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

		if(!weighted) {
			AdjListGraph<String> gmaze = loadMaze(fname);
			List<Vertex<String>> path1 = solveMazeDepthFirst(gmaze, startvertex, endvertex);
			System.out.println("Solution using DFS:");
			for(int i = 0; path1 != null && i < path1.size(); i++) {
				System.out.println(path1.get(i));
			}

			// reload maze in case the graph needs to be reset
			AdjListGraph<String> gmaze2 = loadMaze(fname);
			List<Vertex<String>> path2 = solveMazeBreadthFirst(gmaze2, startvertex, endvertex);
			System.out.println("Solution using BFS:");
			for(int i = 0; path2 != null && i < path2.size(); i++) {
				System.out.println(path2.get(i).getLabel());
			}
		} else {
			WeightedGraph<String> gmaze = loadWeightedMaze(fname);
			List<Vertex<String>> path3 = solveMaze(gmaze, startvertex, endvertex);
			System.out.println("Solution with least weight:");
			for(int i = 0; i < path3.size(); i++) {
				System.out.println(path3.get(i).getLabel());
			}
		}
		
	}

	/*********************
	 * This method loads a maze from a given file with name fname
	 *********************/
	public static AdjListGraph<String> loadMaze(String fname) {
		AdjListGraph<String> mymaze = new AdjListGraph<String>(); 
		Scanner s = null;	//initialize scanner		
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
							if (xshift == 0 ^ yshift == 0){ //scan all directly left right above and below combinations but not its self or diagonals 
								try{	//to avoid out of bounds errors on edges
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
	return mymaze;
	}

	/*********************
	 * This method loads a maze from a given file with name fname as
	 * a weighted graph
	 *********************/
	public static WeightedGraph<String> loadWeightedMaze(String fname) {
		WeightedGraph<String> myMaze = new WeightedGraph<String>();	
		//load the scanner in a try/catch loop to avoid FileNotFoundExceptions
		Scanner s = null;	//initialize scanner		
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
		
		for (int r = 0; r < size ; r++){ //copy the maze file into the array
			mazeArray[r] = s.nextLine().split(" ", size);	//convert lines to arrays by splitting at the spaces
		}
		
		//load vertexes into graph 
		for (int r = 0; r < size ; r++){
			for (int c = 0; c < size ; c++){
				if((mazeArray[r][c]).charAt(2)!= '0'){
					Vertex<String> cell = new Vertex<String>(mazeArray[r][c].substring(0,2),Character.getNumericValue(mazeArray[r][c].charAt(2)));
					myMaze.addVertexObj(cell); //don't add the number to the name of the vertex
				}
			} 
		}

		//add edges
		for (int r = 0; r < size ; r++){	//loop through all rows
			for (int c = 0; c < size ; c++){	//loop through all columns
				if(mazeArray[r][c].charAt(2)!= '0'){
					
					//Find edges by looking for all adjacent cells
					for (int yshift = -1; yshift < 2; yshift++){	//find above and below connections
						for (int xshift = -1; xshift < 2; xshift++){	//find left and right connections
							if (xshift == 0 ^ yshift == 0){ //as long as it is not trying to scan itself
								try{	//to avoid out of bounds errors on edges
									if(mazeArray[r+yshift][c+xshift].charAt(2)!='0'){	//if this is a valid neighbor
										myMaze.addEdge(mazeArray[r][c].substring(0,2), mazeArray[r+yshift][c+xshift].substring(0,2),
											myMaze.getEdgeWeight(mazeArray[r][c].substring(0,2),mazeArray[r+yshift][c+xshift].substring(0,2)));
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
	return myMaze;
	}


	/******** 
	 * This method should use a breadth-first traversal to find a path through the 
	 * maze, then return that path.
	 ******/
	public static List<Vertex<String>> solveMazeBreadthFirst(AdjListGraph<String> maze, String startvert, String endvert) {
		// Use a breadth-first search to find a path through the maze
		Vertex<String> start = maze.getVertex(startvert);
		Vertex<String> end = maze.getVertex(endvert);
		Vertex<String> current = new Vertex<String>(null);

		LinkedList<Vertex<String>> queue = new LinkedList<Vertex<String>>();	//holds the list of vertexes to look at
		LinkedList<Vertex<String>> visitedlist = new LinkedList<Vertex<String>>();	//holds the list of vertexes that have been visited
		LinkedList<Vertex<String>> currentNeighbors = new LinkedList<Vertex<String>>();	//holds the list of neighboring vertexes to the current vertex

		queue.add(start);	//add the starting vertex to the queue
		while(!queue.isEmpty()){
			current = queue.poll();	//get the first vertex from the queue
			if (!visitedlist.contains(current) && !queue.contains(current)){	//don't look at vertexes that have been looked at already
				visitedlist.add(current);	//add the current vertex to the visited list so we don't look at it
				currentNeighbors.addAll(current.getNeighbors());
				LinkedList<Vertex<String>> pathHolder = (LinkedList<Vertex<String>>) current.getPath().clone();	//set it equal to a copy of the current path
				pathHolder.add(current); //add the current vertex to the path
				do{
					Vertex<String> check = currentNeighbors.poll();	//pop the first neighbor vertex to look at 
					if(!visitedlist.contains(check) && !queue.contains(check) && !current.getPath().contains(check)) {

						check.setPath(pathHolder);	//set the path of each valid neighbor
						if (check.equals(end)){
							return check.getPath();	//return the path to the end
						}
						queue.add(check);	//add the valid neighbor to the queue of next vertexes to explore
					}
				} while (!currentNeighbors.isEmpty());	//repeat until there are no more neighbors left
			}
			
		}
	return null;	// if we cannot find the end, then we will return null
	}

	/******** 
	 * This method should use a depth-first traversal to find a path through the 
	 * maze, then return that path.
	 ******/
	
	public static List<Vertex<String>> solveMazeDepthFirst(AdjListGraph<String> maze, String startvert, String endvert) {
		Vertex<String> start = maze.getVertex(startvert);
		Vertex<String> end = maze.getVertex(endvert);
		Vertex<String> current = start;
		ArrayDeque<Vertex<String>> vertStack = new ArrayDeque<Vertex<String>>();
		LinkedList<Vertex<String>> visitedlist = new LinkedList<Vertex<String>>();
		vertStack.push(start); //Adding our first vertex to kick this off
		
		while(!vertStack.isEmpty()){	//repeat until the stack is empty
			current = vertStack.pop();	//set the current vertex to the first vertex in the stack
			if(!visitedlist.contains(current)){	//only go to unvisited vertexes
				visitedlist.add(current);	//add the current vertex to the visited list so we don't revisit it.
				if(current.equals(end)){	//if we found the end
					return current.getPath();	//return the path to the end
				}
				else{	//we aren't at the end yet
					LinkedList<Vertex<String>> currentNeighbors = current.getNeighbors();	//find all current neighbors to the vertex we are at
					LinkedList<Vertex<String>> pathHolder = (LinkedList<Vertex<String>>) current.getPath().clone();	//copy the path (avoid rewriting it)
					pathHolder.add(current); //add the current vertex to the path
					while(!currentNeighbors.isEmpty()){	//repeat until their are no more neighbors left
						Vertex<String> check = currentNeighbors.poll();	//look at the popped neighbor
						check.setPath(pathHolder);	//set the path of each neighbor
						vertStack.push(check);	//add the valid neighbors to the vert stack.
					}
				}
			}

		}
	return null; // if we cannot find the end, then we will return null
	}
	

	/******** 
	 * This method should use Dijkstra's algorithm to find the shortest cost path through the 
	 * maze, then return that path.
	 ******/
	public static List<Vertex<String>> solveMaze(WeightedGraph<String> maze, String startvert, String endvert) {
		//our vertex extends comparable so we don't need to give it a comparator
		PriorityQueue<Vertex<String>> minHeap = new PriorityQueue<Vertex<String>>();	
		Vertex<String> start = maze.getVertex(startvert);
		Vertex<String> end = maze.getVertex(endvert);
		Vertex<String> current = start;
		
		minHeap.add(start);	//add the start vertex to the minHeap to start 
		while(!minHeap.isEmpty()){
			current = minHeap.poll();	//get the smallest distance vertex
			if(current.equals(end)) {	//if we made it to the end
				return current.getPath();	//return the path to the end
			}
			else{
				LinkedList<Vertex<String>> currentNeighbors = current.getNeighbors();	//get a list of all of the neighbors
				LinkedList<Vertex<String>> pathHolder = (LinkedList<Vertex<String>>) current.getPath().clone();	//copy the path without overwriting it
				pathHolder.add(current);	//add the current vertex to the path
				while(!currentNeighbors.isEmpty()){	//repeat through all of the neighbors
					Vertex<String> check = currentNeighbors.poll();	//remove the first neighbor and look at it
					//set the distance from the current vertex to the neighboring vertex
					check.setDistance(current.getDistance() + maze.getEdgeWeight(current.getLabel(), check.getLabel()));	
					check.setPath(pathHolder);	//set the path of the neighbor
					minHeap.add(check);	//add the neighbor to the minheap.
				}
			}
		}
	return null;	//if we didn't find the end, return null
	}
}