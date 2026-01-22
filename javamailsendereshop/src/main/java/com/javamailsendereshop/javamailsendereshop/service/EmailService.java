package com.javamailsendereshop.javamailsendereshop.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
// MÉTODO NUEVO: Para enviar el detalle completo que armamos en Render
    public void enviarNotificacionDetallada(String destinatario, String mensajeCompleto, String estado) {
        String asunto;
        
        // Determinamos el asunto según el estado que viene de MP
        switch (estado != null ? estado : "") {
            case "approved":
                asunto = "¡Tu compra fue aprobada! ✅";
                break;
            case "rejected":
                asunto = "Problema con tu pago ❌";
                break;
            default:
                asunto = "Información sobre tu pedido ⏳";
        }

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(destinatario);
        mailMessage.setSubject(asunto);
        mailMessage.setText(mensajeCompleto); // Aquí va todo el texto formateado

        try {
            mailSender.send(mailMessage);
            System.out.println("✅ Correo detallado enviado a " + destinatario);
        } catch (Exception e) {
            System.err.println("❌ Error enviando correo detallado: " + e.getMessage());
        }
    }

}
