package interfaces;

import dominio.Venta;
import excepciones.DAOException;
import java.util.List;

/**
 * Interfaz que establece los métodos para interactuar con una venta de la base de datos.
 * @author Samuel Vega
 */
public interface IVentaDAO {
    
    /**
     * Obtiene los datos de una venta de la base de datos por su ID.
     * @param id ID de la venta a obtener.
     * @return Los datos de la venta con el ID del parámetro.
     * @throws DAOException Si no se encuentra la venta.
     */
    Venta obtener(Integer id) throws DAOException;
    
    /**
     * Inserta una venta en la base de datos.
     * @param venta venta a insertar en la base de datos.
     * @return La venta que se insertó.
     * @throws DAOException Si no se logra insertar la venta.
     */
    Venta insertar(Venta venta) throws DAOException;
    
    /**
     * Elimina una venta según su ID.
     * @param id ID de la venta a eliminar.
     * @return El venta que fue eliminada.
     * @throws DAOException Si no se logra eliminar la venta.
     */
    Venta eliminar(Integer id) throws DAOException;
    
    /**
     * Regresa la lista de todas las ventas existentes en la base de datos.
     * @return La lista de todas las ventas.
     * @throws DAOException Si no se encuentran todas las ventas.
     */
    List<Venta> consultarLista() throws DAOException;
}
