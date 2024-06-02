package managers;

import commands.Command;
import exceptions.NoSuchCommandException;
import models.*;

import java.time.LocalDateTime;
import java.util.Collection;
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
        Venue venue = new Venue();
        venue.setName(date[5]);
        venue.setCapacity(Integer.parseInt(date[6]));
        venue.setType(VenueType.valueOf(date[7]));
        ticketData.setVenue(venue);
        AllManagers.getManagers().getCollectionManager().addTicket(ticketData);
    }
    public void callUpdateFromScript(String[] date, long id){
        TicketData ticketData = new TicketData();
        ticketData.setCreationDate(LocalDateTime.now());
        ticketData.setName(date[0]);
        ticketData.setCoordinates(new Coordinates(Double.parseDouble(date[1]), Float.parseFloat(date[2])));
        ticketData.setPrice(Float.parseFloat(date[3]));
        ticketData.setType(TicketType.valueOf(date[4]));
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
            System.out.println("Введите значение long >=0");return;
        }
        Ticket ticket = new Ticket(id, ticketData);
        coll.getTickets().add(ticket);
        Collections.sort(coll.getTickets());
    }
}
