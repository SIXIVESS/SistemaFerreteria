package DAO;

import dominio.Venta;
import excepciones.DAOException;
import interfaces.IConexionDB;
import interfaces.IVentaDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VentaDAOIT {
    private static IConexionDB conexionDB;
    private IVentaDAO ventas;
    
    @BeforeAll
    public static void setUpClass() throws Exception {
        conexionDB = new IConexionDB() {
            @Override
            public Connection crearConexion() throws SQLException {
                return java.sql.DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "root", "");
            }
        };
        
        try(
            Connection conexion = conexionDB.crearConexion();
            Statement stmt = conexion.createStatement();
        ) {
            String sql = ""
                    + "CREATE TABLE ventas("
                    + "VentaID INT AUTO_INCREMENT PRIMARY KEY,"
                    + "Fecha DATETIME,"
                    + "Total DECIMAL(10,2))";
            
            stmt.execute(sql);
        }
    }
    
    @BeforeEach
    public void setUp() {
        ventas = new VentaDAO(conexionDB);
    }

    @Test
    public void testObtener() {
        System.out.println("Test de VentaDAO.obtener()");
        
        Venta venta = new Venta(Date.valueOf("2024-10-24"), 100.00f);
        Venta vInsertada = ventas.insertar(venta);
        
        Venta vObtenida = ventas.obtener(vInsertada.getId());
        assertNotNull(vObtenida, "La venta no debe ser nula");
        assertEquals(100.00f, vObtenida.getTotal(), "El total de la venta no coincide");
    }

    @Test
    public void testInsertar() {
        System.out.println("Test de VentaDAO.insertar()");
        
        Venta venta = new Venta(Date.valueOf("2024-10-17"), 125.00f);
        Venta vInsertada = ventas.insertar(venta);
        
        assertNotNull(vInsertada.getId(), "El ID de la venta no debe ser nulo");
    }

    @Test
    public void testEliminar() {
        System.out.println("Test de VentaDAO.eliminar()");
        
        Venta venta = new Venta(Date.valueOf("2024-10-10"), 150.00f);
        Venta vInsertada = ventas.insertar(venta);
        
        Venta vEliminada = ventas.eliminar(vInsertada.getId());
        assertNotNull(vEliminada, "La venta eliminada no debe ser nula");
        assertThrows(DAOException.class, () -> ventas.obtener(vInsertada.getId()));
    }

    @Test
    public void testConsultarLista() {
        System.out.println("Test de VentaDAO.consultarLista()");
        
        ventas.insertar(new Venta(Date.valueOf("2024-10-17"), 125.00f));
        ventas.insertar(new Venta(Date.valueOf("2024-10-10"), 150.00f));
        
        List<Venta> lventas = ventas.consultarLista();
        assertTrue(lventas.size() >= 2, "La lista de ventas debe tener al menos 2 ventas");
    }
    
}
