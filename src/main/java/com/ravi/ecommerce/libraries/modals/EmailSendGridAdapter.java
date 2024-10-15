package com.ravi.ecommerce.libraries.modals;

import com.ravi.ecommerce.libraries.SendGridAdapter;
import org.springframework.stereotype.Component;

@Component
public class EmailSendGridAdapter implements SendGridAdapter {
    private Sendgrid sendGrid;
    public EmailSendGridAdapter() {
        this.sendGrid = new Sendgrid();
    }
    @Override
    public void sendEmailAsync(String email, String subject, String body) {
        sendGrid.sendEmailAsync(email, subject, body);
    }
}
