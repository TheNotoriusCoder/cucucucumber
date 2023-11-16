package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class OracleDBConnection {

    private static final String JDBC_URL = Config.getProperty("jdbc_url");
    private static final String USERNAME = Config.getProperty("db_username");
    private static final String PASSWORD = Config.getProperty("db_password");
    private static  final Logger LOGGER = Logger.getLogger(OracleDBConnection.class.getName());
    private static Connection CONNECTION ;

    public static Connection getCONNECTION(){
        LOGGER.info("Connecting to Database");

        try{
            if(CONNECTION == null){
                CONNECTION = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                LOGGER.info("Creating a connection to DataBase");
                return CONNECTION;
            }
        }catch (SQLException e){
            System.out.println("Faild to connect to DB!");
            LOGGER.warning("Faild to connect to DB!");
        }
        LOGGER.info("Returning existing connection");
        return CONNECTION;
    }
}
