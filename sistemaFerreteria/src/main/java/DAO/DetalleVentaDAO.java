package DAO;

import dominio.DetalleVenta;
import dominio.Producto;
import excepciones.DAOException;
import interfaces.IConexionDB;
import interfaces.IDetalleVentaDAO;
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
 * Contiene las operaciones de persistencia con los detalles de venta de la base de datos.
 * @author Samuel Vega
 */
public class DetalleVentaDAO implements IDetalleVentaDAO {
    private static final Logger LOG = Logger.getLogger(ProductoDAO.class.getName());
    private final IConexionDB MANAGER;
    
    /**
     * Constructo de la clase que inicializa el manejador de la base de datos.
     * @param manejador Manejador de la base de datos.
     */
    public DetalleVentaDAO(IConexionDB manejador) {
        this.MANAGER = manejador;
    }
    
    /**
     * Obtiene los datos de un detalle de venta de la base de datos por su ID.
     * @param id ID del detalle de venta a obtener.
     * @return Los datos del detalle de venta con el ID del parámetro.
     * @throws DAOException Si no se encuentra el detalle de venta.
     */
    @Override
    public DetalleVenta obtener(Integer id) throws DAOException {
        try(
            Connection conexion = MANAGER.crearConexion();
            PreparedStatement comando = conexion.prepareStatement("select * from detalleventas where DetalleID = ?");
        ) {
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();
            DetalleVenta detalle = null;
            
            if(resultado.next()) {
                Integer id_venta = resultado.getInt("VentaID");
                Integer id_producto = resultado.getInt("ProductoID");
                Integer cantidad = resultado.getInt("Cantidad");
                Float precio_unitario = resultado.getFloat("PrecioUnitario");
                
                detalle = new DetalleVenta(id_venta, id_producto, cantidad, precio_unitario);
            }
            
            return detalle;
        } catch(SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo obtener el detalle de venta" + "{0}", sqle.getMessage());
            throw new DAOException("No se pudo obtener el detalle de venta" + sqle.getMessage());
        }
    }

    /**
     * Inserta un detalle de venta en la base de datos.
     * @param detalle Detalle de venta a insertar en la base de datos.
     * @return El detalle de venta que se insertó.
     * @throws DAOException Si no se logra insertar el detalle de venta.
     */
    @Override
    public DetalleVenta insertar(DetalleVenta detalle) throws DAOException {
        String sql = "insert into detalleventas(VentaID, ProductoID, Cantidad, PrecioUnitario)"
                   + "values(?, ?, ?, ?)";
        
        try(
            Connection conexion = MANAGER.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ){
            comando.setInt(1, detalle.getId_venta());
            comando.setInt(2, detalle.getId_producto());
            comando.setInt(3, detalle.getCantidad());
            comando.setFloat(4, detalle.getPrecio_unitario());
            comando.executeUpdate();
            
            ResultSet registroLlaves = comando.getGeneratedKeys();
            
            if(registroLlaves.next()) {
                Integer id = registroLlaves.getInt(Statement.RETURN_GENERATED_KEYS);
                detalle.setId(id);
                return detalle;
            }else {
                LOG.log(Level.SEVERE, "No se mostró el ID");
                throw new DAOException("No se mostró el ID");
            }
        } catch(SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo insertar el detalle de venta" + "{0}", sqle.getMessage());
            throw new DAOException("No se pudo insertar el detalle de venta" + sqle.getMessage());
        }
    }

    /**
     * Elimina un detalle de venta según su ID.
     * @param id ID del detalle de venta a eliminar.
     * @return El detalle de venta que fue eliminado.
     * @throws DAOException Si no se logra eliminar el detalle de venta.
     */
    @Override
    public DetalleVenta eliminar(Integer id) throws DAOException {
        DetalleVenta detalle = this.obtener(id);
        String sql = "delete from detalleventas where DetalleID = ?";
        
        try(
            Connection conexion = MANAGER.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(sql);
        ) {
            comando.setInt(1, id);
            
            boolean eliminacion = comando.execute();
            
            return eliminacion ? detalle : null;
        } catch(SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo eliminar el detalle de venta" + "{0}", sqle.getMessage());
            throw new DAOException("No se pudo eliminar el detalle de venta" + sqle.getMessage());
        }
    }

    /**
     * Regresa la lista de todos los detalles de venta existentes en la base de datos.
     * @return La lista de todos los detalles de venta.
     * @throws DAOException Si no se encuentran todos los detalles de venta.
     */
    @Override
    public List<DetalleVenta> consultarLista() throws DAOException {
        String sql = "select DetalleID, VentaID, ProductoID, Cantidad, PrecioUnitario from detalleventas";
        List<DetalleVenta> listaProductos = new LinkedList<>();
        
        try(
            Connection conexion = MANAGER.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(sql);
        ) {
            ResultSet resultado = comando.executeQuery();
            
            while(resultado.next()) {
                Integer id = resultado.getInt("DetalleID");
                Integer id_venta = resultado.getInt("VentaID");
                Integer id_producto = resultado.getInt("ProductoID");
                Integer cantidad = resultado.getInt("Cantidad");
                Float precio_unitario = resultado.getFloat("PrecioUnitario");
                DetalleVenta detalle = new DetalleVenta(id, id_venta, id_producto, cantidad, precio_unitario);
                
                listaProductos.add(detalle);
            }
            
            return listaProductos;
        } catch(SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo consultar la lista de los detalles de venta" + "{0}", sqle.getMessage());
            throw new DAOException("No se pudo consultar la lista de los detalles de venta" + sqle.getMessage());
        }
    }
    
}
