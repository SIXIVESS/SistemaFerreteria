package control;

import java.awt.TrayIcon;
import java.util.Timer;
import java.util.TimerTask;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

/**
 *
 * @author Samuel Vega
 */
public class NotificacionesTest {
    
    public NotificacionesTest() {}

    @Test
    public void testInicializacion() {
        System.out.println("Prueba de inicialización.");
        
        Notificaciones notificaciones = new Notificaciones();
        assertNotNull(notificaciones);
    }

    @Test
    public void testMensajeTrayIcon() throws Exception {
        System.out.println("Prueba de mensaje de alerta.");
        
        TrayIcon mockTrayIcon = Mockito.mock(TrayIcon.class);
        Notificaciones notificaciones = new Notificaciones();
        notificaciones.setTrayIcon(mockTrayIcon);
        
        notificaciones.MensajeTrayIcon("Mensaje de prueba", TrayIcon.MessageType.INFO);
        
        Mockito.verify(mockTrayIcon).displayMessage("Alerta Ferreteria! :", "Mensaje de prueba", TrayIcon.MessageType.INFO);
    }
    
}
