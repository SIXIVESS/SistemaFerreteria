package dominio;

import java.util.Objects;

/**
 * Contiene los atributos y métodos de acceso de una categoría.
 * @author Samuel Vega
 */
public class Categoria {
    private Integer id;
    private String nombre;

    // Constructor vacío.
    public Categoria() { }

    /**
     * Constructor que inicializa los atributos de la clase menos el ID.
     * @param nombre Nombre de la categoría.
     */
    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Constructor que inicializa los atributos de la clase.
     * @param id ID de la categoría.
     * @param nombre Nombre de la categoría.
     */
    public Categoria(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Categoria other = (Categoria) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return nombre;
    }

}
