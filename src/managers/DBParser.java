package managers;

import exceptions.NullValueException;
import models.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class DBParser {
    CollectionManager collectionManager = AllManagers.managers.getCollectionManager();
    DBConnector dbConnector = new DBConnector();
    public void read(){
        try{
            Statement statement = dbConnector.connect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = statement.executeQuery("SELECT t.\"ID\", t.\"TNAME\", t.\"T_X\", t.\"T_Y\", t.\"PRICE\", t.\"TYPE\", t.\"CREATIONDATE\", v.\"NAME\", v.\"CAPACITY\", v.\"TYPE\" FROM public.\"TICKET\" as t LEFT JOIN public.\"VENUE\" as v ON t.\"ID\" = v.\"ID\"");
            String[] data = new String[res.getMetaData().getColumnCount()];
            res.beforeFirst();
            while(res.next()){
                for(int j = 1; j<=res.getMetaData().getColumnCount(); j++){
                    data[j-1] = res.getString(j);
                    if(j==10){
                        parse(data);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void parse(String[] tempArr){
        Long id = Long.parseLong(tempArr[0]);
        String name = tempArr[1].trim();
        Coordinates coordinates = new Coordinates(Double.parseDouble(tempArr[2].trim()), Float.parseFloat(tempArr[3].trim()));
        LocalDateTime creationDate = LocalDateTime.parse(tempArr[6]);
        float price = Float.parseFloat(tempArr[4].trim());
        TicketType type = TicketType.valueOf(tempArr[5].trim());
        Venue venue = new Venue();
        try{
            venue.setName(tempArr[7].trim());
            venue.setCapacity(Integer.parseInt(tempArr[8].trim()));
            venue.setType(VenueType.valueOf(tempArr[9].trim()));
            venue.setId(Math.abs((long) venue.hashCode()));
            TicketData ticketData = new TicketData();
            ticketData.setName(name);
            ticketData.setCoordinates(coordinates);
            ticketData.setPrice(price);
            ticketData.setType(type);
            ticketData.setCreationDate(creationDate);
            ticketData.setVenue(venue);
            Ticket ticket = new Ticket(id, ticketData);
            collectionManager.getTickets().add(ticket);
        }catch (NullValueException ex){
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
}
