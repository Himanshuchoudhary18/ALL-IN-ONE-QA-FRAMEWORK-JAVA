package utilsDatabase;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import utilities.Base;

import java.util.Objects;

public class ConnectionManagerMongo {
    private static String dbHost;
    private static String dbUser;
    private static String dbPassword;
    private static String dbName;
    private static int dbPort;
    private static Boolean isLocalRun;
    private static MongoClient mongoClient;
    private static MongoDatabase database;


    public static void connectToDatabaseMongo() {
        String connectionString = "mongodb://" + dbUser + ":" + dbPassword + "@" + dbHost + ":" + dbPort + "/" + dbName;
        try {
            mongoClient = new MongoClient(new MongoClientURI(connectionString));
            database = mongoClient.getDatabase(dbName);
            Base.logger.info("Connected to MongoDB successfully!");
        } catch (Exception e) {
            Base.logger.error("Error connecting to Mongo Database: ", e);
        }
    }

    public static void closeMongoConnection() {
        try {
            if (!Objects.isNull(mongoClient)) {
                mongoClient.close();
                Base.logger.info("Closed the Mongo database connection");
            }
        } catch (Exception e) {
            Base.logger.warn("Error occurred while closing the Mongo database connection: ", e);
        }
    }
}
