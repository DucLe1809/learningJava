package org.learningspringwithduc.firstspring;

public class OrderService {
    private PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder() {
        paymentService.processPayment(10);
    }

    /**
     * Setter function
     * @param paymentService
     */
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
