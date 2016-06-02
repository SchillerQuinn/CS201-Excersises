import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.PriorityQueue;
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
		System.out.println(mymaze.toString());
		return mymaze;
	}

	/*********************
	 * This method loads a maze from a given file with name fname as
	 * a weighted graph
	 *********************/
	public static WeightedGraphADT<String> loadWeightedMaze(String fname) {
		WeightedGraph<String> myMaze = new WeightedGraph<String>(); // change this to initialize your graph	
		//load the scanner in a try/catch loop to avoid FileNotFoundExceptions
		Scanner s = null;	//initialize scanner
		System.out.println("Loading Maze..."); //verbose output because this takes longer than the average operation 
		
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
					Vertex<String> cell = new Vertex<String>(mazeArray[r][c].substring(0,2),3);
					//mymaze.addVertexDist(mazeArray[r][c].substring(0,2),(int) mazeArray[r][c].charAt(2)); //don't add the number to the name of the vertex
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
		//System.out.println(mymaze.toString());
		return myMaze;
	}

	/******** 
	 * This method should use a breadth-first traversal to find a path through the 
	 * maze, then return that path.
	 ******/
	/******** 
	 * This method should use a breadth-first traversal to find a path through the 
	 * maze, then return that path.
	 ******/
		public static List<Vertex<String>> solveMazeBreadthFirst(BasicGraphADT<String> maze, String startvert, String endvert) {
		
		//Creating vertexes we will work with for solving the maze
		Vertex<String> start = ((AdjListGraph<String>)(maze)).getVertex(startvert); //the beginning vertex
		Vertex<String> end = ((AdjListGraph<String>)(maze)).getVertex(endvert); //the ending vertex
		Vertex<String> current = start; //the current vertex we are analyzing 

		//Creating two lists to store the vertexes we need to get to and the ones we have visited
		LinkedList<Vertex<String>> queue = new LinkedList<Vertex<String>>(); //List of vertexes we have yet to explore
		LinkedList<Vertex<String>> visitedlist = new LinkedList<Vertex<String>>(); //List of vertexes we have gotten to
		
		queue.add(start);//Adding our first vertex to kick this off
  		
  		while(!queue.isEmpty()){ //while we still have things to explore
  			current = queue.remove(); //removes the vertex we are now going to dig into
    		visitedlist.add(current); //add this vertex to the list of things we have visited

    		if (current.equals(end)){ //if we have reached the end, then return the path!
    			return current.getPath();
    		}
    		else{ //otherwise...
    			LinkedList<Vertex<String>> currentNeighbors = (LinkedList<Vertex<String>>)(current.getNeighbors()); //find all the neighbors of current
    			
    			for(int i = 0; i<currentNeighbors.size(); i++){ //cycles through all the neighbors one at a time
    				Vertex<String> check = currentNeighbors.get(i); //takes one neighbor

    				if(!visitedlist.contains(check)&&!queue.contains(check)){ //if this neighbor is not in the visited list and isn't in the to-be-explored list
    					
    					//sets current's path as the current's path + current 
    					LinkedList<Vertex<String>> newPath = (LinkedList<Vertex<String>>) (current.getPath());
    					newPath.add(current);
    					check.setPath(newPath);
    					
    					//adds this neighbor to the end of to-be-explored list
    					queue.add(check);
    				}
    			} 

    		}
      
  		}

  		return null;// if we cannot find the end, then we will return null

	}

	/******** 
	 * This method should use a depth-first traversal to find a path through the 
	 * maze, then return that path.
	 ******/
	public static List<Vertex<String>> solveMazeDepthFirst(BasicGraphADT<String> maze, String startvert, String endvert) {
		
		//Creating vertexes we will work with for solving the maze
		Vertex<String> start = ((AdjListGraph<String>)(maze)).getVertex(startvert);  //the beginning vertex
		Vertex<String> end = ((AdjListGraph<String>)(maze)).getVertex(endvert);  //the ending vertex
		Vertex<String> current = start;

		//Creating two lists to store the vertexes we need to get to and the ones we have visited
		Stack<Vertex<String>> vertStack = new Stack<Vertex<String>>(); //Stack of vertexes we have yet to explore
		LinkedList<Vertex<String>> visitedlist = new LinkedList<Vertex<String>>(); //List of vertexes we have gotten to

		vertStack.push(start);//Adding our first vertex to kick this off

		while(!vertStack.isEmpty()){//while we still have things to explore
			current = vertStack.pop(); //removes the vertex we are now going to dig into

			if(!visitedlist.contains(current)){ //if current isn't already in the visited list, we add it
				visitedlist.add(current); 
				
				if(current.equals(end)){ //if we have reached the end, then return the path!
					return current.getPath();
				}
				else{ //otherwise... 
					LinkedList<Vertex<String>> currentNeighbors = (LinkedList<Vertex<String>>)(current.getNeighbors()); //find all the neighbors of current
    			
    				for(int i = 0; i<currentNeighbors.size(); i++){ //cycles through all the neighbors one at a time
    					Vertex<String> check = currentNeighbors.get(i); //takes one neighbor
    					
    					/**************************************************
    					* I added this, doesn't appear in pseudocode. 
    					* I am pretty sure we need this, but not certain
    					***************************************************/
    					
    					//sets current's path as the current's path + current 
    					LinkedList<Vertex<String>> newPath = (LinkedList<Vertex<String>>) (current.getPath());
    					newPath.add(current);
    					check.setPath(newPath);
    					
    					//adds this neighbor to the top of to-be-explored list
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
	public static List<Vertex<String>> solveMaze(WeightedGraphADT<String> maze, String startvert, String endvert) {
		
		//Creating vertexes we will work with for solving the maze
		Vertex<String> start = ((AdjListGraph<String>)(maze)).getVertex(startvert); //the beginning vertex
		Vertex<String> end = ((AdjListGraph<String>)(maze)).getVertex(endvert);  //the ending vertex
		Vertex<String> current = start; //the current vertex we are analyzing 

		/**************************
		* STILL NEED COMPARATOR --> 
		* do it by distance? 
		****************************/

		//Creating a min heap to organize the weights
		PriorityQueue<Vertex<String>> minHeap = new PriorityQueue<Vertex<String>>(); 

		minHeap.add(start); //Adding our first vertex to kick this off

		while(!minHeap.isEmpty()){ //while we still have things to explore
			current = minHeap.peek(); //sets our current to be the vertex at the top of the heap

			if(current.equals(end)){ //if we have reached the end, then return the path!
				return current.getPath();
			}
			else{ //otherwise...
				LinkedList<Vertex<String>> currentNeighbors = (LinkedList<Vertex<String>>)(current.getNeighbors());//find all the neighbors of current
    			
    			for(int i = 0; i<currentNeighbors.size(); i++){  //cycles through all the neighbors one at a time
    				Vertex<String> check = currentNeighbors.get(i); //takes one neighbor

    				check.setDistance(current.getDistance() + maze.getEdgeWeight(current.getLabel(),check.getLabel())); //sets the distance of check as the the distance of the previous node + distance between it and the previous node
    				
    				//set it's path as the current's path + current 
    				LinkedList<Vertex<String>> newPath = (LinkedList<Vertex<String>>) (current.getPath());
    				newPath.add(current);
    				check.setPath(newPath);
    				
    				//adds check to the heap
    				minHeap.add(check);
    				
    			} 
			}
		}
		return null; // if we cannot find the end, then we will return null
	}
}
