package utilsDatabase;

import lombok.Setter;
import redis.clients.jedis.Jedis;
import utilities.Base;

public class ConnectionManagerRedis {
    public static Jedis jedis;
    @Setter
    private static String dbUser;
    @Setter
    private static String dbPassword;
    @Setter
    private static int dbPort;
    @Setter
    private static Boolean isLocalRun;
    @Setter
    private static String dbHost;

    public static void connectToDatabaseRedis() {
        String connectionString = "redis://" + dbHost + ":" + dbPort;
        // Connect to Redis
        try {
            jedis = new Jedis(connectionString);
            jedis.auth(dbUser, dbPassword);
            Base.logger.info("Connected to Redis successfully!");
        } catch (Exception e) {
            Base.logger.error("Error connecting to Redis: {}", e.getMessage());
        }
    }

    public static void closeConnectionRedis() {
        try {
            if (jedis != null) {
                jedis.close();
                Base.logger.info("Closed the connection to Redis Database");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
