package db;

import interfaces.IConexionDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB implements IConexionDB {
    private static ConexionDB instance;
    private String CONEXION;
    private String USUARIO;
    private String PASSWORD;

    private ConexionDB(String conexion, String usuario, String password) {
        this.CONEXION = conexion;
        this.USUARIO = usuario;
        this.PASSWORD = password;
    }

    public static ConexionDB getInstance() {
        if (instance == null) {
            instance = new ConexionDB("jdbc:mysql://localhost/ferreteria", "root", "1234");
        }
        return instance;
    }

    @Override
    public Connection crearConexion() throws SQLException {
        return DriverManager.getConnection(CONEXION, USUARIO, PASSWORD);
    }
}
