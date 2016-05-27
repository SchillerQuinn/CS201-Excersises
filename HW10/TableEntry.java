//Quinn Schiller and Andrew Maris

public class TableEntry<K, V> {
	private  K key;  //key of the table entry
	private V value;  //value the entry holds

	public TableEntry(K key, V value) {  //initialize the entry 
		this.key = key;
		this.value = value;
	}

	public K getKey() { //return the key
		return key;
	}

	public V getValue() { //return the data held in this entry
		return value;
	}

	public void setValue(V value) { //set the value of this entry
		this.value = value;
	}
} 
