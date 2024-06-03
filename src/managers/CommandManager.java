package managers;

import commands.Command;
import exceptions.NoSuchCommandException;
import models.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Command manager.
 */
public class CommandManager {
    /**
     * The collection of History command.
     */
    HistoryCommand historyCommand = AllManagers.getManagers().getHistoryCommand();
    /**
     * The collection of Commands.
     */
    Map<String, Command> commands = new HashMap<>();

    /**
     * Instantiates a new Command manager.
     *
     * @param command the command
     */
    public CommandManager(Command... command){
        for(Command comms: command){
            commands.put(comms.getNameInConsole(), comms);
        }
    }

    /**
     * Method that calls command from collection of commands.
     *
     * @param nameCommand the name command
     * @throws NoSuchCommandException the no such command exception
     */
    public void callCommand(String nameCommand) throws NoSuchCommandException{
        try{
            commands.get(nameCommand).run();
            if(historyCommand.getHistoryCommands().size()==11){
                historyCommand.getHistoryCommands().removeFirst();
            }
                historyCommand.addCommand(nameCommand);
        }catch(NullPointerException ex){
            throw new NoSuchCommandException();
        }
    }

    /**
     * Gets collection of commands.
     *
     * @return the commands
     */
    public Map<String, Command> getCommands() {
        return commands;
    }
    public void callAddFromScript(String[] date){
        TicketData ticketData = new TicketData();
        ticketData.setCreationDate(LocalDateTime.now());
        ticketData.setName(date[0]);
        ticketData.setCoordinates(new Coordinates(Double.parseDouble(date[1]), Float.parseFloat(date[2])));
        ticketData.setPrice(Float.parseFloat(date[3]));
        ticketData.setType(TicketType.valueOf(date[4]));
        ticketData.setCreationDate(LocalDateTime.now());
        ticketData.setUsername(Authorization.username.toUpperCase());
        Venue venue = new Venue();
        venue.setName(date[5]);
        venue.setCapacity(Integer.parseInt(date[6]));
        venue.setType(VenueType.valueOf(date[7]));
        ticketData.setVenue(venue);
        addToDB(ticketData);
        AllManagers.getManagers().getCollectionManager().addTicket(ticketData);
    }
    public void callUpdateFromScript(String[] date, long id){
        TicketData ticketData = new TicketData();
        ticketData.setCreationDate(LocalDateTime.now());
        ticketData.setName(date[0]);
        ticketData.setCoordinates(new Coordinates(Double.parseDouble(date[1]), Float.parseFloat(date[2])));
        ticketData.setPrice(Float.parseFloat(date[3]));
        ticketData.setType(TicketType.valueOf(date[4]));
        ticketData.setUsername(Authorization.username);
        Venue venue = new Venue();
        venue.setName(date[5]);
        venue.setCapacity(Integer.parseInt(date[6]));
        venue.setType(VenueType.valueOf(date[7]));
        ticketData.setVenue(venue);
        CollectionManager coll = AllManagers.getManagers().getCollectionManager();
        try {
            Ticket t = coll.getTicketById(id);
            if (t!=null) {
                coll.getTickets().remove(t);
            }
            else {
                return;
            }
        }catch (IndexOutOfBoundsException ex){
            System.out.println("Не найден элемент с таким id");return;
        }
        updateDB(ticketData, id);
        Ticket ticket = new Ticket(id, ticketData);
        coll.getTickets().add(ticket);
        Collections.sort(coll.getTickets());
    }

    public void addToDB(TicketData ticketData){
        try{
        DBConnector dbConnector = new DBConnector();
        Connection connection= dbConnector.connect();
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO s409403.\"TICKET\" (\"TNAME\", \"T_X\", \"T_Y\", \"PRICE\", \"TYPE\", \"CREATIONDATE\", \"USERNAME\") VALUES ('"+ ticketData.getName() + "',"+ticketData.getCoordinates().getX() +", "+ticketData.getCoordinates().getY() +","+ticketData.getPrice()+",'"+ticketData.getType() +"','"+ticketData.getCreationDate()+"', '" + Authorization.username + "'); \n INSERT INTO s409403.\"VENUE\" (\"NAME\", \"CAPACITY\", \"TYPE\") VALUES('" + ticketData.getVenue().getName() + "'," + ticketData.getVenue().getCapacity() +",'"+ ticketData.getVenue().getType()+ "')");
        if(statement!=null){
            statement.close();
        }
        if(connection!=null){
            connection.close();
        }
    } catch (SQLException e) {
            System.out.println("Запрос не выполнен");
    }
    }
    public void updateDB(TicketData ticketData, long id){
        try {
            DBConnector dbConnector = new DBConnector();
            Connection connection = dbConnector.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM s409403.\"TICKET\" WHERE \"ID\" = " +id+" AND \"USERNAME\" = '" + Authorization.username.toUpperCase() +"'");
            if(resultSet.next()){
                statement.executeUpdate("UPDATE s409403.\"TICKET\" \n SET \"TNAME\" = '" + ticketData.getName() + "', \"T_X\" = " + ticketData.getCoordinates().getX()+ ", \"T_Y\"= " + ticketData.getCoordinates().getY()+ ", \"PRICE\" = " + ticketData.getPrice() + ", \"TYPE\" = '" + ticketData.getType()+ "', \"CREATIONDATE\" = '" + ticketData.getCreationDate() + "', \"USERNAME\" = '"+ Authorization.username +"' WHERE \"ID\" = " + id+ "; \n  UPDATE s409403.\"VENUE\" \n SET \"NAME\" = '" + ticketData.getVenue().getName() + "', \"CAPACITY\" = " + ticketData.getVenue().getCapacity() + ", \"TYPE\" = '" + ticketData.getVenue().getType()+ "' WHERE \"ID\" = " + id);
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
