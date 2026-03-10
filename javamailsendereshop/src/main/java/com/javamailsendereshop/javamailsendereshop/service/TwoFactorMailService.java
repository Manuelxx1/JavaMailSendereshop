package com.javamailsendereshop.javamailsendereshop.service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;

@Service
public class TwoFactorMailService {

    private final JavaMailSender mailSender;

    public TwoFactorMailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendCodeByEmail(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Tu código de verificación");
        message.setText("Tu código es: " + code + "\nExpira en 5 minutos.");
        mailSender.send(message);
    }
}
