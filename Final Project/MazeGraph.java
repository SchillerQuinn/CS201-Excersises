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
    
    if(!weighted) {
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
  }

  /*********************
   * This method loads a maze from a given file with name fname
   *********************/
  public static BasicGraphADT<String> loadMaze(String fname) {
    BasicGraphADT<String> mymaze = new AdjListGraph<String>(); 
    // change this to initalize your graph from the given file

    return mymaze;
  }

  /*********************
   * This method loads a maze from a given file with name fname as
   * a weighted graph
   *********************/
  public static WeightedGraphADT<String> loadWeightedMaze(String fname) {
    WeightedGraphADT<String> mymaze = null; // change this to initalize your graph
    // build your maze based on the given file
    return mymaze;
  }

  /******** 
   * This method should use a breadth-first traversal to find a path through the 
   * maze, then return that path.
   ******/
  public static List<Vertex<String>> solveMazeBreadthFirst(BasicGraphADT<String> maze, String startvert, String endvert) {
    // Use a breadth-first search to find a path through the maze
    return null;
  }

  /******** 
   * This method should use a depth-first traversal to find a path through the 
   * maze, then return that path.
   ******/
  public static List<Vertex<String>> solveMazeDepthFirst(BasicGraphADT<String> maze, String startvert, String endvert) {
    // Use a depth-first search to find a path through the maze
    return null;
  }

  /******** 
   * This method should use Dijkstra's algorithm to find the shortest cost path through the 
   * maze, then return that path.
   ******/
  public static List<Vertex<String>> solveMaze(WeightedGraphADT<String> maze, String startvert, String endvert) {
    // Use Dijkstra's algorithm to find a path through the maze
    return null;
  }
}
