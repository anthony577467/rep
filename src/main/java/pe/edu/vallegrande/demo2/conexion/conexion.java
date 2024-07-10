package pe.edu.vallegrande.demo2.conexion;







import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=PRUEBA11;encrypt=false;trustServerCertificate=true;";
    private static final String USER = "SA";
    private static final String PASSWORD = "SQL#12345";


    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            // Cargar el driver JDBC
            String url = "jdbc:sqlserver://<server>:<port>;databaseName=<database>;user=<user>;password=<password>;encrypt=false;trustServerCertificate=true;";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Establecer la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.err.println("No se pudo encontrar el driver JDBC: " + e.getMessage());
            throw new SQLException("Driver JDBC no encontrado.", e);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            throw e;
        }
        return connection;
    }



}
