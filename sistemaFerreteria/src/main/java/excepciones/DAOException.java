package excepciones;

/**
 * Muestra excepciones sobre los DAO del proyecto.
 * @author Samuel Vega
 */
public class DAOException extends RuntimeException {
    
    // Constructor vacío
    public DAOException() { }
    
    /**
     * Constructor que define el mensaje de la excepción.
     * @param msg Mensaje de la excepción.
     */
    public DAOException(String msg) {
        super(msg);
    }
    
    /**
     * Constructor que define el mensaje y la causa de la excepción.
     * @param msg Mensaje de la excepión.
     * @param cause Causa de la excepción.
     */
    public DAOException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
    /**
     * Constructor que define la causa de la excepción.
     * @param cause Causa de la excepción.
     */
    public DAOException(Throwable cause) {
        super(cause);
    }
}
