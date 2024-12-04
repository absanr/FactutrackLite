
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DERICK ALEXIS
 */

public class ConexionSQL {
    // Datos de conexión
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/bdprueba2?characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Obtiene una nueva conexión a la base de datos.
     *
     * @return Una conexión válida a la base de datos.
     */
    public static Connection getConexion() {
        try {
            Class.forName(DRIVER); // Cargar el driver
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver: " + e.getMessage());
            throw new RuntimeException("Error al cargar el driver", e);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            throw new RuntimeException("Error de conexión a la base de datos", e);
        }
    }

    /**
     * Cierra la conexión proporcionada.
     *
     * @param connection La conexión que se desea cerrar.
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}


