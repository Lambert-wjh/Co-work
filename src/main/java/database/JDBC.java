package database;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JDBC {
    private DataSource data_source;
    private static final String JDBC_URL = "jdbc:mysql://localhost/HeadhunterMS";
    private static final String JDBC_USER = "jdbc";
    private static final String JDBC_PASSWORD = "jdbcpassword";
    private static final String CONNECTION_TIMEOUT = "2000";
    private static final String IDLE_TIMEOUT = "120000";
    private static final String MAX_POOL_SIZE = "15";
    private static final JDBC INSTANCE = new JDBC();

    private JDBC() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JDBC_URL);
        config.setUsername(JDBC_USER);
        config.setPassword(JDBC_PASSWORD);
        config.addDataSourceProperty("connectionTimeout", CONNECTION_TIMEOUT);
        config.addDataSourceProperty("idleTimeout", IDLE_TIMEOUT);
        config.addDataSourceProperty("maximumPoolSize", MAX_POOL_SIZE);
        data_source = new HikariDataSource(config);
    }

    public static JDBC getJDBC() {
        return INSTANCE;
    }

    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = data_source.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
