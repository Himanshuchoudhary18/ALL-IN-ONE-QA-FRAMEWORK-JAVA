package utilsDatabase;

import com.jcraft.jsch.JSchException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import utilities.Base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

import static org.testng.AssertJUnit.assertNotNull;

@Slf4j
public class ConnectionManagerMySQL {
    private static final int CONNECTION_TIMEOUT = 30000;
    private static final int MAX_CONNECTION_RETRIES = 3;
    private static final int CONNECTION_RETRY_DELAY_MS = 1000;
    public static Connection dbConnection;
    @Setter
    private static String dbHost;
    @Setter
    private static String dbUser;
    @Setter
    private static String dbPassword;
    @Setter
    private static String dbName;
    @Setter
    private static int dbPort = 3306;
    @Setter
    private static Boolean isLocalRun;


    private static void connectToMySQLDatabase() throws JSchException {
        connectToMySQLDatabase(dbUser, dbHost, dbPassword, dbName);
    }

    private static void connectToMySQLDatabase(String dbUser, String dbHost, String dbPassword, String dbName) throws JSchException {
        boolean connected = false;
        int assignedPort = dbPort;
        String jdbcHost = "127.0.0.1";
        String jdbcUrl = "jdbc:mysql://" + dbUser + ":" + dbPassword + "@" + jdbcHost + ":" + assignedPort + "/" + dbName + "?serverTimezone=UTC&autoReconnect=true&useSSL=false";
        int retryCount = 0;
        while (!connected && retryCount < MAX_CONNECTION_RETRIES) {
            try {
                DriverManager.setLoginTimeout(CONNECTION_TIMEOUT / 1000); // set connection timeout
                dbConnection = DriverManager.getConnection(jdbcUrl);
                connected = true;
                Base.logger.info("{} DB Connection successful", dbName);
            } catch (SQLException e) {
                retryCount++;
                Base.logger.info("{} Database connection failed on attempt {}, will retry...", dbName, retryCount);
            }
        }
        if (!connected) {
            Base.logger.info("Failed to establish database connection after multiple retries!");
        }
    }

    private static void verifyDatabaseConnection() {
        assertNotNull("Database connection should not be null.", dbConnection);
    }

    public static void connectToDatabaseMySQL() throws JSchException {
        connectToMySQLDatabase();
        verifyDatabaseConnection();
    }

    public static void closeConnectionDatabaseMySQL() {
        try {
            if (!Objects.isNull(dbConnection)) {
                dbConnection.close();
                Base.logger.info("DB Connection closed successfully");
            } else {
                Base.logger.info("DB Connection is already closed");
            }
        } catch (SQLException e) {
            Base.logger.error("Error occurred while closing the DB connection: {}", e.getMessage(), e);
        }
    }

}
