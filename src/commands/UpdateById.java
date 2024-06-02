package commands;

import managers.AllManagers;
import managers.CollectionManager;
import managers.ConsoleManager;
import managers.DBWriter;
import models.Ticket;
import models.TicketData;

import java.util.Collections;

/**
 * The type Update by id.
 */
public class UpdateById extends Command{
    /**
     * Instantiates a new Update by id.
     */
    public UpdateById() {
        super("update", "обновить значение элемента коллекции, id которого равен заданному. Необходимо ввести id через пробел.","YES");
    }
    /**
     * command to update the value of a collection item whose ID is equal to the specified.
     */
    @Override
    public void run() {
        CollectionManager collectionManager = AllManagers.getManagers().getCollectionManager();
        ConsoleManager consoleManager = AllManagers.createAllManagers().getConsoleManager();
        DBWriter dbWriter = AllManagers.createAllManagers().getDbWriter();
        try{
            collectionManager.removeById(Long.parseLong(consoleManager.getTokens()[1]));
            TicketData ticketData = dbWriter.updateDB(Long.parseLong(consoleManager.getTokens()[1]));
            Ticket ticket = new Ticket(Long.parseLong(consoleManager.getTokens()[1]), ticketData);
            collectionManager.getTickets().add(ticket);
            Collections.sort(collectionManager.getTickets());
        }catch (NumberFormatException ex){
            System.out.println("ID должен быть long и >=0");
        }catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Можно хотя бы попытаться ввести id");
        }
    }
}
