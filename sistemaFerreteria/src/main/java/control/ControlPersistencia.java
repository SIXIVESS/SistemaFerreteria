package control;

import dao.CategoriaDAO;
import dao.ProductoDAO;
import dao.UsuarioDAO;
import db.ConexionDB;
import dominio.Categoria;
import dominio.Producto;
import dominio.Usuario;
import excepciones.DAOException;
import util.HashUtil;
import interfaces.ICategoriaDAO;
import interfaces.IConexionDB;
import interfaces.IProductoDAO;
import interfaces.IUsuarioDAO;
import java.sql.SQLException;
import java.util.List;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * Lleva el control de las funciones de persistencia del sistema. Controla las
 * operaciones relacionadas con Usuarios, Productos y Categorías.
 */
public class ControlPersistencia {

    private final IConexionDB conexion;
    private final IUsuarioDAO usuarioDAO;
    private final IProductoDAO productoDAO;
    private final ICategoriaDAO categoriaDAO;

    // Constructor que inicializa conectándose a la base de datos.
    public ControlPersistencia() {
        this.conexion = ConexionDB.getInstance();
        this.usuarioDAO = new UsuarioDAO(conexion);
        this.productoDAO = new ProductoDAO(conexion);
        this.categoriaDAO = new CategoriaDAO(conexion);
    }

    public IConexionDB getConexion() {
        return this.conexion;
    }

    /**
     * Agrega un producto a la base de datos que se define en la ventana de
     * agregar productos.
     *
     * @param producto Producto a agregar en la base de datos.
     */
    public void agregarProducto(Producto producto) {
        try {
            productoDAO.insertar(producto);
        } catch (Exception e) {
            // Manejo de excepciones
            System.err.println("Error al agregar producto: " + e.getMessage());
            // Considera lanzar una excepción personalizada o retornar un mensaje adecuado
        }
    }

    /**
     * Obtiene la lista de categorías existentes desde la base de datos.
     *
     * @return La lista de categorías existentes desde la base de datos.
     */
    public List<Categoria> obtenerListaCategorias() {
        try {
            return categoriaDAO.consultarLista();
        } catch (Exception e) {
            // Manejo de excepciones
            System.err.println("Error al obtener categorías: " + e.getMessage());
            return List.of(); // Retorna una lista vacía en caso de error
        }
    }

    /**
     * Obtiene una categoría por su ID.
     *
     * @param id ID de la categoría a obtener.
     * @return Categoría correspondiente al ID.
     */
    public Categoria obtenerCategoria(int id) {
        try {
            return categoriaDAO.obtener(id);
        } catch (Exception e) {
            System.err.println("Error al obtener categoría: " + e.getMessage());
            return null; // Considera lanzar una excepción personalizada
        }
    }

    /**
     * Obtiene la lista de productos existentes desde la base de datos.
     *
     * @return La lista de productos existentes desde la base de datos.
     */
    public List<Producto> obtenerListaProductos() {
        try {
            return productoDAO.consultarLista();
        } catch (Exception e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
            return List.of(); // Retorna una lista vacía en caso de error
        }
    }
    
    public List<Producto> obtenerListaProductosPorNombre(String nombre) {
        try {
            return productoDAO.consultarPorNombre(nombre);
        } catch (Exception e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
            return List.of(); // Retorna una lista vacía en caso de error
        }
    }

    /**
     *
     * @return
     */
    public List<Producto> obtenerListaProductosBajoStock() {
        try {
            return productoDAO.consultarListaBajoStock();
        } catch (Exception e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
            return List.of(); // Retorna una lista vacía en caso de error
        }
    }

    /**
     * Actualiza el stock de un producto.
     *
     * @param stock Nuevo stock del producto.
     * @param id ID del producto a actualizar.
     * @return Mensaje de confirmación.
     */
    public String actualizarProducto(int stock, int id) {
        try {
            return productoDAO.actualizar(stock, id);
        } catch (Exception e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
            return "Error al actualizar producto"; // Mensaje por defecto
        }
    }

    public void registrarNuevoUsuario(String nombre, String contrasena) {
        // Crear nuevo usuario
        Usuario nuevoUsuario = new Usuario(nombre, contrasena);

        try {
            // Usar el método insertar de usuarioDAO
            usuarioDAO.insertar(nuevoUsuario);
            System.out.println("Usuario registrado con ID: " + nuevoUsuario.getId());
        } catch (DAOException e) {
            // Manejar cualquier excepción que pueda surgir al insertar el usuario
            System.err.println("Error al registrar el usuario: " + e.getMessage());
        }
    }

    /**
     * Actualiza la contraseña de un usuario.
     *
     * @param contrasena Nueva contraseña del usuario.
     * @param id ID del usuario a actualizar.
     * @return Mensaje de confirmación.
     */
    public String actualizarUsuario(String contrasena, int id) {
        try {
            return usuarioDAO.actualizar(contrasena, id);
        } catch (Exception e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return "Error al actualizar usuario"; // Mensaje por defecto
        }
    }

    public boolean validarUsuario(String nombreUsuario, String contrasena) {
        Usuario usuarioBD = usuarioDAO.obtenerUsuarioPorNombre(nombreUsuario);
        if (usuarioBD == null) {
            return false; // Usuario no encontrado
        }

        // Hashear la contraseña ingresada
        String contrasenaHasheada = HashUtil.hashPassword(contrasena);
        return contrasenaHasheada.equals(usuarioBD.getContrasena());
    }
    
    public JasperPrint reporteMasVendido(String inicio, String fin) throws SQLException{
        return productoDAO.reporteMasVendidos(inicio,fin);
    }
}
