package task10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Aufgabe 1.3 - Mock mit Framework (Mockito):
// when(...).thenReturn(...) konfiguriert das Verhalten,
// verify(...) prueft die Interaktion.
public class OrderServiceMockitoTest {

    @Test
    void erfolgreicheZahlungGibtBestellungZurueck() {
        IPaymentGateway gateway = mock(IPaymentGateway.class);
        when(gateway.processPayment(100.0)).thenReturn(true);
        OrderService service = new OrderService(gateway);
        Order order = new Order(100.0);

        Order result = service.processOrder(order);

        assertSame(order, result);
        verify(gateway).processPayment(100.0);
    }

    @Test
    void fehlgeschlageneZahlungGibtNullZurueck() {
        IPaymentGateway gateway = mock(IPaymentGateway.class);
        when(gateway.processPayment(anyDouble())).thenReturn(false);
        OrderService service = new OrderService(gateway);

        Order result = service.processOrder(new Order(100.0));

        assertNull(result);
        verify(gateway, times(1)).processPayment(100.0);
    }
}
