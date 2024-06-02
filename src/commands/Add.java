package commands;

import managers.AllManagers;
import managers.CollectionManager;
import managers.DBWriter;
import models.TicketData;

/**
 * The type Add.
 */
public class Add extends Command{
    /**
     * Instantiates a new Add.
     */
    public Add() {
        super("add", "добавить новый элемент в коллекцию", "NO");
    }
    /**
     * Command to add new element to collection.
     */
    @Override
    public void run() {
        CollectionManager collectionManager = AllManagers.getManagers().getCollectionManager();
        DBWriter dbWriter = AllManagers.createAllManagers().getDbWriter();
        TicketData ticketData = dbWriter.addDB();
        collectionManager.addTicket(ticketData);
    }
}
