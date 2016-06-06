import java.util.*;

/********************************* 
* Final Project 6/6/16
* Andrew Maris and Quinn Schiller
***********************************/

public class WeightedGraph<T> implements WeightedGraphADT<T>{


	HashMap<T, Vertex<T>> vertexes = new HashMap<T, Vertex<T>>(); //stores vertexes in a hashmap
	int edges = 0; // keeps track of the # of edges

	
	public ArrayList<Vertex<T>> toList(){
		Collection<Vertex<T>> foo = vertexes.values(); //collected all the values of the hashmap vertexes
		ArrayList<Vertex<T>> bar = new ArrayList<Vertex<T>>(); //creates a list
		bar.addAll(foo); //stores all the vertexes in the list
		return bar; //returns the list
	}


	/*******************
	* Get the vertex object at the lable
	* @return the vertex object
	********************/
	public Vertex<T> getVertex(T vert){
		return vertexes.get(vert); //finds the vertex with name vert
	}


	/*******************
	* Add a vertex to this graph with given label
	* @return Whether the vertex was successfully added
	********************/
	public boolean addVertex(T vert){
		try{
			vertexes.put(vert, new Vertex<T>(vert)); //tries to add a new vertex with the label vert
			return true; //if it succeeds it will return true
		} catch (Exception e){ //if it hits an exception it will return false
			return false;
		}
	}

	//putting in vertex items
	public boolean addVertexObj(Vertex<T> vert){
		try{
			vertexes.put(vert.getLabel(), vert); //tries to add the vertex
			return true; //if it succeeds it will return true
		} catch (Exception e){ //if it hits an exception it will return false
			return false;
		}
	}


	/******************
	* Add an edge to this graph between the two given labels
	* @return Whether the edge was successfully added
	********************/
	public boolean addEdge(T beg, T end){
		try{
			vertexes.get(beg).addNeighbor(vertexes.get(end)); //adds and edge going both ways
			vertexes.get(end).addNeighbor(vertexes.get(beg));
			edges++; //increments number of edges
			return true; //if it succeeds it will return true
		} catch (Exception e){ //if it hits an exception it will return false
			return false;
		}
	}

	/******************
	* Add an edge to this graph between the two given labels with the given weight
	* @return Whether the edge was successfully added
	********************/
	public boolean addEdge(T beg, T end, double weight){
		try{
			vertexes.get(beg).addNeighbor(vertexes.get(end),weight); //adds and edge going both ways with weight
			vertexes.get(end).addNeighbor(vertexes.get(beg),weight);
			edges++; //increments number of edges
			return true; //if it succeeds it will return true
		} catch (Exception e){ //if it hits an exception it will return false
			return false;
		}
	}

	/******************
	* Tests whether a vertex exists in the graph
	* @return Whether the vertex exists
	********************/
	public boolean hasVertex(T vert){
		return vertexes.containsKey(vert); //asks if vertexes contains vert
	}

	/******************
	* Tests whether an edge exists in the graph
	* @return Whether the edge exists
	********************/
	public boolean hasEdge(T beg, T end){
		if (vertexes.get(beg).getNeighbors().contains(end) && vertexes.get(end).getNeighbors().contains(beg)){ //checks to see if there is an edge going both ways
			return true; //if it succeeds it will return true
		}
		else{
			return false; //if it fails it will return false
		}
	}

	/*****************
	* Tests if the graph is empty
	* @return Whether the graph is empty
	*******************/
	public boolean isEmpty(){
		return vertexes.isEmpty(); //asks if vertexes is empty
	}

	/********************
	* Gets the number of vertices
	* @return The number of vertices
	*******************/
	public int getNumVertices(){
		return vertexes.size(); //asks for size of vertexes
	}

	/********************
	* Gets the number of edges
	* @return The number of edges
	*********************/
	public int getNumEdges(){
		return edges; //returns the variable edges
	}

	/**************
	* Clear all edges and vertices from the graph
	********************/
	public void clear(){
		edges = 0; //reset the edge counter
		vertexes.clear(); // clears the map
	}

	/******************
	* Add an edge to this graph between the two given labels with the given weight
	* @return Whether the edge was successfully added
	********************/
	public double getEdgeWeight(T beg, T end){
		try{
			return vertexes.get(beg).getDistance()+vertexes.get(end).getDistance(); //tries to return the weight between two vertexes
		}
		catch (Exception e){ //if it runs into an exception, it will return -1
			return -1;
		}
	}

	public String toString(){
		ArrayList<Vertex<T>> vertexList = this.toList(); //calls on toList to store vertexe as an array list
		StringBuilder output = new StringBuilder();
		while (!vertexList.isEmpty()){ //while vertexList is not empty
			Vertex<T> currentVertex = vertexList.remove(vertexList.size()-1); //remove the last element to save time so it doesn't have to shift anything
			output.append(currentVertex.getLabel());	//write the label of the vertex
			ArrayList<Vertex<T>> neighbors = new ArrayList<Vertex<T>>(); //creates new array list
			neighbors.addAll(currentVertex.getNeighbors()); //stores all the neighbors of the currentVertex
			for (int i =0; i < neighbors.size(); i++){ //prints out each of these neighbors in a neat fashion
				output.append("\t");
				output.append(currentVertex.getWeight(neighbors.get(i)));
				output.append(" --> ");
				output.append(neighbors.get(i).getLabel());
				output.append("\n");	
			}
				//new line
		}
		return output.toString(); //returns this string
	}
}