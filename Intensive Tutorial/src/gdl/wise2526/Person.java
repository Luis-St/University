package gdl.wise2526;

public class Person {
	
	public String name;
	public int alter;
	
	public static void main(String[] args) {
		
		Person person = new Person();
		person.name = "Luis";
		person.method(1);
		
		
	}
	
	public void method(int a) {
		System.out.println(name);
		
	}
	
}
