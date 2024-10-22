package DAO;

import dominio.Categoria;
import excepciones.DAOException;
import interfaces.ICategoriaDAO;
import interfaces.IConexionDB;
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
 * Contiene las operaciones de persistencia con las categorías de la base de datos.
 * @author Samuel Vega
 */
public class CategoriaDAO implements ICategoriaDAO {
    private static final Logger LOG = Logger.getLogger(ProductoDAO.class.getName());
    private final IConexionDB MANAGER;
    
    /**
     * Constructo de la clase que inicializa el manejador de la base de datos.
     * @param manejador Manejador de la base de datos.
     */
    public CategoriaDAO(IConexionDB manejador) {
        this.MANAGER = manejador;
    }
    
    /**
     * Obtiene los datos de una categoría de la base de datos por su ID.
     * @param id ID de la categoría a obtener.
     * @return Los datos del categoría con el ID del parámetro.
     * @throws DAOException Si no se encuentra la categoría.
     */
    @Override
    public Categoria obtener(Integer id) throws DAOException {
        try(
            Connection conexion = MANAGER.crearConexion();
            PreparedStatement comando = conexion.prepareStatement("select * from categorias where CategoriaID = ?");
        ) {
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();
            Categoria categoria = null;
            
            if(resultado.next()) {
                String nombre = resultado.getString("Nombre");
                
                categoria = new Categoria(nombre);
            }
            
            return categoria;
        } catch(SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo obtener la categoría" + "{0}", sqle.getMessage());
            throw new DAOException("No se pudo obtener la categoría" + sqle.getMessage());
        }
    }

    /**
     * Inserta una categoría en la base de datos.
     * @param categoria Categoría a insertar en la base de datos.
     * @return La categoría que se insertó.
     * @throws DAOException Si no se logra insertar la categoría.
     */
    @Override
    public Categoria insertar(Categoria categoria) throws DAOException {
        String sql = "insert into categorias(Nombre)"
                   + "values(?)";
        
        try(
            Connection conexion = MANAGER.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ){
            comando.setString(1, categoria.getNombre());
            comando.executeUpdate();
            
            ResultSet registroLlaves = comando.getGeneratedKeys();
            
            if(registroLlaves.next()) {
                Integer id = registroLlaves.getInt(Statement.RETURN_GENERATED_KEYS);
                categoria.setId(id);
                return categoria;
            }else {
                LOG.log(Level.SEVERE, "No se mostró el ID");
                throw new DAOException("No se mostró el ID");
            }
        } catch(SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo insertar la categoría" + "{0}", sqle.getMessage());
            throw new DAOException("No se pudo insertar la categoría" + sqle.getMessage());
        }
    }

    /**
     * Elimina una categoría según su ID.
     * @param id ID de la categoría a eliminar.
     * @return La categoría que fue eliminada.
     * @throws DAOException Si no se logra eliminar la categoría.
     */
    @Override
    public Categoria eliminar(Integer id) throws DAOException {
        Categoria categoria = this.obtener(id);
        String sql = "delete from categorias where CategoriaID = ?";
        
        try(
            Connection conexion = MANAGER.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(sql);
        ) {
            comando.setInt(1, id);
            
            boolean eliminacion = comando.execute();
            
            return eliminacion ? categoria : null;
        } catch(SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo eliminar la categoría" + "{0}", sqle.getMessage());
            throw new DAOException("No se pudo eliminar la categoría" + sqle.getMessage());
        }
    }

    /**
     * Regresa la lista de todas las categorías existentes en la base de datos.
     * @return La lista de todas las categorías.
     * @throws DAOException Si no se encuentran todas las categorías.
     */
    @Override
    public List<Categoria> consultarLista() throws DAOException {
        String sql = "select CategoriaID, Nombre from categorias";
        List<Categoria> listaCategorias = new LinkedList<>();
        
        try(
            Connection conexion = MANAGER.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(sql);
        ) {
            ResultSet resultado = comando.executeQuery();
            
            while(resultado.next()) {
                Integer id = resultado.getInt("CategoriaID");
                String nombre = resultado.getString("Nombre");
                Categoria categoria = new Categoria(id, nombre);
                
                listaCategorias.add(categoria);
            }
            
            return listaCategorias;
        } catch(SQLException sqle) {
            LOG.log(Level.SEVERE, "No se pudo consultar la lista de categorías" + "{0}", sqle.getMessage());
            throw new DAOException("No se pudo consultar la lista de categorías" + sqle.getMessage());
        }
    }

}
