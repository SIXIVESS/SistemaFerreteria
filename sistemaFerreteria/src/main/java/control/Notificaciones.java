package control;


import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import vista.FrmControlExistencias;

/**
 *
 * @author Dórame
 */
public class Notificaciones {

    PopupMenu popup = new PopupMenu();
    private Image image = new ImageIcon(getClass().getResource("/Imagenes/noti.png")).getImage();
    private final TrayIcon trayIcon = new TrayIcon(image, "Aplicación Java", popup);
    final SystemTray systemtray = SystemTray.getSystemTray();
    private Timer timer;
    public static boolean band;
    public  String prod = "";


    public Notificaciones() {
       
        if (SystemTray.isSupported()) {

            MouseListener mouseListener = new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent evt) {
                   
                    if (evt.getButton() == MouseEvent.BUTTON1)
                    {
                        MensajeTrayIcon("Por favor verifique la información", TrayIcon.MessageType.WARNING);
                    }
                }
                @Override
                public void mouseEntered(MouseEvent evt) {
                }

                @Override
                public void mouseExited(MouseEvent evt) {
                }

                @Override
                public void mousePressed(MouseEvent evt) {
                }

                @Override
                public void mouseReleased(MouseEvent evt) {
                }
            };

       
            ActionListener salir = (ActionEvent e) -> {
                Notificaciones.band = true;
            };

            ActionListener verinformacion = (ActionEvent e) -> {
                JOptionPane.showMessageDialog(null, "Productos bajos en Stock; " + "\n" + prod, "Aplicación Java",  JOptionPane.INFORMATION_MESSAGE);
                band = true;
            };
          
            MenuItem SalirItem = new MenuItem("Salir");
            SalirItem.addActionListener(salir);
            popup.add(SalirItem);

            MenuItem Itemverinfo = new MenuItem("Ver informacion");
            Itemverinfo.addActionListener(verinformacion);
            popup.add(Itemverinfo);
            trayIcon.setImageAutoSize(true);
            trayIcon.addMouseListener(mouseListener);

            try {
                systemtray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("Error:" + e.getMessage());
            }
        } else {
            System.err.println("Error: sin soporte");
        }
    }

    public void MensajeTrayIcon(String texto, TrayIcon.MessageType tipo) {
        trayIcon.displayMessage("Alerta Ferreteria! :", texto, tipo);
    }

    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
           
            if (Notificaciones.band) {
                timer.cancel();
            } else {
                    prod += " " + FrmControlExistencias.msj;
                notificacion();
            }
        }

        public void notificacion() {
            MensajeTrayIcon("""
                            Productos bajos en Stock 
                             Verifique la lista de productos """, TrayIcon.MessageType.INFO);
        }
    }

    public void mje() throws AWTException {
        Notificaciones.band = false;
        timer = new Timer();
        timer.schedule(new MyTimerTask(), 0, 100000);
    }
}

                   

