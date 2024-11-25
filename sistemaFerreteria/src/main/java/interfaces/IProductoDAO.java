package interfaces;

import dominio.Producto;
import excepciones.DAOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import net.sf.jasperreports.engine.JasperPrint;

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
    
    /**
     * Regresa la lista de todos los productos existentes en la base de datos con el nombre del parámetro.
     * @param nombre Nombre del producto que se está buscando.
     * @return Lista de productos que coinciden con el nombre puesto.
     * @throws DAOException Si no se encuentran los prodcutos.
     */
    List<Producto> consultarPorNombre(String nombre) throws DAOException;
    
    /**
     * Actualiza el producto seleccionado
     * @param stock
     * @param id
     * @return la fila actualizada
     * @throws DAOException 
     */
    String actualizar(int stock, int id) throws DAOException;
    /**
     * 
     * @return lista de productos con stock bajo
     * @throws DAOException 
     */
    public List<Producto> consultarListaBajoStock()throws DAOException;
    /**
     * Generador de Reporte Jasper Report
     * @return
     * @throws java.sql.SQLException
     * @throws DAOException 
     */
    public JasperPrint reporteMasVendidos(String inicio, String fin)throws SQLException;
}
