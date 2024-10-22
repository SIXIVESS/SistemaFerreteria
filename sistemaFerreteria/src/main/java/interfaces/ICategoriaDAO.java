package interfaces;

import dominio.Categoria;
import excepciones.DAOException;
import java.util.List;

/**
 * Interfaz que establece los métodos para interactuar con una categoría de la base de datos.
 * @author Samuel Vega
 */
public interface ICategoriaDAO {
    
    /**
     * Obtiene los datos de una categoría de la base de datos por su ID.
     * @param id ID de la categoría a obtener.
     * @return Los datos del categoría con el ID del parámetro.
     * @throws DAOException Si no se encuentra la categoría.
     */
    Categoria obtener(Integer id) throws DAOException;
    
    /**
     * Inserta una categoría en la base de datos.
     * @param categoria Categoría a insertar en la base de datos.
     * @return La categoría que se insertó.
     * @throws DAOException Si no se logra insertar la categoría.
     */
    Categoria insertar(Categoria categoria) throws DAOException;
    
    /**
     * Elimina una categoría según su ID.
     * @param id ID de la categoría a eliminar.
     * @return El categoría que fue eliminada.
     * @throws DAOException Si no se logra eliminar la categoría.
     */
    Categoria eliminar(Integer id) throws DAOException;
    
    /**
     * Regresa la lista de todas las categorías existentes en la base de datos.
     * @return La lista de todas las categorías.
     * @throws DAOException Si no se encuentran todas las categorías.
     */
    List<Categoria> consultarLista() throws DAOException;
}
