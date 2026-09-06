package utilsDatabase;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import utilities.Base;

public class ConnectionManagerMongo {

    private static MongoClient mongoClient;
    private static MongoDatabase database;
    public static void connectToDatabaseMongo()
    {
        try {
            String uri = System.getenv("MONGODB_URI");
            String dbName = System.getenv("MONGODB_DATABASE");

            if (uri == null || uri.isBlank() || dbName == null || dbName.isBlank()) {
                throw new IllegalStateException("Set MONGODB_URI and MONGODB_DATABASE before connecting.");
            }

            mongoClient = MongoClients.create(uri);
            database = mongoClient.getDatabase(dbName);
            Base.logger.info(" Connected to MongoDB database: " + dbName);
        }
        catch (Exception e)
        {
            Base.logger.error(" Error connecting to MongoDB: ", e);
        }
    }

    public static MongoDatabase getDatabase()
    {
        return database;
    }

    public static void closeMongoConnection() {
        try {
            if (mongoClient != null) {
                mongoClient.close();
                Base.logger.info("MongoDB connection closed");
            }
        } catch (Exception e) {
            Base.logger.warn("Error while closing MongoDB connection: ", e);
        }
    }
}