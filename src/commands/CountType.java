package commands;

import managers.AllManagers;
import managers.CollectionManager;
import managers.ConsoleManager;
import models.Ticket;
import models.TicketType;
import java.util.EnumSet;
import java.util.Vector;

/**
 * The type Count type.
 */
public class CountType extends Command{
    /**
     * Instantiates a new Count type.
     */
    public CountType() {
        super("count_by","вывести количество элементов, значения поля type равно заданному. Необходимо ввести type через пробел", "YES");
    }
    /**
     * Command to get count of elements with given type.
     */
    @Override
    public void run() {
        ConsoleManager consoleManager = AllManagers.createAllManagers().getConsoleManager();
        CollectionManager collectionManager = AllManagers.getManagers().getCollectionManager();
        Vector <Ticket> tickets = collectionManager.getTickets();
        int counter = 0;
        try{
            for (Ticket ticket:tickets) {
                if(ticket.getType().equals(TicketType.valueOf(consoleManager.getTokens()[1].toUpperCase()))){
                    counter++;
                }
            }
            System.out.println(counter);
        }catch (IllegalArgumentException ex){
            System.out.println("Вы ввели ерунду. Выберите предложенный тип");
            EnumSet<TicketType> ticketTypes = EnumSet.allOf(TicketType.class);
            for (TicketType ticketType:ticketTypes) {
                System.out.println(ticketType);
            }
        }catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("Можно хотя бы попытаться ввести тип");
        }
    }
}
