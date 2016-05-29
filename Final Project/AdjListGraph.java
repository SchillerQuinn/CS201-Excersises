import java.util.HashMap;

public class AdjListGraph<T> implements BasicGraphADT<T>{

	HashMap<T, Vertex<T>> vertexes = new HashMap<T, Vertex<T>>();
	int edges = 0;
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
			return vertexes.get(beg).getWeight(end);
		}
		catch (Exception e){
			return -1;
		}
	}
}