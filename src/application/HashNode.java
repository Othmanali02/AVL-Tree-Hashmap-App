package application;

public class HashNode<T extends Comparable<T>> {
	String data;
	int status = 0; 

	public HashNode(String value) {
		this.data = value;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String  getData() {
		return data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	

}
