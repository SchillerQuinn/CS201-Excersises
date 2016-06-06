import java.util.*;

/********************************* 
* Final Project 6/6/16
* Andrew Maris and Quinn Schiller
***********************************/


/*********************
 * Vertex class for use in building a graph implementation
 *
 * 
 *
 **********************/

//adding the implements comparator section allows us to use the priority queue
public class Vertex<T> implements Comparable<Vertex<T>> {

	/****
	 * Label and neighborList are integral to what a vertex holds
	 ***/
	private T label;
	private LinkedList<Vertex<T>> neighborList;

	//hold the distance to each neighbor
	private HashMap<Vertex<T>, Double> weights = new HashMap<Vertex<T>, Double>();

	/*******
	 * distance and path are instance variables that will come 
	 * in handy when running graph algorithms. You can interact
	 * with them using the getters and setters, but they aren't 
	 * automatically set to anything specific
	 ********/
	private double distance;
	private LinkedList<Vertex<T>> path;

	/****************
	 * Constructor for Vertex class, pass it the label
	 ************/
	public Vertex(T lb) {
		this.label = lb; //intializes instance variables
		this.path = new LinkedList<Vertex<T>>();
		this.neighborList = new LinkedList<Vertex<T>>();
		this.distance = Double.POSITIVE_INFINITY;
	}

	/****************
	 * Constructor for Vertex class with distance, pass it the label
	 ************/
	public Vertex(T lb, int dist) {
		this.label = lb; //initializes instance vaibles with a distance
		this.path = new LinkedList<Vertex<T>>();
		this.neighborList = new LinkedList<Vertex<T>>();
		this.distance = (double) dist;
	}

	/***********
	 * Returns the label used for this vertex
	 ***********/
	public T getLabel() {
		return this.label; //returns the label
	}

	/***************
	 * Gets a hash code for this vertex
	 ************/
	public int hashCode() {
		return this.label.hashCode(); //returns the hash code
	}

	/******************
	 * Gets a list of this Vertex's neighbors
	 *******************/
	public LinkedList<Vertex<T>> getNeighbors() {
		LinkedList<Vertex<T>> foo = new LinkedList<Vertex<T>>(); //creates new list
		foo.addAll(this.neighborList); //adds all neighbors into
		return foo; //returns the list
	}



	public LinkedList<String> getStringNeighbors() {
		LinkedList<String> foo = new LinkedList<String>(); //creates new list
		int count = neighborList.size(); //takes note of the size of the of the neighborList
		for(int i = 0; i < count; i++){ //adds ever label to the list
			foo.add(neighborList.get(i).getLabel().toString());
		}
		return foo; //returns the list
	}

	
	/********************
	 * Add a neighbor to the vertex (no weight specified)
	 *******************/
	public void addNeighbor(Vertex<T> n) {
		if(!this.neighborList.contains(n)) { //if the neighborList does not already contain this item
			this.neighborList.add(n); // it adds the item to the neighborList
		}
	} 

	/********************
	 * Add a neighbor to the vertex
	 *******************/
	public void addNeighbor(Vertex<T> n, double weight) {
		if(!this.neighborList.contains(n)) {  //if the neighborList does not already contain this item
			this.neighborList.add(n); // it adds the item to the neighborList
			this.weights.put(n, weight); // then adds weight to the vertex
		}
	}
	/**************
	 * Returns this Vertex as a string
	 ******************/
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.label.toString()); 
		if(distance != Double.POSITIVE_INFINITY) { //if the distance is not positive infinity
			sb.append(String.format("(%2f)", distance));
		}
		return sb.toString(); //returns the built string
	}

	/****************
	 * Get this vertex's distance
	 *****************/
	public double getDistance() {
		return this.distance; //returns the distance variable
	}

	/****************
	 * Get this vertex's weight
	 *****************/
	public double getWeight(Vertex<T> end) {
		return this.weights.get(end); //returns the weight variable
	}

	/****************
	 * Set this vertex's distance
	 *****************/
	public void setDistance(double d) {
		this.distance = d; //sets the distance variable
	}

	/*********************
	 * Get this vertex's path list
	 *******************/
	public LinkedList<Vertex<T>> getPath() {
		return this.path; //asks for the path list
	}

	/************************
	 * Set this vertex's path list
	 ***********************/
	public void setPath(LinkedList<Vertex<T>> p) {
		this.path = p; //gives the vertex a new path
	}

	/*****************
	 * Allows us to see if two vertexes are the same
	 *****************/
	public boolean equals(Object ob) {
		if(!(ob instanceof Vertex)) { return false; } //if we are not comparing like objects, return false
		return this.label.equals(((Vertex<T>)ob).getLabel()); //if the labels are the same, they are equal!
	}

	/****************
	 * Which vertex has the longer distance
	 *****************/
	public int compareTo(Vertex<T> vert){
		return (int) (this.getDistance() - (vert.getDistance())); //finds which vertex distance is longer
	}

	/****************
	 * Which vertex has the longer distance
	 *****************/
	public int compare(Vertex<T> v1, Vertex<T> v2){
		return (int) (v1.getDistance() -(v2.getDistance())); //finds which vertex distance is longer
	}

}
