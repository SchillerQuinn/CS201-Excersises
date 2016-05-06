public class OrderedAListTester{

	public static void main(String[] args){
		StringComparator sComp = new StringComparator();
		OrderedAList<String> test = new OrderedAList<String>(sComp);
  		
		/*******************************
			Testing the add, contains, get, remove method
			Should print unless commented out:
			
			//EmptyStackException
			//EmptyStackException
			5
			true
			false
			a
			b
			c
			d
			e
			//IndexOutOfBException
			a
			null
			4
			false
			false
			b
			c
			0
			true

		**********************************/
  		//test.remove("meme");
  		//test.contains("meme");

  		test.add("b");
  		test.add("a");
  		test.add("d");
  		test.add("c");
  		test.add("e");
  		System.out.println(test.size());

  		System.out.println(test.contains("a"));
  		System.out.println(test.contains("foo")); 
  		
  		System.out.println(test.get(0)); 
  		System.out.println(test.get(1)); 
  		System.out.println(test.get(2)); 
  		System.out.println(test.get(3)); 
  		System.out.println(test.get(4)); 
  		//System.out.println(test.get(10)); 


  		System.out.println(test.remove("a"));
  		System.out.println(test.remove("foo"));
  		System.out.println(test.size());
  		System.out.println(test.contains("foo"));
  		System.out.println(test.contains("a")); 

  		System.out.println(test.get(0));
  		System.out.println(test.get(1));

  		test.clear();

  		System.out.println(test.size());
  		System.out.println(test.isEmpty());

  		}
}
