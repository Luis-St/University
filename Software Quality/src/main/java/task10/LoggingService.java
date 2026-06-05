package task10;

public class LoggingService implements ILoggingService {
    public void logEvent(String event) {
        // Protokolliere das Ereignis (z. B. in einer Datenbank)
        System.out.println("Ereignis protokolliert: " + event);
    }
}
