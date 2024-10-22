package db;

import static com.google.protobuf.JavaFeaturesProto.java;
import interfaces.IConexionDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Establece la conexión con la base de datos.
 * @author Samuel Vega
 */
public class ConexionDB implements IConexionDB {
    private String CONEXION;
    private String USUARIO;
    private String PASSWORD;

    /**
     * Constructor que inicializa los atributos de la clase correspondientes a la conexión.
     * @param conexion Base de datos a la que conectarse.
     * @param usuario Usuario que quiere conectarse.
     * @param password Contraseña del usuario correpondiente.
     */
    public ConexionDB(String conexion, String usuario, String password) {
        this.CONEXION = conexion;
        this.USUARIO = usuario;
        this.PASSWORD = password;
    }
    
    /**
     * Crea una conexión hacia la base de datos establecida.
     * @return Conexión con la base de datos establecida.
     * @throws SQLException Si la conexión falla.
     */
    @Override
    public Connection crearConexion() throws SQLException {
        Connection conexion = DriverManager.getConnection(CONEXION, USUARIO, PASSWORD);
        return conexion;
    }
}
