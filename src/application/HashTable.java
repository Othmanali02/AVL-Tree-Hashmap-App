package application;

public class HashTable<T extends Comparable<T>> {
	int capacity = 10;
	@SuppressWarnings("unchecked")
	HashNode<T>[] QuadTable = new HashNode[capacity];
	String[] HashCodeTable = new String[capacity];

	public HashTable(int capacity) {
		
		for (int i = 0; i < QuadTable.length; i++) {

			QuadTable[i] = new HashNode<>("");
			
			HashCodeTable = new String[capacity];

		}

		
		
		
	}

	private int nextPrime(int num) {
		num++;
		for (int i = 2; i < num; i++) {
			if (num % i == 0) {
				num++;
				i = 2;
			} else {
				continue;
			}
		}
		return num;
	}

	private void reHash() {
		HashTable<T> rehashList = new HashTable<T>(nextPrime(2 * QuadTable.length));
		for (int i = 0; i < QuadTable.length; i++) {
			if ((QuadTable[i] != null) && (QuadTable[i].status != 0) && (QuadTable[i].status != 2)) {
				String[] split = QuadTable[i].data.split(" "); 
				rehashList.insertQuadratic(new Student(split[0], Integer.parseInt(split[1]),
						Double.parseDouble(split[2]), split[3].charAt(0)));
			}
			QuadTable = rehashList.QuadTable;
			this.capacity = rehashList.capacity;
		}

	}

	public void insertQuadratic(Student student) {
		int collision = 0;

		int hashCode = student.hashCode();
		int index = hashCode % QuadTable.length;
		if (index > capacity / 2)
			reHash();
		
		if (index < capacity / 2 && QuadTable[index].data.equals("")) {
			QuadTable[index].data = student.toString();
			HashCodeTable[index] = student.hashCode() + ""; 
		} else if (index < capacity / 2 && !QuadTable[index].data.equals("")) {
			int s = index;
			while (!QuadTable[index].data.equals("")) {
				collision++;
				index = (s + collision * collision) % QuadTable.length;
			}
			if (index > capacity / 2)
				reHash();
			QuadTable[index].data = student.toString();
			HashCodeTable[index] = student.hashCode() + "";
		}
	}
	
	public int Quadlength() {
		return this.capacity;
	}

	public String toStringQuadratic() {
		String s = "                Hashtable   \n";
		for (int i = 0; i < QuadTable.length; i++) {
			s += i + " : " + QuadTable[i].data + "\n";

		}
		s += "---------------------------------------------";
		return s;
	}

	public String toStringHashcode() {

		String s = "                Hashtable HashCodes   \n";
		for (int i = 0; i < HashCodeTable.length; i++) {

			if (HashCodeTable[i] != null)
				s += i + " : " + HashCodeTable[i] + "\n";
			else
				s += i + " : null \n";

		}
		s += "---------------------------------------------";
		return s;
	}

}
