package application;

public class Chaueffer {
	public static void main(String[] args) {

		AVLTree<Integer> instance = new AVLTree<>();

		instance.insertElement(55);
		instance.insertElement(71);
		instance.insertElement(80);
		instance.insertElement(75);
		instance.insertElement(100);
		instance.insertElement(34);
		//instance.delete(34);

		instance.inorderTraversal();
		
		System.out.println();
		
		HashMap hash = new HashMap(10);
		Student s = new Student("Othman", 1202927, 89.5, 'M');
		Student u = new Student("Khalid", 1202598, 79.5, 'M');
		Student ssy = new Student("Mohammad", 1202365, 99.5, 'M');
//		
//		Student s1 = new Student("Jesse", 1202927, 89.5, 'M');
//		Student u1 = new Student("Walter", 1202598, 79.5, 'M');
//		Student ssy1 = new Student("Saul", 1202365, 99.5, 'M');
//		Student s2 = new Student("Mike", 1202927, 89.5, 'M');
//		Student u2 = new Student("Gus", 1202598, 79.5, 'M');
//		Student ssy2 = new Student("Skylar", 1202365, 99.5, 'F');
		
		hash.insert(s.hashCode(), s);
		System.out.println(s.hashCode());
		hash.insert(u.hashCode(), u);
		System.out.println(u.hashCode());
		hash.insert(ssy.hashCode(), ssy);
		System.out.println(ssy.hashCode());
		
//		hash.insert(s1.hashCode(), s1);
//		hash.insert(u1.hashCode(), u1);
//		hash.insert(ssy1.hashCode(), ssy1);
//		hash.insert(s2.hashCode(), s2);
//		hash.insert(u2.hashCode(), u2);
//		hash.insert(ssy2.hashCode(), ssy2);
		
		System.out.println(hash.search("Othman"));
		
		System.out.println(hash.toStringQuadratic());
//		HashTable <Student> hash = new HashTable<>();
//		hash.insert(new Student(12345, "Tala"));
//		hash.insert(new Student(12340, "Abdullah"));
//		hash.insert(new Student(12321, "Kareen"));
//		hash.insert(new Student(12347, "Othman"));
//		hash.insert(new Student(12348, "Ahmad"));
//		hash.insert(new Student(12342, "Zaina"));
		
//		System.out.println(hash.toString());
	}
}
