package com.javamailsendereshop.javamailsendereshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper; // IMPORTANTE
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage; // IMPORTANTE (o javax.mail si usas Spring viejo)

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarNotificacionDetallada(String destinatario, String mensajeHtml, String estado) {
        String asunto;
        
        // 1. Determinamos el asunto según el estado
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

        try {
            // 2. CAMBIO CLAVE: Usamos MimeMessage en lugar de SimpleMailMessage
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            
            // El 'true' indica que el mensaje puede tener contenido HTML y archivos
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(destinatario);
            helper.setSubject(asunto);
            
            // 3. SEGUNDO CAMBIO CLAVE: El 'true' al final de setText activa el modo HTML
            helper.setText(mensajeHtml, true); 

            mailSender.send(mimeMessage);
            System.out.println("✅ Correo HTML enviado a " + destinatario);
            
        } catch (Exception e) {
            System.err.println("❌ Error enviando correo HTML: " + e.getMessage());
        }
    }
}
