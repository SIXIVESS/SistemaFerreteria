package dominio;

import java.util.Objects;

/**
 * Contiene los atributos y métodos de acceso de los detalles de una venta.
 * @author Samuel Vega
 */
public class DetalleVenta {
    private Integer id;
    private Integer id_venta;
    private Integer id_producto;
    private Integer cantidad;
    private Float precio_unitario;

    // Constructor vacío
    public DetalleVenta() { }

    /**
     * Constructor que inicializa los atributos de la clase menos el ID.
     * @param id_venta ID de la venta.
     * @param id_producto ID del producto en la venta.
     * @param cantidad Cantidad de unidades del producto en la venta.
     * @param precio_unitario Precio unitario del producto.
     */
    public DetalleVenta(Integer id_venta, Integer id_producto, Integer cantidad, Float precio_unitario) {
        this.id_venta = id_venta;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    /**
     * Inicializa todos los atributos de la clase.
     * @param id ID del detalle de la venta.
     * @param id_venta ID de la venta.
     * @param id_producto ID del producto en la venta.
     * @param cantidad Cantidad de unidades del producto en la venta.
     * @param precio_unitario Precio unitario del producto.
     */
    public DetalleVenta(Integer id, Integer id_venta, Integer id_producto, Integer cantidad, Float precio_unitario) {
        this.id = id;
        this.id_venta = id_venta;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_venta() {
        return id_venta;
    }

    public void setId_venta(Integer id_venta) {
        this.id_venta = id_venta;
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final DetalleVenta other = (DetalleVenta) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "DetalleVenta{" + "id=" + id + ", id_venta=" + id_venta + ", id_producto=" + id_producto + ", cantidad=" + cantidad + ", precio_unitario=" + precio_unitario + '}';
    }

}
