package application;


public class HashEntry {
	private int key;
	private Student value;
	private byte status; // insert: 1, delete: 2, empty: 0

	HashEntry(int key, Student value, byte status) {
		this.key = key;
		this.value = value;
		this.status = status;
	}

	public int getKey() {
		return key;
	}

	public String getValue() {
		return value.toString();
	}
	
	public Student getRecord() {
		return value;
	}
	
	public int getId() {
		return value.getId();
	}
	
	public String getName() {
		return value.getName();
	}

	
	public Student getValueObject() {
		return value;
	}

	public byte getStatus() {
		return status;
	}

	public void setDeleteStatus() {
		status = 2;
	}
	
	@Override
	public String toString() {
		return value.toString();
		
	}
}
