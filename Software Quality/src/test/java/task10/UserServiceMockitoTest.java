package task10;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

// Aufgabe 2 - Mock mit Framework (Mockito):
// verify(...) prueft, dass sendConfirmationEmail mit der
// richtigen E-Mail-Adresse genau einmal aufgerufen wurde.
public class UserServiceMockitoTest {

    @Test
    void registerUserSendetBestaetigungAnRichtigeAdresse() {
        IEmailService emailService = mock(IEmailService.class);
        UserService service = new UserService(emailService);

        service.registerUser(new User("Anna", "anna@example.com"));

        verify(emailService, times(1)).sendConfirmationEmail("anna@example.com");
        verifyNoMoreInteractions(emailService);
    }
}
