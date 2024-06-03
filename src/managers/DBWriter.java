package managers;

import models.TicketData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWriter {
    DBConnector dbConnector = new DBConnector();
    DataCollector dataCollector = new DataCollector();
    public TicketData addDB(){
        try{
            TicketData ticketData = dataCollector.wrap();
            Connection connection= dbConnector.connect();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO s409403.\"TICKET\" (\"TNAME\", \"T_X\", \"T_Y\", \"PRICE\", \"TYPE\", \"CREATIONDATE\", \"USERNAME\") VALUES ('"+ ticketData.getName() + "',"+ticketData.getCoordinates().getX() +", "+ticketData.getCoordinates().getY() +","+ticketData.getPrice()+",'"+ticketData.getType() +"','"+ticketData.getCreationDate()+"', '" + Authorization.username + "'); \n INSERT INTO s409403.\"VENUE\" (\"NAME\", \"CAPACITY\", \"TYPE\") VALUES('" + ticketData.getVenue().getName() + "'," + ticketData.getVenue().getCapacity() +",'"+ ticketData.getVenue().getType()+ "')");
            ticketData.setUsername(Authorization.username.toUpperCase());
            if(statement!=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
            return ticketData;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public TicketData updateDB(long id) {
        try {
            Connection connection = dbConnector.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM s409403.\"TICKET\" WHERE \"ID\" = " +id+" AND \"USERNAME\" = '" + Authorization.username.toUpperCase() +"'");
            if(resultSet.next()){
                TicketData ticketData = dataCollector.wrap();
                statement.executeUpdate("UPDATE s409403.\"TICKET\" \n SET \"TNAME\" = '" + ticketData.getName() + "', \"T_X\" = " + ticketData.getCoordinates().getX()+ ", \"T_Y\"= " + ticketData.getCoordinates().getY()+ ", \"PRICE\" = " + ticketData.getPrice() + ", \"TYPE\" = '" + ticketData.getType()+ "', \"CREATIONDATE\" = '" + ticketData.getCreationDate() + "', \"USERNAME\" = '"+ Authorization.username +"' WHERE \"ID\" = " + id+ "; \n  UPDATE s409403.\"VENUE\" \n SET \"NAME\" = '" + ticketData.getVenue().getName() + "', \"CAPACITY\" = " + ticketData.getVenue().getCapacity() + ", \"TYPE\" = '" + ticketData.getVenue().getType()+ "' WHERE \"ID\" = " + id);
                ticketData.setUsername(Authorization.username);
                if(resultSet!=null){
                    resultSet.close();
                }
                if(statement!=null){
                    statement.close();
                }
                if(connection!=null){
                    connection.close();
                }
                return ticketData;
            }else {
                System.out.println("У вас нет доступа к этой ячейке");
            }
        } catch (SQLException e) {
            System.out.println("Запрос не выполнен");
        }
        return null;
    }
    public void clearDB(){
        try{
            Connection connection = dbConnector.connect();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM s409403.\"TICKET\" WHERE \"USERNAME\" = '"+Authorization.username.toUpperCase() +"'");
            if(statement!=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("У вас нет доступа к этим ячейкам");
        }
    }
    public void removeDB(long id){
        try{
            Connection connection = dbConnector.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT* FROM s409403.\"TICKET\" WHERE \"ID\" = " +id+" AND \"USERNAME\" = '" + Authorization.username.toUpperCase() +"'");
            if(resultSet.next()){
                statement.executeUpdate("DELETE FROM s409403.\"TICKET\" where \"ID\" = " + id + "; \n DELETE FROM s409403.\"VENUE\" where \"ID\" =" + id);
                if(resultSet!=null){
                    resultSet.close();
                }
                if(statement!=null){
                    statement.close();
                }
                if(connection!=null){
                    connection.close();
                }
            }else {
                System.out.println("У вас нет доступа к этой ячейке");
            }
        } catch (SQLException e) {
            System.out.println("Запрос не выполнен");
        }
    }
}
