package application;

public class HashMap {
	private int tableSize = 5;
	private HashEntry[] table;
	private int currentSize = 0;

	public HashMap(int size) {
		table = new HashEntry[size];
		for (int i = 0; i < size; i++)
			table[i] = null;
		tableSize = size;
		currentSize = 0;
	}

	public void makeEmpty() {
		for (int i = 0; i < table.length; i++)
			table[i] = null;
		currentSize = 0;
	}

	public int getCurrentSize() {
		return currentSize;
	}

	public int getTableSize() {
		return tableSize;
	}

	public boolean contains(int key) {
		return get(key) != null;
	}

	public String get(int id) {
		int i = 1;
		int key = getHash(id);
		while ((table[key] != null) && (table[key].getStatus() != 0)) {
			if (table[key].getKey() == id)
				return table[key].getValue();
			key = (key + i * i++) % tableSize;
		}
		return null;
	}

	public void remove(String name) {
		int i = 1;
		int key = getHash(name.hashCode());

		while ((table[key] != null) && (table[key].getStatus() > 0) && (table[key].getKey() != name.hashCode()))
			key = (key + i * i++) % tableSize;

		currentSize--;
		table[key].setDeleteStatus();
		if (table.length < tableSize / 4)
			shrink();
	}

	public void shrink() {
		HashMap newList; 
		newList = new HashMap(nextPrime(table.length / 2));
		// Copy table over
		for (int i = 0; i < table.length; i++)
			if ((table[i] != null) && (table[i].getStatus() == 1))
				newList.insert(table[i].getKey(), table[i].getValueObject());

		table = newList.table;
		tableSize = newList.tableSize;
	}

	public boolean search(String name) {
		int i = 1;
		int key = getHash(name.hashCode());

		while ((table[key] != null) && (table[key].getStatus() > 0) && (table[key].getKey() != name.hashCode()))
			key = (key + i * i++) % tableSize;

		currentSize--;
		if (table[key] == null)
			return false;
		return true;
	}

	public void insert(int key, Student value) {
		if (currentSize >= tableSize / 2)
			rehash();
		int hash = getHash(key);
		int i = 1;

		while ((table[hash] != null) && (table[hash].getStatus() != 0) && (table[hash].getStatus() != 2))
			hash = (hash + i * i++) % tableSize;

		currentSize++;
		table[hash] = new HashEntry(key, value, (byte) 1);
	}

	public int getHash(int key) {
		int hashVal = key;

		hashVal %= tableSize;
		if (hashVal < 0)
			hashVal += tableSize;
		return hashVal;
	}

	private void rehash() {
		HashMap newList;
		newList = new HashMap(nextPrime(2 * table.length));
		// Copy table over
		for (int i = 0; i < table.length; i++)
			if ((table[i] != null) && (table[i].getStatus() == 1))
				newList.insert(table[i].getKey(), table[i].getValueObject());

		table = newList.table;
		tableSize = newList.tableSize;
	}

	public static int nextPrime(int num) {
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

	public void printHashTable() {
		for (int i = 0; i < table.length; i++)
			if ((table[i] != null) && (table[i].getStatus() == 1))
				System.out.println(table[i].getValue().toString());
	}

	// returns a string of the hash table including empty spots
	@Override
	public String toString() {
		String str = "";

		for (int i = 0; i < table.length; i++) {
			try {
				if ((table[i].getStatus() == 1))

					str += table[i].getValue() + "\n";

			} catch (NullPointerException e) {
				str += "\n";

			}
		}

		return str;
	}

	public String toStringQuadratic() {
		String s = "                Hashtable   \n";
		for (int i = 0; i < table.length; i++) {
			try {
				if ((table[i].getStatus() == 1))

					s += i + ": " + table[i].getValue() + "\n";

			} catch (NullPointerException e) {
				s += i + ": null \n";

			}

		}
		s += "---------------------------------------------";
		return s;
	}

	public String toStringFileSave() {
		String s = "";
		for (int i = 0; i < table.length; i++) {
			try {
				if ((table[i].getStatus() == 1)) {

					s += table[i].getRecord().getName() + "/" + table[i].getRecord().getId() + "/"
							+ table[i].getRecord().getAverage() + "/" + table[i].getRecord().getGender() + "\n";
				}
			} catch (NullPointerException e) {

			}
		}
		return s;
	}

	public String toStringHashCode() {
		String s = "                Hashtable   \n";
		for (int i = 0; i < table.length; i++) {
			try {
				if ((table[i].getStatus() == 1))

					s += i + ": " + table[i].getValue().hashCode() + "\n";

			} catch (NullPointerException e) {
				s += i + ": null \n";

			}

		}
		s += "---------------------------------------------";
		return s;
	}

	// returns a string of the hash table as the file format
	public String toStringFile() {
		String str = "";
		for (int i = 0; i < table.length; i++)
			if ((table[i] != null) && (table[i].getStatus() == 1))
				str += table[i].toString() + "\n";

		return str;

	}

}
