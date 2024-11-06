/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dominio.Usuario;
import excepciones.DAOException;
import java.util.List;

/**
 *
 * @author chaly
 */
public interface IUsuarioDAO {

    /**
     * Obtiene los datos de un usuario de la base de datos por su ID.
     *
     * @param id ID del usuario a obtener.
     * @return Los datos del usuario con el ID del parámetro.
     * @throws DAOException Si no se encuentra el usuario.
     */
    Usuario obtener(Integer id) throws DAOException;

    /**
     * Inserta un usuario en la base de datos.
     *
     * @param usuario Usuario a insertar en la base de datos.
     * @return El usuario que se insertó.
     * @throws DAOException Si no se logra insertar el usuario.
     */
    Usuario insertar(Usuario usuario) throws DAOException;

    /**
     * Elimina un usuario según su ID.
     *
     * @param id ID del usuario a eliminar.
     * @return El usuario que fue eliminado.
     * @throws DAOException Si no se logra eliminar el usuario.
     */
    Usuario eliminar(Integer id) throws DAOException;

    /**
     * Regresa la lista de todos los usuarios existentes en la base de datos.
     *
     * @return La lista de todos los usuarios.
     * @throws DAOException Si no se encuentran todos los usuarios.
     */
    List<Usuario> consultarLista() throws DAOException;

    /**
     * Actualiza los datos del usuario seleccionado.
     *
     * @param usuario El usuario con los datos actualizados.
     * @return Mensaje de confirmación de que el usuario fue actualizado.
     * @throws DAOException Si no se logra actualizar el usuario.
     */
String actualizar(String contrasena, int id) throws DAOException; 

Usuario obtenerUsuarioPorNombre(String nombreUsuario);

boolean validarUsuario(String nombreUsuario, String contrasena) throws DAOException;

   
}
