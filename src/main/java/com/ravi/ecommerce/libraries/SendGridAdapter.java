package com.ravi.ecommerce.libraries;

public interface SendGridAdapter {
     void sendEmailAsync(String email, String subject, String body);
}
