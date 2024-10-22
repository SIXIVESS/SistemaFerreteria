package DAO;

import dominio.Producto;
import excepciones.DAOException;
import interfaces.IConexionDB;
import interfaces.IProductoDAO;
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
            PreparedStatement comando = conexion.prepareStatement("select * from productos where id = ?");
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
            
            boolean eliminacion = comando.execute();
            
            return eliminacion ? producto : null;
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
    
}
