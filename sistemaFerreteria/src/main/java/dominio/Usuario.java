package dominio;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Usuario {

    private Integer id;
    private String nombreUsuario;
    private String contrasena;
    private String fechaRegistro;

    public Usuario() {
    }

    public Usuario(Integer id, String nombreUsuario, String contrasena, String fechaRegistro) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = hashearContrasena(contrasena);
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario(Integer id, String nombreUsuario, String contrasena) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public Usuario(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = hashearContrasena(contrasena);
    
    }

    public Usuario(String nombreUsuario, String contrasena, String fechaRegistro) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = hashearContrasena(contrasena);
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = hashearContrasena(contrasena); // Actualizar la contraseña hasheada
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    // Método para hashear la contraseña
    private String hashearContrasena(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(contrasena.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Usuario{"
                + "id=" + id
                + ", nombreUsuario='" + nombreUsuario + '\''
                + ", fechaRegistro='" + fechaRegistro + '\''
                + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) obj;
        return Objects.equals(nombreUsuario, usuario.nombreUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreUsuario);
    }
}
