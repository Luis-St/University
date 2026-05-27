package wise2526.ka;

public class Aufgabe8 {
	
	public static class A {
		public String a = "";
		public A nachfolger = null;
		
		public A(String p) {
			a = p;
		}
		
		public void ausgabe() {
			System.out.println("***");
			aus(1);
		}
		
		public void aus(int i) {
			System.out.println(i + ": " + a);
			if (nachfolger != null)
				nachfolger.aus(i + 1);
		}
	}
	
	public static void main(String[] args) {
		A p = new A("Katze");
		A q = new A("Hund");
		A r = new A("Maus");
		
		p.nachfolger = q;
		q.nachfolger = r;
		p.ausgabe();
		q.ausgabe();
		A s = q;
		s.a = "Pferd";
		s.ausgabe();
		p.ausgabe();
		q.nachfolger = null;
		p.ausgabe();
		r = null;
		p.ausgabe();
		q.nachfolger = s;
		p.ausgabe();
	}
}
