package DAO;

import dominio.Venta;
import excepciones.DAOException;
import interfaces.IConexionDB;
import interfaces.IVentaDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contiene las operaciones de persistencia con las ventas de la base de datos.
 * @author Samuel Vega
 */
public class VentaDAO implements IVentaDAO {
    private static final Logger LOG = Logger.getLogger(ProductoDAO.class.getName());
    private final IConexionDB MANAGER;
    
    /**
     * Constructo de la clase que inicializa el manejador de la base de datos.
     * @param manejador Manejador de la base de datos.
     */
    public VentaDAO(IConexionDB manejador) {
        this.MANAGER = manejador;
    }
    
    /**
     * Obtiene los datos de una venta de la base de datos por su ID.
     * @param id ID de la venta a obtener.
     * @return Los datos del venta con el ID del parámetro.
     * @throws DAOException Si no se encuentra la venta.
     */
    @Override
    public Venta obtener(Integer id) throws DAOException {
        try(
            Connection conexion = MANAGER.crearConexion();
            PreparedStatement comando = conexion.prepareStatement("select * from ventas where VentaID = ?");
        ) {
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();
            Venta venta = null;
            
            if(resultado.next()) {
                Date fecha = resultado.getDate("Fecha");
                Float total = resultado.getFloat("Total");
                
                venta = new Venta(fecha, total);
            }else {
                throw new DAOException("No se pudo obtener la venta con ID: " + id);
            }
            
            return venta;
        } catch(SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo obtener la venta" + "{0}", sqle.getMessage());
            throw new DAOException("No se pudo obtener la venta" + sqle.getMessage());
        }
    }

    /**
     * Inserta una venta en la base de datos.
     * @param venta Venta a insertar en la base de datos.
     * @return La venta que se insertó.
     * @throws DAOException Si no se logra insertar la venta.
     */
    @Override
    public Venta insertar(Venta venta) throws DAOException {
        String sql = "insert into ventas(Fecha, Total)"
                   + "values(?, ?)";
        
        try(
            Connection conexion = MANAGER.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ){
            comando.setDate(1, venta.getFecha());
            comando.setFloat(2, venta.getTotal());
            comando.executeUpdate();
            
            ResultSet registroLlaves = comando.getGeneratedKeys();
            
            if(registroLlaves.next()) {
                Integer id = registroLlaves.getInt(Statement.RETURN_GENERATED_KEYS);
                venta.setId(id);
                return venta;
            }else {
                LOG.log(Level.SEVERE, "No se mostró el ID");
                throw new DAOException("No se mostró el ID");
            }
        } catch(SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo insertar la venta" + "{0}", sqle.getMessage());
            throw new DAOException("No se pudo insertar la venta" + sqle.getMessage());
        }
    }

    /**
     * Elimina una venta según su ID.
     * @param id ID de la venta a eliminar.
     * @return La venta que fue eliminada.
     * @throws DAOException Si no se logra eliminar la venta.
     */
    @Override
    public Venta eliminar(Integer id) throws DAOException {
        Venta venta = this.obtener(id);
        String sql = "delete from ventas where VentaID = ?";
        
        try(
            Connection conexion = MANAGER.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(sql);
        ) {
            comando.setInt(1, id);
            
            int afectadas = comando.executeUpdate();
            
            if(afectadas > 0) {
                return venta;
            }else {
                throw new DAOException("No se pudo eliminar la venta con ID: " + id);
            }
        } catch(SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo eliminar la venta" + "{0}", sqle.getMessage());
            throw new DAOException("No se pudo eliminar la venta" + sqle.getMessage());
        }
    }

    /**
     * Regresa la lista de todas las ventas existentes en la base de datos.
     * @return La lista de todas las ventas.
     * @throws DAOException Si no se encuentran todas las ventas.
     */
    @Override
    public List<Venta> consultarLista() throws DAOException {
        String sql = "select VentaID, Fecha, Total from ventas";
        List<Venta> listaVentas = new LinkedList<>();
        
        try(
            Connection conexion = MANAGER.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(sql);
        ) {
            ResultSet resultado = comando.executeQuery();
            
            while(resultado.next()) {
                Integer id = resultado.getInt("VentaID");
                Date fecha = resultado.getDate("Fecha");
                Float total = resultado.getFloat("Total");
                Venta venta = new Venta(id, fecha, total);
                
                listaVentas.add(venta);
            }
            
            return listaVentas;
        } catch(SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo consultar la lista de ventas" + "{0}", sqle.getMessage());
            throw new DAOException("No se pudo consultar la lista de ventas" + sqle.getMessage());
        }
    }

}
