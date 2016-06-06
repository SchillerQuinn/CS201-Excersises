import java.util.*;

/********************************* 
* Final Project 6/6/16
* Andrew Maris and Quinn Schiller
***********************************/


public class AdjListGraph<T> implements BasicGraphADT<T>{

	HashMap<T, Vertex<T>> vertexes = new HashMap<T, Vertex<T>>(); //store the vertexes as hash maps 
	int edges = 0; //keeps track of the total number of edges
	
	/*******************
	* Give the list of vertexes in the graph
	* @return a collection of vertex values
	********************/
	public ArrayList<Vertex<T>> toList(){
		Collection<Vertex<T>> foo = vertexes.values();//takes the values of the vertexes
		ArrayList<Vertex<T>> bar = new ArrayList<Vertex<T>>();// creates an new list
		bar.addAll(foo); //store the values in the list
		return bar; //returns the list
	}


	/*******************
	* Get the vertex object at the lable
	* @return the vertex object
	********************/
	public Vertex<T> getVertex(T vert){
		return vertexes.get(vert); //fings the specified vertex given a parameter in T
	}

	/*******************
	* Add a vertex to this graph with given label
	* @return Whether the vertex was successfully added
	********************/
	public boolean addVertex(T vert){
		try{
			vertexes.put(vert, new Vertex<T>(vert)); //tries to add the vertex to the map. If it succeeds it returns true
			return true;
		} catch (Exception e){ //if it fails it return false
			return false;
		}
	}


	public String toString(){
		ArrayList<Vertex<T>> vertexList = this.toList(); //stores the result of to list in vertexList
		StringBuilder output = new StringBuilder(); 
		while (!vertexList.isEmpty()){ //while the vertex list isn't empty
			Vertex<T> currentVertex = vertexList.remove(vertexList.size()-1); //remove the last element to save time so it doesn't have to shift anything
			output.append(currentVertex.getLabel());	//write the label of the vertex
			output.append(" --> ");
			output.append(currentVertex.getNeighbors().toString());	//write the neighbors of the vertex
			output.append("\n");	//new line
		}
		return output.toString();
	}
	/******************
	* Add an edge to this graph between the two given labels
	* @return Whether the edge was successfully added
	********************/
	public boolean addEdge(T beg, T end){
		try{
			vertexes.get(beg).addNeighbor(vertexes.get(end)); //adds a directed edge going one direction
			vertexes.get(end).addNeighbor(vertexes.get(beg)); //adds a directed edge going the other direction
			edges++; //increments the number of edges by 1
			return true; 
		} catch (Exception e){ //it it runs into an error, it will return false
			return false;
		}
	}

	/******************
	* Tests whether a vertex exists in the graph
	* @return Whether the vertex exists
	********************/
	public boolean hasVertex(T vert){
		return vertexes.containsKey(vert); //asks the map whether it contains the parameter requested
	}

	/******************
	* Tests whether an edge exists in the graph
	* @return Whether the edge exists
	********************/
	public boolean hasEdge(T beg, T end){
		if (vertexes.get(beg).getNeighbors().contains(end) && vertexes.get(end).getNeighbors().contains(beg)){ //if there is a directed edge going both ways
			return true; //then return true
		}
		else{
			return false; //otherwise return false
		}
	}

	/*****************
	* Tests if the graph is empty
	* @return Whether the graph is empty
	*******************/
	public boolean isEmpty(){
		return vertexes.isEmpty(); //askes the map if it is empty
	}

	/********************
	* Gets the number of vertices
	* @return The number of vertices
	*******************/
	public int getNumVertices(){
		return vertexes.size(); //asks the map for its size
	}

	/********************
	* Gets the number of edges
	* @return The number of edges
	*********************/
	public int getNumEdges(){
		return edges; //returns the value of the variable edges
	}

	/**************
	* Clear all edges and vertices from the graph
	********************/
	public void clear(){
		edges = 0; //resets edges
		vertexes.clear(); //clear the map
	}

	/******************
	* Add an edge to this graph between the two given labels with the given weight
	* @return Whether the edge was successfully added
	********************/
	public double getEdgeWeight(T beg, T end){
		try{
			return vertexes.get(beg).getWeight(vertexes.get(end)); //tries to return the weight between two vertexes
		}
		catch (Exception e){ //if it can't it returns -1
			return -1;
		}
	}
}