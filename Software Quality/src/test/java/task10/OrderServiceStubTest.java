package task10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Aufgabe 1.1 - Stub: liefert eine fest verdrahtete Antwort,
// es wird kein Verhalten ueberprueft (keine Verifikation).
public class OrderServiceStubTest {

    // Stub, der eine Zahlung immer als erfolgreich meldet
    static class PaymentGatewayStubErfolg implements IPaymentGateway {
        @Override
        public boolean processPayment(double amount) {
            return true;
        }
    }

    // Stub, der eine Zahlung immer ablehnt
    static class PaymentGatewayStubFehler implements IPaymentGateway {
        @Override
        public boolean processPayment(double amount) {
            return false;
        }
    }

    @Test
    void erfolgreicheZahlungGibtBestellungZurueck() {
        OrderService service = new OrderService(new PaymentGatewayStubErfolg());
        Order order = new Order(100.0);

        Order result = service.processOrder(order);

        assertSame(order, result);
    }

    @Test
    void fehlgeschlageneZahlungGibtNullZurueck() {
        OrderService service = new OrderService(new PaymentGatewayStubFehler());
        Order order = new Order(100.0);

        Order result = service.processOrder(order);

        assertNull(result);
    }
}
