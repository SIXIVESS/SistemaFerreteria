/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dominio.Usuario;
import excepciones.DAOException;
import interfaces.IConexionDB;
import interfaces.IUsuarioDAO;
import java.sql.*;
import java.util.List;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDAOIT {

    private static IConexionDB conexionDB;
    private IUsuarioDAO usuarios;

    @BeforeAll
    public static void setUpClass() throws Exception {
        conexionDB = new IConexionDB() {
            @Override
            public Connection crearConexion() throws SQLException {
                return DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "root", "");
            }
        };

        try (
                Connection conexion = conexionDB.crearConexion(); Statement stmt = conexion.createStatement();) {
            String sql = ""
                    + "CREATE TABLE Usuarios ("
                    + "UsuarioID INT AUTO_INCREMENT PRIMARY KEY,"
                    + "NombreUsuario VARCHAR(50) NOT NULL UNIQUE,"
                    + "Contrasena VARCHAR(255) NOT NULL,"
                    + "FechaRegistro DATETIME DEFAULT CURRENT_TIMESTAMP)";

            stmt.execute(sql);
        }
    }

    @BeforeEach
    public void setUp() throws DAOException {
        usuarios = new UsuarioDAO(conexionDB);

        // Buscar el ID del usuario con el nombre "carlos"
        Usuario usuario = usuarios.obtenerUsuarioPorNombre("carlos");
        if (usuario != null) {
            // Eliminar el usuario por su ID
            usuarios.eliminar(usuario.getId());
        }

        // Insertar de nuevo el usuario "carlos"
        usuarios.insertar(new Usuario("carlos", "password"));
    }

    @Test
    public void testObtenerUsuarioPorNombre() {
        System.out.println("Test de UsuarioDAO.obtenerUsuarioPorNombre()");
        Usuario usuario = new Usuario("pepe", "contrasena123");
        Usuario uInsertado = usuarios.insertar(usuario);

        Usuario uObtenido = usuarios.obtenerUsuarioPorNombre("pepe");
        assertNotNull(uObtenido, "El usuario no debe ser nulo");
        assertEquals("pepe", uObtenido.getNombreUsuario(), "El nombre del usuario no coincide");
    }

    @Test
    public void testInsertar() {
        System.out.println("Test de UsuarioDAO.insertar()");

        Usuario usuario = new Usuario("maria", "contrasena456");
        Usuario uInsertado = usuarios.insertar(usuario);

        assertNotNull(uInsertado.getId(), "El ID del usuario no debe ser nulo");
    }

//    @Test
//public void testEliminar() {
//    System.out.println("Test de UsuarioDAO.eliminar()");
//
//    Usuario usuario = new Usuario("juan", "contrasena789");
//    Usuario uInsertado = usuarios.insertar(usuario);
//
//    Integer usuarioId = uInsertado.getId();
//
//    Usuario uEliminado = usuarios.eliminar(usuarioId);
//    
//    // Verificar que el usuario fue eliminado
//    assertNotNull(uEliminado, "El usuario eliminado no debe ser nulo");
//    
//    // Verificar que el usuario ya no existe en la base de datos
//    assertThrows(DAOException.class, () -> usuarios.obtenerUsuarioPorNombre("juan"));
//}

    @Test
    public void testConsultarLista() {
        System.out.println("Test de UsuarioDAO.consultarLista()");

        usuarios.insertar(new Usuario("pedro", "contrasena001"));
        usuarios.insertar(new Usuario("luis", "contrasena002"));

        List<Usuario> lUsuarios = usuarios.consultarLista();
        assertTrue(lUsuarios.size() >= 2, "La lista de usuarios debe tener al menos 2 usuarios");
    }

//    @Test
//    public void testValidarUsuario() {
//        System.out.println("Test de UsuarioDAO.validarUsuario()");
//
//        // Verifica que el usuario existente "carlos" con la contraseña "contrasena123" sea válido
//        boolean esValido = usuarios.validarUsuario("carlos", "contrasena123");
//        assertTrue(esValido, "La validación del usuario debe ser exitosa");
//
//        // Verifica que una contraseña incorrecta para "carlos" falle
//        boolean esInvalido = usuarios.validarUsuario("carlos", "contrasenaIncorrecta");
//        assertFalse(esInvalido, "La validación del usuario debe fallar con contraseña incorrecta");
//    }
}
