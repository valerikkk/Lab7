package managers;

import models.Ticket;
import models.TicketData;
import java.util.Vector;

/**
 * The type Collection manager.
 */
public class CollectionManager {
    /**
     * The collection of Tickets.
     */
    Vector<Ticket> tickets = new Vector<>();
    private long lastId = 0;

    /**
     * Instantiates a new Collection manager.
     */
    public CollectionManager() {
    }

    /**
     * Add ticket to collection.
     *
     * @param ticketData the ticket data
     */
    public void addTicket(TicketData ticketData) {
        Ticket ticket = new Ticket(getNewId(), ticketData);
        tickets.add(ticket);
    }

    /**
     * Get new id long. Automatically generation.
     *
     * @return the long
     */
    public long getNewId(){
        return ++lastId;
    }

    /**
     * Get ticket by id ticket.
     *
     * @param id the id
     * @return the ticket
     */
    public Ticket getTicketById(long id){
        for (Ticket tick : tickets) {
            if (tick.getId() == id) {
                return tick;
                }
            }
        System.out.println("Элемента с таким id не обнаружено");
        return null;
    }

    /**
     * Remove ticket from collection by id.
     *
     * @param id the id
     */
    public void removeById(long id){
        try {
            tickets.remove(getTicketById(id));
        }catch (IndexOutOfBoundsException ex){
            System.out.println("Введите значение long >=0");
        }
    }

    /**
     * Update ticket into collection by id.
     *
     * @param id the id
     */


    /**
     * Get ticket's collection.
     *
     * @return the vector
     */
    public Vector<Ticket> getTickets(){
        return tickets;
    }

    /**
     * Set new collection.
     *
     * @param newColl the new coll
     */
    public void setCollection(Vector<Ticket> newColl){
        this.tickets = newColl;
        lastId = getNewId();
    }
    /**
     * Get last generate id.
     *
     * @return the last id
     */
    public long getLastId() {
        return lastId;
    }

    /**
     * Set new last id, if last element has deleted.
     *
     * @param lastId the last id
     */
    public void setLastId(long lastId) {
        this.lastId = lastId;
    }
}