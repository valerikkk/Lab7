package managers;

import models.TicketData;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWriter {
    DBConnector dbConnector = new DBConnector();
    DataCollector dataCollector = new DataCollector();
    public TicketData addDB(){
        try{
            TicketData ticketData = dataCollector.wrap();
            System.out.println("Здесь всё кайф");
            Connection connection = null;
            try{
                connection = dbConnector.connect();
            }
            catch (NullPointerException E){
                E.printStackTrace();
                System.out.println(E.getLocalizedMessage());
                System.out.println(E.getMessage());
            }
            if(connection.isClosed()){
                System.out.println("true!!!!");
            }
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO public.\"TICKET\" (\"TNAME\", \"T_X\", \"T_Y\", \"PRICE\", \"TYPE\", \"CREATIONDATE\") VALUES ('"+ ticketData.getName() + "','"+ticketData.getCoordinates().getX() +"','"+ticketData.getCoordinates().getY() +"','"+ticketData.getPrice()+"','"+ticketData.getType() +"','"+ticketData.getCreationDate()+"'); \n INSERT INTO public.\"VENUE\" (\"NAME\", \"CAPACITY\", \"TYPE\") VALUES('" + ticketData.getVenue().getName() + "','" + ticketData.getVenue().getCapacity() +"','"+ ticketData.getVenue().getType()+ "')");
            System.out.println("Здесь всё норм");
            return ticketData;
        } catch (SQLException e) {
            System.out.println("Запрос не выполнен");
        }
        System.out.println("Здесь всё имба");
        return null;
    }
    public TicketData updateDB(long id) {
        TicketData ticketData = dataCollector.wrap();
        try {
            Connection connection = dbConnector.connect();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE public.\"TICKET\" \n SET \"TNAME\" = '" + ticketData.getName() + "', \"T_X\" = " + ticketData.getCoordinates().getX()+ ", \"T_Y\"= " + ticketData.getCoordinates().getY()+ ", \"PRICE\" = " + ticketData.getPrice() + ", \"TYPE\" = '" + ticketData.getType()+ "', \"CREATIONDATE\" = '" + ticketData.getCreationDate() + "' WHERE \"ID\" = " + id+ "; \n  UPDATE public.\"VENUE\" \n SET \"NAME\" = '" + ticketData.getVenue().getName() + "', \"CAPACITY\" = " + ticketData.getVenue().getCapacity() + ", \"TYPE\" = '" + ticketData.getVenue().getType()+ "' WHERE \"ID\" = " + id);
        } catch (SQLException e) {
            System.out.println("Запрос не выполнен");
        }
        return ticketData;
    }
    public void clearDB(){
        try{
            Connection connection = dbConnector.connect();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM public.\"TICKET\"; \n DELETE FROM public\"VENUE\"");
        } catch (SQLException e) {
            System.out.println("Запрос не выполнен");
        }
    }
    public void removeDB(long id){
        try{
            Connection connection = dbConnector.connect();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM public.\"TICKET\" where \"ID\" = " + id);
        } catch (SQLException e) {
            System.out.println("Запрос не выполнен");
        }
    }
}
