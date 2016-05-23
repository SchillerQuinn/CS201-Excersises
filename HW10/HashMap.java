import java.util.List;
import java.util.LinkedList;
import java.lang.StringBuilder;

public class HashMap<K, V> {
	private int size;
	private int INITIAL_SIZE = 101;
	private LinkedList<TableEntry<K,V>>[] table;
	@SuppressWarnings("unchecked")	//make the compiler stop yelling at us
	
	public HashMap(){
		table = (LinkedList<TableEntry<K,V>>[]) new LinkedList[INITIAL_SIZE];
		for (int i = 0; i < INITIAL_SIZE; i++) {
			table[i] = new LinkedList<TableEntry<K,V>>();
		}
	}

	public V get(K key) {
		LinkedList<TableEntry<K,V>> subTable = table[Math.abs(key.hashCode()%INITIAL_SIZE)];
		for (int i = 0; i < subTable.size(); i++) {
			if (subTable.get(i).getKey().equals(key)) {
				return subTable.get(i).getValue();
			}
		}
		return null;
	}

	public boolean containsKey(K key){
		LinkedList<TableEntry<K,V>> subTable = table[Math.abs(key.hashCode()%INITIAL_SIZE)];
		for (int i = 0; i < subTable.size(); i++) {
			if (subTable.get(i).getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsValue(V value){
		for (int k = 0; k < INITIAL_SIZE; k++) { //you have to check every value because there is no key
			LinkedList<TableEntry<K,V>> subTable = table[k];
			for (int i = 0; i < subTable.size(); i++) {
				if (subTable.get(i).getValue().equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

	public void put(K key, V value) {
		table[Math.abs(key.hashCode()%INITIAL_SIZE)].addFirst(new TableEntry(key, value));
	}

	private boolean subTableRemove(LinkedList<TableEntry<K,V>> subTable, TableEntry<K,V> key){
		for (int i = 0; i < subTable.size(); i++) {
			if (subTable.get(i).getKey().equals(key.getKey())) {
				subTable.remove(subTable.get(i));
				return true;
			}
		}
		return false;
	}


	public String toCount() {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < INITIAL_SIZE; i++){
			LinkedList<TableEntry<K,V>> subTable = table[i];
			while (!subTable.isEmpty()){
				int count = 1;
				boolean stop = false;
				TableEntry<K,V> key = subTable.poll(); //remove and store the first item on the list
				while (this.subTableRemove(subTable,key)){
					count++;
					//subTable.remove(key);
				}
				output.append(key.getKey());
				output.append(": ");
				output.append(count);
				output.append("\n");
			}
		}
		return output.toString();
	}


	public int size() {
		return this.size;
	}

	public void remove(K key) {
		LinkedList<TableEntry<K,V>> subTable = table[Math.abs(key.hashCode()%INITIAL_SIZE)];
		for (int i = 0; i < subTable.size(); i++) {
			if (subTable.get(i).getKey().equals(key)) {
				subTable.remove(i); // hopefull this will work
				//table.remove(table.get(i));
				size--;
			}
		}
	}
} 