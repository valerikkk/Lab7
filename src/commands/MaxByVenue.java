package commands;

import managers.AllManagers;
import managers.CollectionManager;
import managers.ComparatorVenue;
import models.Ticket;
import java.util.Vector;

/**
 * The type Max by venue.
 */
public class MaxByVenue extends Command{
    /**
     * Instantiates a new Max by venue.
     */
    public MaxByVenue() {
        super("max_by_venue", "вывести любой объект из коллекции, значение поля venue которого является максимальным", "NO");
    }
    /**
     * Command to get element with max venue.
     */
    @Override
    public void run() {
        CollectionManager collectionManager = AllManagers.getManagers().getCollectionManager();
        ComparatorVenue comparatorVenue = new ComparatorVenue();
        Vector<Ticket> tickets = collectionManager.getTickets();
        Vector<Ticket> sortTickets = new Vector<>(tickets);
        sortTickets.sort(comparatorVenue);
        System.out.println(sortTickets.get(0));
    }
}
