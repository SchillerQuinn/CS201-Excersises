import java.util.Comparator;

/********
 * Simple String Comparator class that just uses String's 
 * built-in compare method to compare two strings
 ********/
public class StringComparator implements Comparator<String> {
  public int compare(String s1, String s2) {
    return s1.compareTo(s2);
  }
}
