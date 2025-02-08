package com.rvtjakula.paymentservice.configs;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayClientConfigs {

    @Value("${razorpay.key.secret}")
    private String razorpaySecret;

    @Value("${razorpay.key.id}")
    private String razorpayId;

    @Bean
    public RazorpayClient createRazorpayClient() throws RazorpayException {
        return new RazorpayClient(razorpayId, razorpaySecret);
    }

}
