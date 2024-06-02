package commands;

import managers.AllManagers;
import managers.CollectionManager;
import managers.ConsoleManager;
import models.Ticket;
import models.TicketType;
import java.util.EnumSet;
import java.util.Vector;

/**
 * The type Filter type.
 */
public class FilterType extends Command{
    /**
     * Instantiates a new Filter type.
     */
    public FilterType() {
        super("filter_by", "вывести элементы, значения поля type которых равны заданному. Необходимо ввести type через пробел.","YES");
    }
    /**
     * Command to get elements with a given ticket type
     */
    @Override
    public void run() {
        ConsoleManager consoleManager = AllManagers.createAllManagers().getConsoleManager();
        CollectionManager collectionManager = AllManagers.getManagers().getCollectionManager();
        Vector<Ticket> tickets = collectionManager.getTickets();
        Vector<Ticket> filterTickets = new Vector<>();
        try{
            for (Ticket ticket:tickets) {
                if(ticket.getType().equals(TicketType.valueOf(consoleManager.getTokens()[1].toUpperCase()))){
                    filterTickets.add(ticket);
                }
            }
            System.out.println(filterTickets);
        }catch(IllegalArgumentException ex){
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
