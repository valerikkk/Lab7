package commands;

import managers.AllManagers;
import managers.CollectionManager;
import models.Ticket;
import java.util.Vector;

/**
 * The type Show.
 */
public class Show extends Command{
    /**
     * Instantiates a new Show.
     */
    public Show(){
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении", "NO");
    }
    /**
     * Command to output all items in the collection in string representation to the standard output stream
     */
    @Override
    public void run(){
        CollectionManager collectionManager = AllManagers.createAllManagers().getCollectionManager();
        Vector<Ticket> tickets = collectionManager.getTickets();
        for(Ticket ticks: tickets){
            System.out.println(ticks.toString());
        }
    }
}
