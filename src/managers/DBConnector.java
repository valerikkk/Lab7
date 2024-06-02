package managers;

import java.sql.*;
import java.util.Properties;

public class DBConnector {
    public Connection connect() {
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/postgres";
            Properties authorization = new Properties();
            authorization.put("user", "postgres");
            authorization.put("password", "1441");
            Connection connection =DriverManager.getConnection(url, authorization);
            return connection;
        }   catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
