package task10;

public class UserService {
    private IEmailService emailService;

    public UserService(IEmailService emailService) {
        this.emailService = emailService;
    }

    public void registerUser(User user) {
        // Benutzerregistrierung (z. B. in einer Datenbank speichern)
        // Sende Bestaetigungs-E-Mail
        emailService.sendConfirmationEmail(user.getEmail());
    }
}
