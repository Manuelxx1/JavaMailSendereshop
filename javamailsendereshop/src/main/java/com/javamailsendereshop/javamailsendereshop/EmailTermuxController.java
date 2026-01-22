
package com.javamailsendereshop.javamailsendereshop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.javamailsendereshop.javamailsendereshop.service.EmailService;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmailTermuxController {

    // Asegúrate de que este sea el nombre de tu servicio de email en Termux
    @Autowired
    private EmailService emailService; 

    @PostMapping("/enviar-email")
    public void recibirDesdeRender(@RequestBody Map<String, String> datos) {
        try {
            String correo = datos.get("correo");
            // Ahora extraemos el mensaje completo que armamos en el Webhook
            String mensaje = datos.get("mensaje");
            String estado = datos.get("estado"); 

            System.out.println("Solicitud recibida desde Render para: " + correo);

            // Pasamos el mensaje completo al servicio
            emailService.enviarNotificacionDetallada(correo, mensaje, estado);
            
            System.out.println("✅ Email con detalles enviado exitosamente desde Termux.");
            
        } catch (Exception e) {
            System.err.println("❌ Error procesando el envío en Termux: " + e.getMessage());
        }
    }
}
