package task10;

// AnalyticsService-Klasse, die die Ereignisse verfolgt
public class AnalyticsService {
    private ILoggingService loggingService;

    public AnalyticsService(ILoggingService loggingService) {
        this.loggingService = loggingService;
    }

    public void trackEvent(String event) {
        // Erledige einige Sachen ... Dann
        loggingService.logEvent(event);
    }
}
