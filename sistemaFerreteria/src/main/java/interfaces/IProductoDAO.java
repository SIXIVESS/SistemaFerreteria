package interfaces;

import dominio.Producto;
import excepciones.DAOException;
import java.util.List;

/**
 * Interfaz que establece los métodos para interactuar con un producto de la base de datos.
 * @author Samuel Vega
 */
public interface IProductoDAO {
    
    /**
     * Obtiene los datos de un producto de la base de datos por su ID.
     * @param id ID del producto a obtener.
     * @return Los datos del producto con el ID del parámetro.
     * @throws DAOException Si no se encuentra el producto.
     */
    Producto obtener(Integer id) throws DAOException;
    
    /**
     * Inserta un producto en la base de datos.
     * @param producto Producto a insertar en la base de datos.
     * @return El producto que se insertó.
     * @throws DAOException Si no se logra insertar el producto.
     */
    Producto insertar(Producto producto) throws DAOException;
    
    /**
     * Elimina un producto según su ID.
     * @param id ID del producto a eliminar.
     * @return El producto que fue eliminado.
     * @throws DAOException Si no se logra eliminar el producto.
     */
    Producto eliminar(Integer id) throws DAOException;
    
    /**
     * Regresa la lista de todos los productos existentes en la base de datos.
     * @return La lista de todos los productos.
     * @throws DAOException Si no se encuentran todos los productos.
     */
    List<Producto> consultarLista() throws DAOException;
}
