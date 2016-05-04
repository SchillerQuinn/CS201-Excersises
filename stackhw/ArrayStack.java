import java.util.EmptyStackException;

public class ArrayStack<T> implements StackADT<T>{
	private T[] data;	//holds the data
	private int count;	//holds the size of the stack
	
	//this is the constructor
	public ArrayStack(){
		this.count = 0;
		this.data = (T[]) new Object[10]; //it will make a compile warning
	}

	public void push(T item){
		if(this.count == this.data.length){
			T[] holder = (T[]) new Object[2*this.data.length];		//holder variable that will hold value while we make the array bigger
			for (int i=0; i <= count; i++){				//loop through the data variable.
				holder[i] = this.data[i]; 				//copy the data
			}
			this.data = holder; //set the data equal to the holder values
		}
		this.data[this.count] = item; //set data equal to the new value
		count++;
	}
	public T pop(){
		if (this.count == 0){		//if the array is empty
			throw new EmptyStackException(); //raise an exception because you can't pop an empty array.
		}
		T popval = data[count]; //store the popvalue so it saves after the array has been changed
		this.data[count] = null;
		this.count--; //adjust the size
		return popval;	//return the pop value
	}
	public T peek(){
		if (this.count == 0){
			throw new EmptyStackException();
			//throw new Exception("Can't pop an empty stack");
		}
		return data[count]; //return the first value in the stack
	}
	public boolean isEmpty(){
		if (count == 0){ //if the list is empty, return true
			return true;
		}
		else{
			return false;
		}
	}
	public int size(){
		return this.count; //return the size of the list
	}
	public void clear(){
		T[] data = (T[]) new Object[10];
		this.count = 0; //just clears data
	}

	/*public static void main(String[] args) {
		ArrayStack<String> test = new ArrayStack<String>();
		test.push("swag");
		test.push("second");
		try{
			System.out.println(test.pop());
			System.out.println(test.peek());	
		}
		catch (Exception e){

		}
		System.out.println(test.isEmpty());
				try{
			System.out.println(test.peek());	
		}
		catch (Exception e){
			System.out.println("Peek error works");
		}
				try{
			
			System.out.println(test.peek());	
		}
		catch (Exception e){
			System.out.println("Pop error works");
		}
		try{
			for (int i = 0; i <10; i++)
				System.out.println(test.data[i]);
		}catch (Exception e){

		}

	}*/
	
}