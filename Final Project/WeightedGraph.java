import java.util.*;

/********************************* 
* Final Project 6/6/16
* Andrew Maris and Quinn Schiller
***********************************/

public class WeightedGraph<T> implements WeightedGraphADT<T>{


	HashMap<T, Vertex<T>> vertexes = new HashMap<T, Vertex<T>>();
	int edges = 0;

	
	public ArrayList<Vertex<T>> toList(){
		Collection<Vertex<T>> foo = vertexes.values();
		ArrayList<Vertex<T>> bar = new ArrayList<Vertex<T>>();
		bar.addAll(foo);
		return bar;
	}


	/*******************
	* Get the vertex object at the lable
	* @return the vertex object
	********************/
	public Vertex<T> getVertex(T vert){
		return vertexes.get(vert);
	}


	/*******************
	* Add a vertex to this graph with given label
	* @return Whether the vertex was successfully added
	********************/
	public boolean addVertex(T vert){
		try{
			vertexes.put(vert, new Vertex<T>(vert));
			return true;
		} catch (Exception e){
			return false;
		}
	}

	//putting in vertex items
	public boolean addVertexObj(Vertex<T> vert){
		try{
			vertexes.put(vert.getLabel(), vert);
			return true;
		} catch (Exception e){
			return false;
		}
	}


	/******************
	* Add an edge to this graph between the two given labels
	* @return Whether the edge was successfully added
	********************/
	public boolean addEdge(T beg, T end){
		try{
			vertexes.get(beg).addNeighbor(vertexes.get(end));
			vertexes.get(end).addNeighbor(vertexes.get(beg));
			edges++;
			return true;
		} catch (Exception e){
			return false;
		}
	}

	/******************
	* Add an edge to this graph between the two given labels with the given weight
	* @return Whether the edge was successfully added
	********************/
	public boolean addEdge(T beg, T end, double weight){
		try{
			vertexes.get(beg).addNeighbor(vertexes.get(end),weight);
			vertexes.get(end).addNeighbor(vertexes.get(beg),weight);
			edges++;
			return true;
		} catch (Exception e){
			return false;
		}
	}

	/******************
	* Tests whether a vertex exists in the graph
	* @return Whether the vertex exists
	********************/
	public boolean hasVertex(T vert){
		return vertexes.containsKey(vert);
	}

	/******************
	* Tests whether an edge exists in the graph
	* @return Whether the edge exists
	********************/
	public boolean hasEdge(T beg, T end){
		if (vertexes.get(beg).getNeighbors().contains(end) && vertexes.get(end).getNeighbors().contains(beg)){
			return true;
		}
		else{
			return false;
		}
	}

	/*****************
	* Tests if the graph is empty
	* @return Whether the graph is empty
	*******************/
	public boolean isEmpty(){
		return vertexes.isEmpty();
	}

	/********************
	* Gets the number of vertices
	* @return The number of vertices
	*******************/
	public int getNumVertices(){
		return vertexes.size();
	}

	/********************
	* Gets the number of edges
	* @return The number of edges
	*********************/
	public int getNumEdges(){
		return edges;
	}

	/**************
	* Clear all edges and vertices from the graph
	********************/
	public void clear(){
		edges = 0;
		vertexes.clear();
	}

	/******************
	* Add an edge to this graph between the two given labels with the given weight
	* @return Whether the edge was successfully added
	********************/
	public double getEdgeWeight(T beg, T end){
		try{
			return vertexes.get(beg).getDistance()+vertexes.get(end).getDistance();
		}
		catch (Exception e){
			return -1;
		}
	}

	public String toString(){
		ArrayList<Vertex<T>> vertexList = this.toList();
		StringBuilder output = new StringBuilder();
		while (!vertexList.isEmpty()){
			Vertex<T> currentVertex = vertexList.remove(vertexList.size()-1); //remove the last element to save time so it doesn't have to shift anything
			output.append(currentVertex.getLabel());	//write the label of the vertex
			ArrayList<Vertex<T>> neighbors = new ArrayList<Vertex<T>>();
			neighbors.addAll(currentVertex.getNeighbors());
			for (int i =0; i < neighbors.size(); i++){
				output.append("\t");
				output.append(currentVertex.getWeight(neighbors.get(i)));
				output.append(" --> ");
				output.append(neighbors.get(i).getLabel());
				output.append("\n");	
			}
				//new line
		}
		return output.toString();
	}
}