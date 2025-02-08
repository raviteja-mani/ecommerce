package com.rvtjakula.paymentservice.paymentgateways;

public interface PaymentGateway {
    public String generatePaymentLink(String email, String phoneNumber, Long amount, String orderId);
}
