package dominio;

import java.sql.Date;
import java.util.Objects;

/**
 * Contiene los atributos y métodos de acceso de una venta.
 * @author Samuel Vega
 */
public class Venta {
    private Integer id;
    private Date fecha;
    private Float total;

    // Constructor vacío
    public Venta() { }

    /**
     * Constructor de la clase sin ID.
     * @param fecha Fecha en que se realizó la venta.
     * @param total Total de la venta.
     */
    public Venta(Date fecha, Float total) {
        this.fecha = fecha;
        this.total = total;
    }

    /**
     * Constructor que inicializa los atributos de la clase.
     * @param id ID de la venta.
     * @param fecha Fecha en que se realizó la venta.
     * @param total Total de la venta.
     */
    public Venta(Integer id, Date fecha, Float total) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.id);
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
        final Venta other = (Venta) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", fecha=" + fecha + ", total=" + total + '}';
    }

}
