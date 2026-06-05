package task10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Aufgabe 3 - Spy: Im Gegensatz zum Mock fuehrt der Spy das
// echte Verhalten weiter aus und zeichnet zusaetzlich die Aufrufe auf.
public class AnalyticsServiceSpyTest {

    // Handgeschriebener Spy: delegiert an den echten LoggingService
    // und protokolliert zugleich die Aufrufe.
    static class LoggingServiceSpy implements ILoggingService {
        private final LoggingService echt = new LoggingService();
        int aufrufZaehler = 0;
        String letztesEvent = null;

        @Override
        public void logEvent(String event) {
            aufrufZaehler++;
            letztesEvent = event;
            echt.logEvent(event); // echtes Verhalten wird ausgefuehrt
        }
    }

    @Test
    void trackEventRuftLogEventMitRichtigemEventAuf_handgeschriebenerSpy() {
        LoggingServiceSpy spy = new LoggingServiceSpy();
        AnalyticsService service = new AnalyticsService(spy);

        service.trackEvent("Login");

        assertEquals(1, spy.aufrufZaehler);
        assertEquals("Login", spy.letztesEvent);
    }

    @Test
    void trackEventRuftLogEventMitRichtigemEventAuf_mockitoSpy() {
        ILoggingService spy = spy(new LoggingService());
        AnalyticsService service = new AnalyticsService(spy);

        service.trackEvent("Login");

        verify(spy, times(1)).logEvent("Login");
    }
}
