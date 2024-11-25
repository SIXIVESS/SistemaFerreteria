package dao;

import dominio.Producto;
import excepciones.DAOException;
import interfaces.IConexionDB;
import interfaces.IProductoDAO;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Contiene las operaciones de persistencia con los productos de la base de datos.
 * @author Samuel Vega
 */
public class ProductoDAO implements IProductoDAO {
    private static final Logger LOG = Logger.getLogger(ProductoDAO.class.getName());
    private final IConexionDB MANAGER;
    
    /**
     * Constructo de la clase que inicializa el manejador de la base de datos.
     * @param manejador Manejador de la base de datos.
     */
    public ProductoDAO(IConexionDB manejador) {
        this.MANAGER = manejador;
    }
    
    /**
     * Obtiene los datos de un producto de la base de datos por su ID.
     * @param id ID del producto a obtener.
     * @return Los datos del producto con el ID del parámetro.
     * @throws DAOException Si no se encuentra el producto.
     */
    @Override
    public Producto obtener(Integer id) throws DAOException {
        try(
            Connection conexion = MANAGER.crearConexion();
            PreparedStatement comando = conexion.prepareStatement("select * from productos where ProductoID = ?");
        ) {
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();
            Producto producto = null;
            
            if(resultado.next()) {
                String nombre = resultado.getString("Nombre");
                String descripcion = resultado.getString("Descripcion");
                Float precio = resultado.getFloat("Precio");
                Integer stock = resultado.getInt("Stock");
                Integer id_categoria = resultado.getInt("CategoriaID");
                
                producto = new Producto(nombre, descripcion, precio, stock, id_categoria);
            }else {
                throw new DAOException("No se pudo obtener el producto con ID: " + id);
            }
            
            return producto;
        } catch(SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo obtener el producto" + "{0}", sqle.getMessage());
            throw new DAOException("No se pudo obtener el producto" + sqle.getMessage());
        }
    }

    /**
     * Inserta un producto en la base de datos.
     * @param producto Producto a insertar en la base de datos.
     * @return El producto que se insertó.
     * @throws DAOException Si no se logra insertar el producto.
     */
    @Override
    public Producto insertar(Producto producto) throws DAOException {
        String sql = "insert into productos(Nombre, Descripcion, Precio, Stock, CategoriaID)"
                   + "values(?, ?, ?, ?, ?)";
        
        try(
            Connection conexion = MANAGER.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ){
            comando.setString(1, producto.getNombre());
            comando.setString(2, producto.getDescripcion());
            comando.setFloat(3, producto.getPrecio());
            comando.setInt(4, producto.getStock());
            comando.setInt(5, producto.getId_categoria());
            comando.executeUpdate();
            
            ResultSet registroLlaves = comando.getGeneratedKeys();
            
            if(registroLlaves.next()) {
                Integer id = registroLlaves.getInt(Statement.RETURN_GENERATED_KEYS);
                producto.setId(id);
                return producto;
            }else {
                LOG.log(Level.SEVERE, "No se mostró el ID");
                throw new DAOException("No se mostró el ID");
            }
        } catch(SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo insertar el producto" + "{0}", sqle.getMessage());
            throw new DAOException("No se pudo insertar el producto" + sqle.getMessage());
        }
    }

    /**
     * Elimina un producto según su ID.
     * @param id ID del producto a eliminar.
     * @return El producto que fue eliminado.
     * @throws DAOException Si no se logra eliminar el producto.
     */
    @Override
    public Producto eliminar(Integer id) throws DAOException {
        Producto producto = this.obtener(id);
        String sql = "delete from productos where ProductoID = ?";
        
        try(
            Connection conexion = MANAGER.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(sql);
        ) {
            comando.setInt(1, id);
            
            int afectadas = comando.executeUpdate();
            
            if(afectadas > 0) {
                return producto;
            }else {
                throw new DAOException("No se pudo eliminar el producto con ID: " + id);
            }
        } catch(SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo eliminar el producto" + "{0}", sqle.getMessage());
            throw new DAOException("No se pudo eliminar el producto" + sqle.getMessage());
        }
    }

    /**
     * Regresa la lista de todos los productos existentes en la base de datos.
     * @return La lista de todos los productos.
     * @throws DAOException Si no se encuentran todos los productos.
     */
    @Override
    public List<Producto> consultarLista() throws DAOException {
        String sql = "select ProductoID, Nombre, Descripcion, Precio, Stock, CategoriaID from productos";
        List<Producto> listaProductos = new LinkedList<>();
        
        try(
            Connection conexion = MANAGER.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(sql);
        ) {
            ResultSet resultado = comando.executeQuery();
            
            while(resultado.next()) {
                Integer id = resultado.getInt("ProductoID");
                String nombre = resultado.getString("Nombre");
                String descripcion = resultado.getString("Descripcion");
                Float precio = resultado.getFloat("Precio");
                Integer stock = resultado.getInt("Stock");
                Integer id_categoria = resultado.getInt("CategoriaID");
                Producto producto = new Producto(id, nombre, descripcion, precio, stock, id_categoria);
                
                listaProductos.add(producto);
            }
            
            return listaProductos;
        } catch(SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo consultar la lista de productos" + "{0}", sqle.getMessage());
            throw new DAOException("No se pudo consultar la lista de productos" + sqle.getMessage());
        }
    }
  /**
   * Regresa mensaje de confirmacion, producto actualizado
   * @param stock
   * @param id
   * @return
   * @throws DAOException 
   */
    @Override
    public String actualizar(int stock, int id) throws DAOException{
        try(
            Connection conexion = MANAGER.crearConexion();
            PreparedStatement comando = conexion.prepareStatement("update productos set Stock = ? where ProductoID = ?");
        ) {
            comando.setInt(1,stock);
            comando.setInt(2,id);
            
            int afectadas = comando.executeUpdate();
            
            if(afectadas > 0) {
                return "El producto gue actualizado correctamente!";
            }else {
                throw new DAOException("No se pudo actualizar el producto con ID: " + id);
            }
        } catch(SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo actualizar el producto" + "{0}", sqle.getMessage());
            throw new DAOException("No se pudo actualizar el producto" + sqle.getMessage());
        } 
        
    }
    @Override
     public List<Producto> consultarListaBajoStock() throws DAOException {
        String sql = "select *  from productos where stock <= 3;";
        List<Producto> listaProductos = new LinkedList<>();
        
        try(
            Connection conexion = MANAGER.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(sql);
        ) {
            ResultSet resultado = comando.executeQuery();
            
            while(resultado.next()) {
                Integer id = resultado.getInt("ProductoID");
                String nombre = resultado.getString("Nombre");
                String descripcion = resultado.getString("Descripcion");
                Float precio = resultado.getFloat("Precio");
                Integer stock = resultado.getInt("Stock");
                Integer id_categoria = resultado.getInt("CategoriaID");
                Producto producto = new Producto(id, nombre, descripcion, precio, stock, id_categoria);
                
                listaProductos.add(producto);
            }
            
            return listaProductos;
        } catch(SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo consultar la lista de productos" + "{0}", sqle.getMessage());
            throw new DAOException("No se pudo consultar la lista de productos" + sqle.getMessage());
        }
    }
     
     /**
      * Genera un reporte de los productos mas vendidos en un rango de fechas
      * @param inicio
      * @param fin
      * @return jasperPrint
      * @throws SQLException 
      */
    @Override
     public JasperPrint reporteMasVendidos(String inicio, String fin) throws SQLException{
         Map<String, Object> parametros = new HashMap<>();
         String sql = "SELECT ferreteria.productos.`ProductoID`,\n" +
                "	ferreteria.productos.`Nombre`,\n" +
                "	SUM( ferreteria.detalleventas.`Cantidad`) AS Vendido,\n" +
                "	ferreteria.ventas.`Fecha`\n" +
                "FROM ferreteria.detalleventas\n" +
                "	JOIN ferreteria.ventas  ON \n" +
                "	 ferreteria.detalleventas.`VentaID` = ferreteria.ventas.`VentaID` \n" +
                "	JOIN ferreteria.productos  ON \n" +
                "	 ferreteria.detalleventas.`ProductoID` = ferreteria.productos.`ProductoID` \n" +
                "WHERE \n" +
                "	ferreteria.ventas.`Fecha` BETWEEN  ? AND ? \n" +
                "GROUP BY ferreteria.productos.`ProductoID`,\n" +
                "	ferreteria.productos.`Nombre`, ferreteria.ventas.`Fecha`\n" +
                "ORDER BY Vendido DESC";
         String logoPath = this.getClass().getResource("/Reporte/logo.png").getPath();
         parametros.put("logoPath", logoPath);
         File reporte = new File(getClass().getResource("/Reporte/ferreteriaReporte.jasper").getFile());
         if(!reporte.exists()){
            return null;
         }
        try {        
            Connection conexion = MANAGER.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(sql);
            comando.setString(1, inicio);
            comando.setString(2, fin);
            ResultSet rs = comando.executeQuery();
            InputStream is = new BufferedInputStream(new FileInputStream(reporte.getAbsoluteFile()));

             try {
                  JRResultSetDataSource jrrs = new JRResultSetDataSource(rs);
                  JasperReport jr = (JasperReport) JRLoader.loadObject(is);
                  JasperPrint jp = JasperFillManager.fillReport(jr, parametros, jrrs);
                  return jp;

             } catch (JRException ex) {
                 Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
             }
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
    
}
