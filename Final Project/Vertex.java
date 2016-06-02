import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/*********************
 * Vertex class for use in building a graph implementation
 *
 * 
 *
 **********************/
public class Vertex<T> {

  /****
   * Label and neighborList are integral to what a vertex holds
   ***/
  private T label;
  private List<Vertex<T>> neighborList;

  //hold the distance to each neighbor
  private HashMap<Vertex<T>, Double> weights = new HashMap<Vertex<T>, Double>();

  /*******
   * distance and path are instance variables that will come 
   * in handy when running graph algorithms. You can interact
   * with them using the getters and setters, but they aren't 
   * automatically set to anything specific
   ********/
  private double distance;
  private List<Vertex<T>> path;

  /****************
   * Constructor for Vertex class, pass it the label
   ************/
  public Vertex(T lb) {
    this.label = lb;
    this.path = new ArrayList<Vertex<T>>();
    this.neighborList = new ArrayList<Vertex<T>>();
    this.distance = Double.POSITIVE_INFINITY;
  }

  /****************
   * Constructor for Vertex class with distance, pass it the label
   ************/
  public Vertex(T lb, int dist) {
    this.label = lb;
    this.path = new ArrayList<Vertex<T>>();
    this.neighborList = new ArrayList<Vertex<T>>();
    this.distance = (double) dist;
  }

  /***********
   * Returns the label used for this vertex
   ***********/
  public T getLabel() {
    return this.label;
  }

  /***************
   * Gets a hash code for this vertex
   ************/
  public int hashCode() {
    return this.label.hashCode();
  }

  /******************
   * Gets a list of this Vertex's neighbors
   *******************/
  public List<Vertex<T>> getNeighbors() {
    return this.neighborList;
  }

  
  /********************
   * Add a neighbor to the vertex (no weight specified)
   *******************/
  public void addNeighbor(Vertex<T> n) {
    if(!this.neighborList.contains(n)) {
      this.neighborList.add(n);
    }
  } 

  /********************
   * Add a neighbor to the vertex
   *******************/
  public void addNeighbor(Vertex<T> n, double dist) {
    if(!this.neighborList.contains(n)) {
      this.neighborList.add(n);
      this.weights.put(n, dist);
    }
  }
  /**************
   * Returns this Vertex as a string
   ******************/
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.label.toString());
    if(distance != Double.POSITIVE_INFINITY) {
      sb.append(String.format("(%2f)", distance));
    }
    return sb.toString();
  }

  /****************
   * Get this vertex's distance
   *****************/
  public double getDistance() {
    return this.distance;
  }

  public double getWeight(T end) {
    return this.weights.get(end);
  }

  /****************
   * Set this vertex's distance
   *****************/
  public void setDistance(double d) {
    this.distance = d;
  }

  /*********************
   * Get this vertex's path list
   *******************/
  public List<Vertex<T>> getPath() {
    return this.path;
  }

  /************************
   * Set this vertex's path list
   ***********************/
  public void setPath(List<Vertex<T>> p) {
    this.path = p;
  }

  public boolean equals(Object ob) {
    if(!(ob instanceof Vertex)) { return false; }

    return this.label.equals(((Vertex<T>)ob).getLabel());
  }
}
