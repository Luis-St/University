package task10;

public class OrderService {
    private IPaymentGateway paymentGateway;

    public OrderService(IPaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public Order processOrder(Order order) {
        if (paymentGateway.processPayment(order.getAmount())) {
            return order; // Bestellung erfolgreich
        }
        return null; // Zahlung fehlgeschlagen
    }
}
