package DAO;

import dominio.DetalleVenta;
import excepciones.DAOException;
import interfaces.IConexionDB;
import interfaces.IDetalleVentaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DetalleVentaDAOIT {
    private static IConexionDB conexionDB;
    private IDetalleVentaDAO detalles;
    
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
                    + "CREATE TABLE detalleventas("
                    + "DetalleID INT AUTO_INCREMENT PRIMARY KEY,"
                    + "VentaID INT,"
                    + "ProductoID INT,"
                    + "Cantidad INT,"
                    + "PrecioUnitario DECIMAL(10,2))";
            
            stmt.execute(sql);
        }
    }
    
    @BeforeEach
    public void setUp() {
        detalles = new DetalleVentaDAO(conexionDB);
    }

    @Test
    public void testObtener() {
        System.out.println("Test de DetalleVentaDAO.obtener()");
        
        DetalleVenta detalle = new DetalleVenta(1, 1, 3, 150.00f);
        DetalleVenta dInsertado = detalles.insertar(detalle);
        
        DetalleVenta dObtenido = detalles.obtener(dInsertado.getId());
        assertNotNull(dObtenido, "El detalle de venta no debe ser nulo");
        assertEquals(3, dObtenido.getCantidad(), "La cantidad en el detalle de venta no coincide");
    }

    @Test
    public void testInsertar() {
        System.out.println("Test de DetalleVentaDAO.insertar()");
        
        DetalleVenta detalle = new DetalleVenta(1, 2, 5, 500.00f);
        DetalleVenta dInsertado = detalles.insertar(detalle);
        
        assertNotNull(dInsertado.getId(), "El ID del detalle de venta no debe ser nulo");
    }

    @Test
    public void testEliminar() {
        System.out.println("Test de DetalleVentaDAO.eliminar()");
        
        DetalleVenta detalle = new DetalleVenta(1, 3, 2, 50.00f);
        DetalleVenta dInsertado = detalles.insertar(detalle);
        
        DetalleVenta dEliminado = detalles.eliminar(dInsertado.getId());
        assertNotNull(dEliminado, "El detalle de venta eliminado no debe ser nulo");
        assertThrows(DAOException.class, () -> detalles.obtener(dInsertado.getId()));
    }

    @Test
    public void testConsultarLista() {
        System.out.println("Test de DetalleVentaDAO.consultarLista()");
        
        detalles.insertar(new DetalleVenta(1, 3, 2, 50.00f));
        detalles.insertar(new DetalleVenta(1, 1, 3, 150.00f));
        
        List<DetalleVenta> ldetalles = detalles.consultarLista();
        assertTrue(ldetalles.size() >= 2, "La lista de detalles de ventas debe tener al menos 2 detalles");
    }
    
}
