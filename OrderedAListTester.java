public class OrderedAListTester{

	public static void main(String[] args){
		OrderedAList<String> test = new OrderedAList<String>();
  		
		/*******************************
			Testing the add, contains, get, remove method
			Should print:
			
			EmptyStackException
			true
			false
			a
			IndexOutofRangeException
			a
			null
			null
			null
			b
			0
			true

		**********************************/
  		test.remove("meme");
  		test.contains("meme");

  		test.add("b");
  		test.add("a");
  		test.add("r");

  		System.out.println(test.contains("a"));
  		System.out.println(test.contains("foo")); 
  		
  		System.out.println(test.get(0)); 
  		System.out.println(test.get(10)); 


  		System.out.println(test.remove("a"));
  		System.out.println(test.remove("foo"));
  		System.out.println(test.contains("foo"));
  		System.out.println(test.contains("a")); 

  		System.out.println(test.get(0));

  		test.clear();

  		System.out.println(test.size());
  		System.out.println(test.isEmpty());

  		}
}