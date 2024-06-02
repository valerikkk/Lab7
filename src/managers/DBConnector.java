package managers;

import java.sql.*;
import java.util.Properties;

public class DBConnector {
    public Connection connect() {
        try{
            Class.forName("org.postgresql.Driver");
            //String url = "jdbc:postgresql://localhost:5432/postgres";
            String url = "jdbc:postgresql://db:5432/studs";
            Properties authorization = new Properties();
            //authorization.put("user", "postgres");
            authorization.put("user", "s409403");
            //authorization.put("password", "1441");
            authorization.put("password", "tgwZo0X026ODdLJH");
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
