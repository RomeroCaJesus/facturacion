package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class conMySQL {

    static conMySQL instance = null;
    Connection connection = null;

    public conMySQL() throws Exception {
        String url = "jdbc:mysql://localhost:3306/facturacion";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, "root", "");
    }

    public static conMySQL getInstance() throws Exception {
        if (instance == null) {
            instance = new conMySQL();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    protected void finalize() throws Throwable {
        connection.close();
        connection=null;
        super.finalize();
    }
}
