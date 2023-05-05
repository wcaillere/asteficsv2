package connection;

import java.sql.Connection;

public interface IConnection {

    /**
     * Crée et retourne une connection JDBC
     *
     * @return une connection JDBC
     */
    Connection getConnection();
}
