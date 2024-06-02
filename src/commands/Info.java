package commands;

import managers.AllManagers;
import managers.CollectionManager;
import models.Ticket;

/**
 * The type Info.
 */
public class Info extends Command{
    /**
     * Instantiates a new Info.
     */
    public Info() {
        super("info", " вывести в стандартный поток вывода информацию о коллекции (тип коллекции,тип элементов, количество элементов)", "NO");
    }
    /**
     * Command to get info about collection type, size and elements type.
     */
    @Override
    public void run() {
        CollectionManager collectionManager = AllManagers.getManagers().getCollectionManager();
        System.out.println("Тип коллекции: " + collectionManager.getTickets().getClass().getSimpleName());
        System.out.println("Тип элементов: " + Ticket.class.getSimpleName());
        System.out.println("Количество элементов: " + collectionManager.getTickets().size());
    }
}
