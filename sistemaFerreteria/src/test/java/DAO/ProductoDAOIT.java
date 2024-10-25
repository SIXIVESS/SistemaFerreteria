package DAO;

import dominio.Producto;
import excepciones.DAOException;
import interfaces.IConexionDB;
import interfaces.IProductoDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoDAOIT {
    private static IConexionDB conexionDB;
    private IProductoDAO productos;
    
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
                    + "CREATE TABLE productos("
                    + "ProductoID INT AUTO_INCREMENT PRIMARY KEY,"
                    + "Nombre VARCHAR(100),"
                    + "Descripcion TEXT,"
                    + "Precio DECIMAL(10,2),"
                    + "Stock INT,"
                    + "CategoriaID INT)";
            
            stmt.execute(sql);
        }
    }
    
    @BeforeEach
    public void setUp() {
        productos = new ProductoDAO(conexionDB);
    }

    @Test
    public void testObtener() {
        System.out.println("Test de ProductoDAO.obtener()");
        
        Producto producto = new Producto("Taladro", "Descripcion sobre taladro", 100.00f, 1, 1);
        Producto pInsertado = productos.insertar(producto);
        
        Producto pObtenido = productos.obtener(pInsertado.getId());
        assertNotNull(pObtenido, "El producto no debe ser nulo");
        assertEquals("Taladro", pObtenido.getNombre(), "El nombre del producto no coincide");
    }

    @Test
    public void testInsertar() {
        System.out.println("Test de ProductoDAO.insertar()");
        
        Producto producto = new Producto("Linterna", "Descripcion sobre linterna", 125.00f, 1, 1);
        Producto pInsertado = productos.insertar(producto);
        
        assertNotNull(pInsertado.getId(), "El ID del producto no debe ser nulo");
    }

    @Test
    public void testEliminar() {
        System.out.println("Test de ProductoDAO.eliminar()");
        
        Producto producto = new Producto("Pintura", "Descripcion sobre pintura", 85.00f, 10, 1);
        Producto pInsertado = productos.insertar(producto);
        
        Producto pEliminado = productos.eliminar(pInsertado.getId());
        assertNotNull(pEliminado, "El producto eliminado no debe ser nulo");
        assertThrows(DAOException.class, () -> productos.obtener(pInsertado.getId()));
    }

    @Test
    public void testConsultarLista() {
        System.out.println("Test de ProductoDAO.consultarLista()");
        
        productos.insertar(new Producto("Taladro", "Descripcion sobre taladro", 100.00f, 1, 1));
        productos.insertar(new Producto("Linterna", "Descripcion sobre linterna", 125.00f, 1, 1));
        
        List<Producto> lproductos = productos.consultarLista();
        assertTrue(lproductos.size() >= 2, "La lista de productos debe tener al menos 2 productos");
    }
    
}
