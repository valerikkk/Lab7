package managers;

import exceptions.NullValueException;
import models.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Csv manager.
 */
public class CSVManager {
    /**
     * Instantiates a new Csv manager.
     */
    public CSVManager() {
    }

    /**
     * The import of Collection manager.
     */
    CollectionManager collectionManager = AllManagers.managers.getCollectionManager();

    /**
     * Assigning Values from a Parsed File to Ticket Fields.
     *
     * @param data the data
     */
    public void ticketParse(String[] data) {
        try {
            for (String cont : data) {
                String[] tempArr = cont.split(",");
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
                String name = tempArr[0].trim(); //Поле не может быть null, Строка не может быть пустой
                Coordinates coordinates = new Coordinates(Double.parseDouble(tempArr[1].trim()), Float.parseFloat(tempArr[2].trim())); //Поле не может быть null
                LocalDateTime creationDate = LocalDateTime.parse(tempArr[3].trim(), dateTimeFormatter); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
                float price = Float.parseFloat(tempArr[4].trim()); //Значение поля должно быть больше 0
                TicketType type = TicketType.valueOf(tempArr[5].trim()); //Поле не может быть null
                Venue venue = new Venue(); //Поле не может быть null\
                try{
                    venue.setName(tempArr[6].trim());
                    venue.setCapacity(Integer.parseInt(tempArr[7].trim()));
                    venue.setType(VenueType.valueOf(tempArr[8].trim()));
                    venue.setId(Math.abs((long) venue.hashCode()));
                    TicketData ticketData = new TicketData();
                    ticketData.setName(name);
                    ticketData.setCoordinates(coordinates);
                    ticketData.setPrice(price);
                    ticketData.setType(type);
                    ticketData.setCreationDate(creationDate);
                    ticketData.setVenue(venue);
                    collectionManager.addTicket(ticketData);
                }catch (NullValueException ex){
                    System.out.println(ex.getMessage());
                    System.exit(1);
                }
            }
        }catch (IndexOutOfBoundsException ex){
            System.out.println("Выбранный файл не соответствует стандарту");
            AllManagers.getManagers().setPath(null);
            System.out.println("Введите путь к корректному фалйу, где есть все поля, необходимые для инициализации билета");
        }
    }
}
