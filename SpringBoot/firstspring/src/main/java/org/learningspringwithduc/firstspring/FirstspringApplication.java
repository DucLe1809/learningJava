package org.learningspringwithduc.firstspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstspringApplication.class, args);
        var orderService = new OrderService(new PayPalPaymentService());
        // orderService.setPaymentService(new PayPalPaymentService());
        orderService.placeOrder();
    }

}
