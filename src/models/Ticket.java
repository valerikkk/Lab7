
package models;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
    public class Ticket implements Comparable<Ticket> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float price; //Значение поля должно быть больше 0
    private TicketType type; //Поле не может быть null
    private Venue venue; //Поле не может быть null
        private String username;
    public Ticket(Long id, TicketData ticketData) {
        this.id = id;
        this.name = ticketData.getName();
        this.coordinates = ticketData.getCoordinates();
        this.creationDate = ticketData.getCreationDate();
        this.price = ticketData.getPrice();
        this.type = ticketData.getType();
        this.venue = ticketData.getVenue();
        this.username = ticketData.getUsername();
    }
    public Long getId() {
        return id;
    }
    public TicketType getType() {
        return type;
    }

    public Venue getVenue() {
        return venue;
    }
        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Ticket{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", coordinates=" + coordinates +
                    ", creationDate=" + creationDate +
                    ", price=" + price +
                    ", type=" + type +
                    ", venue=" + venue +
                    ", username='" + username + '\'' +
                    '}';
        }

        @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Ticket ticket = (Ticket) object;
        return Float.compare(price, ticket.price) == 0 && Objects.equals(id, ticket.id) && Objects.equals(name, ticket.name) && Objects.equals(coordinates, ticket.coordinates) && Objects.equals(creationDate, ticket.creationDate) && type == ticket.type && Objects.equals(venue, ticket.venue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, price, type, venue);
    }

    @Override
    public int compareTo(Ticket tick) {
        int compareById = this.id.compareTo(tick.getId());
        if (compareById != 0) {
            return compareById;
        }
        return this.type.compareTo(tick.getType());
    }
}