package control;

import DAO.CategoriaDAO;
import DAO.ProductoDAO;
import db.ConexionDB;
import dominio.Categoria;
import dominio.Producto;
import interfaces.ICategoriaDAO;
import interfaces.IConexionDB;
import interfaces.IProductoDAO;
import java.util.List;

/**
 * Lleva el control de las funciones del sistema.
 * @author Samuel Vega
 */
public class Control {
    private IConexionDB conexion;
    
    public Control() {
        this.conexion = new ConexionDB("jdbc:mysql://localhost/ferreteria", "root", "");
    }
    
    /**
     * Agrega un producto a la base de datos que se define en la ventana de agregar productos.
     * @param producto Producto a agregar en la base de datos.
     */
    public void agregarProducto(Producto producto) {
        IProductoDAO productos = new ProductoDAO(conexion);
        
        productos.insertar(producto);
    }
    
    public List<Categoria> obtenerListaCategorias() {
        ICategoriaDAO categorias = new CategoriaDAO(conexion);
        
        return categorias.consultarLista();
    }
}
