package interfaces;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interfaz que establece los métodos para establecer conexión con la base de datos.
 * @author Samuel Vega
 */
public interface IConexionDB {
    
    /**
     * Crea la conexión a una base de datos.
     * @return Conexión a una base de datos.
     * @throws SQLException Si la conexión falla.
     */
    Connection crearConexion() throws SQLException;
}
