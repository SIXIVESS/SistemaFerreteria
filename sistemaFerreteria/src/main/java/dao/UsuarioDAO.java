/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dominio.Usuario;
import excepciones.DAOException;
import interfaces.IConexionDB;
import interfaces.IUsuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chaly
 */
public class UsuarioDAO implements IUsuarioDAO {

    private static final Logger LOG = Logger.getLogger(UsuarioDAO.class.getName());
    private final IConexionDB MANAGER;

    public UsuarioDAO(IConexionDB manejador) {
        this.MANAGER = manejador;
    }
    
    /**
     *
     * @param nombreUsuario
     * @return
     * @throws SQLException
     */
    @Override
     public Usuario obtenerUsuarioPorNombre(String nombreUsuario) throws DAOException {
            String query = "SELECT UsuarioID, NombreUsuario, Contrasena FROM Usuarios WHERE NombreUsuario = ?";

        try (PreparedStatement stmt = MANAGER.crearConexion().prepareStatement(query)) {
            stmt.setString(1, nombreUsuario);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("UsuarioID")); // Usa "UsuarioID" en lugar de "id"
                usuario.setNombreUsuario(rs.getString("NombreUsuario"));
                usuario.setContrasena(rs.getString("Contrasena"));
                return usuario;
            }
        }
    }   catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return null;
    }


    @Override
    public Usuario obtener(Integer id) throws DAOException {
        try (
                Connection conexion = MANAGER.crearConexion(); PreparedStatement comando = conexion.prepareStatement("SELECT * FROM usuarios WHERE UsuarioID = ?");) {
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();
            Usuario usuario = null;

            if (resultado.next()) {
                String nombreUsuario = resultado.getString("NombreUsuario");
                String contrasena = resultado.getString("Contrasena");
                String fechaRegistro = resultado.getString("FechaRegistro");

                usuario = new Usuario(id, nombreUsuario, contrasena, fechaRegistro);
            } else {
                throw new DAOException("No se pudo obtener el usuario con ID: " + id);
            }

            return usuario;
        } catch (SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo obtener el usuario: {0}", sqle.getMessage());
            throw new DAOException("No se pudo obtener el usuario: " + sqle.getMessage());
        }
    }

    @Override
    public Usuario insertar(Usuario usuario) throws DAOException {
        String sql = "INSERT INTO usuarios(NombreUsuario, Contrasena) VALUES(?, ?)";
        try (Connection conexion = MANAGER.crearConexion(); PreparedStatement comando = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            comando.setString(1, usuario.getNombreUsuario());
            comando.setString(2, usuario.getContrasena());
            comando.executeUpdate();

            try (ResultSet registroLlaves = comando.getGeneratedKeys()) {
                if (registroLlaves.next()) {
                    usuario.setId(registroLlaves.getInt(1));
                    return usuario;
                } else {
                    LOG.log(Level.SEVERE, "No se mostró el ID");
                    throw new DAOException("No se mostró el ID");
                }
            }
        } catch (SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo insertar el usuario: {0}", sqle.getMessage());
            throw new DAOException("Error al insertar el usuario: " + sqle.getMessage());
        }
    }

    @Override
    public Usuario eliminar(Integer id) throws DAOException {
        Usuario usuario = this.obtener(id);
        String sql = "DELETE FROM usuarios WHERE UsuarioID = ?";

        try (
                Connection conexion = MANAGER.crearConexion(); PreparedStatement comando = conexion.prepareStatement(sql);) {
            comando.setInt(1, id);
            int afectadas = comando.executeUpdate();

            if (afectadas > 0) {
                return usuario;
            } else {
                throw new DAOException("No se pudo eliminar el usuario con ID: " + id);
            }
        } catch (SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo eliminar el usuario: {0}", sqle.getMessage());
            throw new DAOException("No se pudo eliminar el usuario: " + sqle.getMessage());
        }
    }

    @Override
    public List<Usuario> consultarLista() throws DAOException {
        String sql = "SELECT UsuarioID, NombreUsuario, Contrasena, FechaRegistro FROM usuarios";
        List<Usuario> listaUsuarios = new LinkedList<>();

        try (
                Connection conexion = MANAGER.crearConexion(); PreparedStatement comando = conexion.prepareStatement(sql);) {
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Integer id = resultado.getInt("UsuarioID");
                String nombreUsuario = resultado.getString("NombreUsuario");
                String contrasena = resultado.getString("Contrasena");
                String fechaRegistro = resultado.getString("FechaRegistro");
                Usuario usuario = new Usuario(id, nombreUsuario, contrasena, fechaRegistro);

                listaUsuarios.add(usuario);
            }

            return listaUsuarios;
        } catch (SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo consultar la lista de usuarios: {0}", sqle.getMessage());
            throw new DAOException("No se pudo consultar la lista de usuarios: " + sqle.getMessage());
        }
    }

    public String actualizar(String contrasena, int id) throws DAOException {
        try (
                Connection conexion = MANAGER.crearConexion(); PreparedStatement comando = conexion.prepareStatement("UPDATE usuarios SET Contrasena = ? WHERE UsuarioID = ?");) {
            comando.setString(1, contrasena);
            comando.setInt(2, id);

            int afectadas = comando.executeUpdate();

            if (afectadas > 0) {
                return "La contraseña del usuario fue actualizada correctamente!";
            } else {
                throw new DAOException("No se pudo actualizar el usuario con ID: " + id);
            }
        } catch (SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo actualizar el usuario: {0}", sqle.getMessage());
            throw new DAOException("No se pudo actualizar el usuario: " + sqle.getMessage());
        }
    }

    @Override
    public boolean validarUsuario(String nombreUsuario, String contrasena) throws DAOException {
        String query = "SELECT COUNT(*) FROM usuarios WHERE NombreUsuario = ? AND contrasena = ?"; // Asegúrate de que los nombres son correctos
        try (Connection conexion = MANAGER.crearConexion(); PreparedStatement comando = conexion.prepareStatement(query)) {

            comando.setString(1, nombreUsuario); // Asegúrate de que este argumento corresponde con la variable que usas en el formulario
            comando.setString(2, contrasena);

            ResultSet rs = comando.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true si hay al menos un usuario que coincide
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error al validar usuario: {0}", e.getMessage());
            throw new DAOException("Error al validar usuario: " + e.getMessage());
        }
        return false; // Por defecto, retornar false
    }

}
