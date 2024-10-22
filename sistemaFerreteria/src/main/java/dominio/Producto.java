package dominio;

import java.util.Objects;

/**
 * Contiene los atributos y métodos de acceso de un producto.
 * @author Samuel Vega
 */
public class Producto {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Float precio;
    private Integer stock;
    private Integer id_categoria;

    // Constructor vacío
    public Producto() {}

    /**
     * Constructor que inicializa los atributos de la clase, menos el ID del producto.
     * @param nombre Nombre del producto.
     * @param descripcion Descripción del producto.
     * @param precio Precio del producto.
     * @param stock Stock del producto.
     * @param id_categoria ID de la categoría a la que pertenece el producto.
     */
    public Producto(String nombre, String descripcion, Float precio, Integer stock, Integer id_categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.id_categoria = id_categoria;
    }

    /**
     * Constructor que inicializa todos los atributos de la clase.
     * @param id ID del producto.
     * @param nombre Nombre del producto.
     * @param descripcion Descripción del producto.
     * @param precio Precio del producto.
     * @param stock Stock del producto.
     * @param id_categoria ID de la categoría a la que pertenece el producto.
     */
    public Producto(Integer id, String nombre, String descripcion, Float precio, Integer stock, Integer id_categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.id_categoria = id_categoria;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Productos{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + ", stock=" + stock + ", id_categoria=" + id_categoria + '}';
    }

}
