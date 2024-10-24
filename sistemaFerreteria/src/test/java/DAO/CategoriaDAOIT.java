package DAO;

import dominio.Categoria;
import excepciones.DAOException;
import interfaces.ICategoriaDAO;
import interfaces.IConexionDB;
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

public class CategoriaDAOIT {
    private static IConexionDB conexionDB;
    private ICategoriaDAO categorias;
    
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
                    + "CREATE TABLE categorias("
                    + "CategoriaID INT AUTO_INCREMENT PRIMARY KEY,"
                    + "Nombre VARCHAR(100))";
            
            stmt.execute(sql);
        }
    }
    
    @BeforeEach
    public void setUp() {
        categorias = new CategoriaDAO(conexionDB);
    }

    @Test
    public void testObtener() {
        System.out.println("Test de CategoriaDAO.obtener()");
        
        Categoria categoria = new Categoria("Tuberias");
        Categoria cInsertada = categorias.insertar(categoria);
        
        Categoria cObtenida = categorias.obtener(cInsertada.getId());
        assertNotNull(cObtenida, "La categoría no debe ser nula");
        assertEquals("Tuberias", cObtenida.getNombre(), "El nombre de la categoría no coincide");
    }

    @Test
    public void testInsertar() {
        System.out.println("Test de CategoriaDAO.insertar()");
        
        Categoria categoria = new Categoria("Herramientas");
        Categoria cInsertada = categorias.insertar(categoria);
        
        assertNotNull(cInsertada.getId(), "El ID de la categoría no debe ser nulo");
    }

    @Test
    public void testEliminar() {
        System.out.println("Test de CategoriaDAO.eliminar()");
        
        Categoria categoria = new Categoria("Tuercas");
        Categoria cInsertada = categorias.insertar(categoria);
        
        Categoria cEliminada = categorias.eliminar(cInsertada.getId());
        assertNotNull(cEliminada, "La categoría eliminada no debe ser nula");
        assertThrows(DAOException.class, () -> categorias.obtener(cInsertada.getId()));
    }

    @Test
    public void testConsultarLista() {
        System.out.println("Test de CategoriaDAO.consultarLista()");
        
        categorias.insertar(new Categoria("Herramientas"));
        categorias.insertar(new Categoria("Tuercas"));
        
        List<Categoria> lcategorias = categorias.consultarLista();
        assertTrue(lcategorias.size() >= 2, "La lista de categorías debe tener al menos 2 categorías");
    }
    
}
