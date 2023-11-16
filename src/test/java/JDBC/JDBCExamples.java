package JDBC;

import utilities.OracleDBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExamples {
    public static void main(String[] args) {
        String CreateTableQuery = " CREATE TABLE customers121(" +
                "customer_id NUMBER Generated ALWAYS as IDENTITY PRIMARY KEY, " +
                "customer_name VARCHAR(30)," +
                "email VARCHAR(30)," +
                "phone_number VARCHAR(15))";
        createTableStatement(CreateTableQuery);
    }

    public static void createTableStatement(String query){
        Connection connection = OracleDBConnection.getCONNECTION();
        try {
            Statement statement = connection.createStatement();
            boolean execute = statement.execute(query);
            System.out.println(execute);
            System.out.println("Table was create");
        }catch (SQLException | NullPointerException e){
            System.out.println("Failed to create a Statement");
            e.printStackTrace();
        }
    }


}
