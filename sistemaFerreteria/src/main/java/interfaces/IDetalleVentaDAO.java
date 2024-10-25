package interfaces;

import dominio.DetalleVenta;
import excepciones.DAOException;
import java.util.List;

/**
 * Interfaz que establece los métodos para interactuar con un detalle de venta de la base de datos.
 * @author Samuel Vega
 */
public interface IDetalleVentaDAO {
        
    /**
     * Obtiene los datos de un detalle de venta de la base de datos por su ID.
     * @param id ID del detalle de venta a obtener.
     * @return Los datos del detalle de venta con el ID del parámetro.
     * @throws DAOException Si no se encuentra el detalle de venta.
     */
    DetalleVenta obtener(Integer id) throws DAOException;
    
    /**
     * Inserta un detalle de venta en la base de datos.
     * @param detalle Detalle de venta a insertar en la base de datos.
     * @return El detalle de venta que se insertó.
     * @throws DAOException Si no se logra insertar el detalle de venta.
     */
    DetalleVenta insertar(DetalleVenta detalle) throws DAOException;
    
    /**
     * Elimina un detalle de venta según su ID.
     * @param id ID del detalle de venta a eliminar.
     * @return El detalle de venta que fue eliminado.
     * @throws DAOException Si no se logra eliminar el detalle de venta.
     */
    DetalleVenta eliminar(Integer id) throws DAOException;
    
    /**
     * Regresa la lista de todos los detalles de venta existentes en la base de datos.
     * @return La lista de todos los detalles de venta.
     * @throws DAOException Si no se encuentran todos los detalles de venta.
     */
    List<DetalleVenta> consultarLista() throws DAOException;
}
