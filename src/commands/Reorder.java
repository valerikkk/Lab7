package commands;

import managers.AllManagers;
import managers.CollectionManager;
import models.Ticket;
import java.util.Vector;

/**
 * The type Reorder.
 */
public class Reorder extends Command{
    /**
     * Instantiates a new Reorder.
     */
    public Reorder() {
        super("reorder", "отсортировать коллекцию в порядке, обратном нынешнему", "NO");
    }

    /**
     *  Command to sort the collection in reverse order
     */
    @Override
    public void run() {
        CollectionManager collectionManager = AllManagers.getManagers().getCollectionManager();
        Vector<Ticket> tickets = collectionManager.getTickets();
        Vector<Ticket> sortTickets = new Vector<>();
        for (int i = tickets.size()-1; i>=0; i--) {
            sortTickets.add(tickets.get(i));
        }
        collectionManager.setLastId(collectionManager.getLastId()-1);
        collectionManager.setCollection(sortTickets);
        System.out.println("Вызовите команду show, чтобы убедиться.");
    }
}
