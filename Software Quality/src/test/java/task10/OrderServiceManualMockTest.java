package task10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Aufgabe 1.2 - Mock ohne Framework: der Mock zeichnet die Interaktion auf
// (wurde er aufgerufen, mit welchem Betrag, wie oft) und der Test verifiziert dies.
public class OrderServiceManualMockTest {

    // Handgeschriebener Mock, der das gewuenschte Rueckgabeverhalten
    // sowie die empfangenen Aufrufe protokolliert.
    static class PaymentGatewayMock implements IPaymentGateway {
        private final boolean rueckgabe;
        int aufrufZaehler = 0;
        double letzterBetrag = Double.NaN;

        PaymentGatewayMock(boolean rueckgabe) {
            this.rueckgabe = rueckgabe;
        }

        @Override
        public boolean processPayment(double amount) {
            aufrufZaehler++;
            letzterBetrag = amount;
            return rueckgabe;
        }
    }

    @Test
    void processPaymentWirdMitRichtigemBetragAufgerufen() {
        PaymentGatewayMock mock = new PaymentGatewayMock(true);
        OrderService service = new OrderService(mock);

        service.processOrder(new Order(100.0));

        // Verifikation der Interaktion
        assertEquals(1, mock.aufrufZaehler);
        assertEquals(100.0, mock.letzterBetrag, 0.0001);
    }

    @Test
    void erfolgreicheZahlungGibtBestellungZurueck() {
        PaymentGatewayMock mock = new PaymentGatewayMock(true);
        OrderService service = new OrderService(mock);
        Order order = new Order(50.0);

        assertSame(order, service.processOrder(order));
    }

    @Test
    void fehlgeschlageneZahlungGibtNullZurueck() {
        PaymentGatewayMock mock = new PaymentGatewayMock(false);
        OrderService service = new OrderService(mock);

        assertNull(service.processOrder(new Order(50.0)));
        assertEquals(1, mock.aufrufZaehler);
    }
}
