package control;

import dao.CategoriaDAO;
import dao.ProductoDAO;
import dao.UsuarioDAO;
import db.ConexionDB;
import dominio.Categoria;
import dominio.Producto;
import interfaces.ICategoriaDAO;
import interfaces.IConexionDB;
import interfaces.IProductoDAO;
import interfaces.IUsuarioDAO;
import java.util.List;

/**
 * Lleva el control de las funciones de persistencia del sistema.
 *
 * @author Samuel Vega
 */
public class ControlPersistencia {

    private final IConexionDB conexion;
    private final IUsuarioDAO usuarioDAO;

    // Constructor que inicializa conectandose a la base de datos.
    public ControlPersistencia() {
        this.conexion = ConexionDB.getInstance();
        this.usuarioDAO = new UsuarioDAO(conexion);
    }

    /**
     * Agrega un producto a la base de datos que se define en la ventana de
     * agregar productos.
     *
     * @param producto Producto a agregar en la base de datos.
     */
    public void agregarProducto(Producto producto) {
        IProductoDAO productos = new ProductoDAO(conexion);

        productos.insertar(producto);
    }

    /**
     * Obtiene la lista de categorías existentes desde la base de datos.
     *
     * @return La lista de categorías existentes desde la base de datos.
     */
    public List<Categoria> obtenerListaCategorias() {
        ICategoriaDAO categorias = new CategoriaDAO(conexion);

        return categorias.consultarLista();
    }

    /**
     *
     * @param id
     * @return
     */
    public Categoria obtenerCategoria(int id) {
        ICategoriaDAO categorias = new CategoriaDAO(conexion);
        return categorias.obtener(id);
    }

    /**
     * Obtiene la lista de productos existentes desde la base de datos.
     *
     * @return La lista de productos existentes desde la base de datos.
     */
    public List<Producto> obtenerListaProductos() {
        IProductoDAO productos = new ProductoDAO(conexion);
        return productos.consultarLista();
    }

    /**
     * obtiene mensaje de confirmacion, actualizacion realiazda
     *
     * @param stock
     * @param id
     * @return
     */
    public String actualizarProducto(int stock, int id) {
        IProductoDAO productos = new ProductoDAO(conexion);
        return productos.actualizar(stock, id);

    }

    public String actualizarUsuario(String contrasena, int id) {
        return usuarioDAO.actualizar(contrasena, id);
    }

    public boolean validarUsuario(String usuario, String contrasena) {
        IUsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
        // Aquí deberías implementar la lógica para validar el usuario en la base de datos.
        return usuarioDAO.validar(usuario, contrasena); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
