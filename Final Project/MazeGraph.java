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
				System.out.println(path3.get(i));
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
							if (xshift == 0 ^ yshift == 0){ //scan all directly left right above and below combinations but not itsself 
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
	return mymaze;
	}

	/*********************
	 * This method loads a maze from a given file with name fname as
	 * a weighted graph
	 *********************/
	public static WeightedGraph<String> loadWeightedMaze(String fname) {
		WeightedGraph<String> myMaze = new WeightedGraph<String>(); // change this to initialize your graph	
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
		//copy the maze file into the array
		for (int r = 0; r < size ; r++){
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

		LinkedList<Vertex<String>> queue = new LinkedList<Vertex<String>>();
		LinkedList<Vertex<String>> visitedlist = new LinkedList<Vertex<String>>();
		queue.add(start);	//add the starting vertex to the queue
		LinkedList<Vertex<String>> currentNeighbors = new LinkedList<Vertex<String>>();
		while(!queue.isEmpty()){
			System.out.println(queue);
			current = queue.poll();	//get the first vertex from the queue
				//add the current vertex to the list of visited vertexes
			//System.out.println(current);
			System.out.println(visitedlist);
			System.out.println("-----");
			if (current.equals(end)){	//the search is over
				return current.getPath();
			}
			if (!visitedlist.contains(current) && !queue.contains(current)){	//don't look at vertexes that have been looked at already
				visitedlist.add(current);
				currentNeighbors.addAll(current.getNeighbors());
				LinkedList<Vertex<String>> foo = (LinkedList<Vertex<String>>) current.getPath().clone();
				foo.add(current); 
				do{
					Vertex<String> check = currentNeighbors.poll();
					//System.out.println(check);
					//System.out.println("-----");
					//System.out.print(visitedlist.contains(check));
					//System.out.println("\t");
					//System.out.println(queue.contains(check));
					if(!visitedlist.contains(check) && !queue.contains(check) && !current.getPath().contains(check)) {
						//System.out.print(check.getLabel());
						//System.out.print("\t");
						check.setPath(foo);
						if (check.equals(end)){
							return check.getPath();
						}
						//System.out.println(check.getPath());
						queue.add(check);
					}
					//System.out.println("\n");
				} while (!currentNeighbors.isEmpty());
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
		vertStack.push(start);//Adding our first vertex to kick this off
		while(!vertStack.isEmpty()){
			current = vertStack.pop();
			if(!visitedlist.contains(current)){//only go to unvisted vertexes
				visitedlist.add(current);
				if(current.getLabel().equals(end)){
					return current.getPath();
				}
				else{
					LinkedList<Vertex<String>> currentNeighbors = current.getNeighbors();
					LinkedList<Vertex<String>> foo = (LinkedList<Vertex<String>>) current.getPath().clone();
					foo.add(current); 
					while(!currentNeighbors.isEmpty()){
						Vertex<String> check = currentNeighbors.poll();
						check.setPath(foo);
						vertStack.push(check);
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
		PriorityQueue<Vertex<String>> minHeap = new PriorityQueue<Vertex<String>>();
		Vertex<String> start = maze.getVertex(startvert);
		Vertex<String> end = maze.getVertex(endvert);
		Vertex<String> current = start;
		while(!minHeap.isEmpty()){
			current = minHeap.poll();

			if(current.equals(end)) {
				return current.getPath();
			}
			else{
				LinkedList<Vertex<String>> currentNeighbors = current.getNeighbors();
				LinkedList<Vertex<String>> foo = (LinkedList<Vertex<String>>) current.getPath().clone();
				foo.add(current); 
				while(!currentNeighbors.isEmpty()){
					Vertex<String> check = currentNeighbors.poll();
					check.setDistance(current.getDistance() + maze.getEdgeWeight(current.getLabel(), check.getLabel()));
					check.setPath(foo);
					minHeap.add(check);
				} 
			}
		}
	return null;
	}
}