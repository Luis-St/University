public class ZugriffsZeiten {
	
	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();
		
		long maxMemory = runtime.maxMemory() - 8 * 1024 * 1024;
		int arraySize = (int)(maxMemory / 8);
		
		System.out.println("Verfügbarer Heap-Speicher: " + maxMemory / (1024 * 1024) + " MB");
		System.out.println("Array-Größe: " + arraySize + " Elemente");
		System.out.println("Theoretischer Speicherbedarf: " + (arraySize * 8L) / (1024 * 1024) + " MB");
		
		try {
			System.out.println("Erstelle Array...");
			double[] zahlenFeld = new double[arraySize];
			System.out.println("Array erstellt.");
			
			while (true) {
				long startzeit = System.currentTimeMillis();
				
				for (int i = 0; i < zahlenFeld.length; i++) {
					zahlenFeld[i] = Math.random();
				}
				
				long endzeit = System.currentTimeMillis();
				
				long gesamtzeit = endzeit - startzeit;
				double zugriffsZeitProElement = (double) gesamtzeit / zahlenFeld.length;
				
				System.out.println(
					"Durchlauf abgeschlossen in " + gesamtzeit + " ms, " +
						"Zugriffszeit pro Element: " + zugriffsZeitProElement * 1_000_000 + " ns, " +
						"Elemente pro Sekunde: " + (long)((zahlenFeld.length / (double)gesamtzeit) * 1000)
				);
				
				Thread.sleep(1000);
			}
		} catch (OutOfMemoryError e) {
			System.err.println("Fehler: Nicht genügend Speicher verfügbar");
			System.err.println("Versuchen Sie, die JVM mit einer größeren Heap-Größe zu starten:");
			System.err.println("java -Xms<Größe>m -Xmx<Größe>m ZugriffsZeiten");
			System.err.println("Beispiel: java -Xms4096m -Xmx4096m ZugriffsZeiten");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Programm wurde unterbrochen.");
		}
	}
}
