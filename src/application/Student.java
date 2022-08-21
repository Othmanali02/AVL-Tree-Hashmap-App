package application;

public class Student implements Comparable<Student>{
	int id;
	String name;
	double average;
	char gender;


	public Student(String name, int id, double average, char gender) {
		this.name = name;
		this.id = id;
		this.average = average;
		this.gender = gender;
	}
	
	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name + " " + id + " " + average + " " + gender;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public int compareTo(Student o) {
		return id-o.id;
	}
}
