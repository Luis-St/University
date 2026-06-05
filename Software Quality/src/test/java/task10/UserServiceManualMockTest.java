package task10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Aufgabe 2 - Mock ohne Framework:
// Der Mock zeichnet auf, ob sendConfirmationEmail mit der
// richtigen E-Mail-Adresse aufgerufen wurde.
public class UserServiceManualMockTest {

    static class EmailServiceMock implements IEmailService {
        int aufrufZaehler = 0;
        String letzteEmail = null;

        @Override
        public void sendConfirmationEmail(String email) {
            aufrufZaehler++;
            letzteEmail = email;
        }
    }

    @Test
    void registerUserSendetBestaetigungAnRichtigeAdresse() {
        EmailServiceMock mock = new EmailServiceMock();
        UserService service = new UserService(mock);

        service.registerUser(new User("Anna", "anna@example.com"));

        assertEquals(1, mock.aufrufZaehler);
        assertEquals("anna@example.com", mock.letzteEmail);
    }
}
