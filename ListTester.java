//My Partner is Andrew Marris

public class ListTester {

  public static void main(String[] args) {
    UnorderedListADT<String> testList = new LinkedUList<String>();

    //start by testing initialization
    System.out.println("Printing list size: " + testList.size() + " (should be 0)");
    System.out.println("Printing list empty: " + testList.isEmpty() + " (should be true)");

    //test adding
    testList.add("a", 0);
    testList.add("s", 1);
    testList.add("d", 2);
    testList.add("f", 3);

    //test that everything was entered correctly
    System.out.println("Printing list size: " + testList.size() + " (should be 4)");
    System.out.println("Printing list empty: " + testList.isEmpty() + " (should be false)");
    
    //test contains
    System.out.println("Contains a: " + testList.contains("a") + " (should be true)");
    System.out.println("Contains b: " + testList.contains("b") + " (should be false)");
    
    //test get
    System.out.println("What is at the first index?: " + testList.get(0) + " (should be a)");
    System.out.println("What is at the second index?: " + testList.get(1) + " (should be s)");

    //test removing
    System.out.println("Removing the first index...");
    testList.remove("a");
    System.out.println("Contains a: " + testList.contains("a") + " (should be false)");
    System.out.println("What is at the new first index?: " + testList.get(0) + " (should be s)");
    
    //test clearing
    testList.clear();
    System.out.println("Has list been cleared: " + testList.isEmpty() + " (should be true)");
  }
}
