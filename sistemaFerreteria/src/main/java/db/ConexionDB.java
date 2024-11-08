package db;

import interfaces.IConexionDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Establece una conexión con la base de datos utilizando el patrón Singleton
 *
 */
public class ConexionDB implements IConexionDB {

    private static ConexionDB instance;
    private String CONEXION;
    private String USUARIO;
    private String PASSWORD;

    /**
     * Constructor que inicializa los atributos de conexión.
     *
     * @param conexion URL para la conexión con la base de datos.
     * @param usuario Nombre de usuario para conectarse a la base de datos.
     * @param password Contraseña del usuario.
     */
    private ConexionDB(String conexion, String usuario, String password) {
        this.CONEXION = conexion;
        this.USUARIO = usuario;
        this.PASSWORD = password;
    }

    /**
     * Método que devuelve la instancia de ConexionDB. Si la instancia no ha
     * sido creada, se inicializa con los parámetros definidos.
     *
     * @return Instancia única de ConexionDB.
     */
    public static ConexionDB getInstance() {
        if (instance == null) {
            instance = new ConexionDB("jdbc:mysql://localhost/ferreteria", "root", "1234");
        }
        return instance;
    }

    /**
     * Crea y devuelve la conexión a la base de datos utilizando los parámetros
     * especificados.
     *
     * @return Conexión a la base de datos.
     * @throws SQLException Lanza una excepción si ocurre un error al intentar
     * establecer una conexión.
     */
    @Override
    public Connection crearConexion() throws SQLException {
        return DriverManager.getConnection(CONEXION, USUARIO, PASSWORD);
    }
}
