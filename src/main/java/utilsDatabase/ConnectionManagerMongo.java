package utilsDatabase;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import utilities.Base;

import java.util.Objects;

public class ConnectionManagerMongo {
    private static final ThreadLocal<String> dbHost = new ThreadLocal<>();
    private static final ThreadLocal<String> dbUser = new ThreadLocal<>();
    private static final ThreadLocal<String> dbPassword = new ThreadLocal<>();
    private static final ThreadLocal<String> dbName = new ThreadLocal<>();
    private static final ThreadLocal<Integer> dbPort = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> isLocalRun = new ThreadLocal<>();
    private static final ThreadLocal<MongoClient> mongoClient = new ThreadLocal<>();
    private static final ThreadLocal<MongoDatabase> database = new ThreadLocal<>();


    public static void connectToDatabaseMongo() {
        String connectionString = "mongodb://" + dbUser.get() + ":" + dbPassword.get() + "@" + dbHost.get() + ":" + dbPort.get() + "/" + dbName.get();
        try {
            mongoClient.set(new MongoClient(new MongoClientURI(connectionString)));
            database.set(mongoClient.get().getDatabase(dbName.get()));
            Base.logger.info("Connected to MongoDB successfully!");
        } catch (Exception e) {
            Base.logger.error("Error connecting to Mongo Database: ", e);
        }
    }

    public static void closeMongoConnection() {
        try {
            if (!Objects.isNull(mongoClient.get())) {
                mongoClient.get().close();
                Base.logger.info("Closed the Mongo database connection");
            }
        } catch (Exception e) {
            Base.logger.warn("Error occurred while closing the Mongo database connection: ", e);
        }
    }
}
