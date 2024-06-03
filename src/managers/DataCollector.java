package managers;
import exceptions.NullValueException;
import models.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.Scanner;

/**
 * The type Data collector.
 */
public class DataCollector{
    Scanner scanner = AllManagers.getManagers().getScanner();

    /**
     * Wrap ticket data from user info.
     *
     * @return the ticket data
     */
    public TicketData wrap(){
        TicketData ticketData = new TicketData();
        ticketData.setCreationDate(LocalDateTime.now());
        collectName(ticketData);
        collectCoordinates(ticketData);
        collectPrice(ticketData);
        collectTicketType(ticketData);
        collectVenue(ticketData);
        System.out.println("success");
        ticketData.setUsername(Authorization.username);
        return ticketData;
    }

    /**
     * Collect name.
     *
     * @param ticketData the ticket data
     */
    public void collectName(TicketData ticketData){
        System.out.println("Введите название билета");
        String name = collectString();
        ticketData.setName(name);
    }

    /**
     * Collect coordinates.
     *
     * @param ticketData the ticket data
     */
    public void collectCoordinates(TicketData ticketData){
        System.out.println("Введите координату x (double)");
        double x = collectDouble();
        System.out.println("Введите координату y (float)");
        float y = collectAllFloat();
        ticketData.setCoordinates(new Coordinates(x, y));
    }

    /**
     * Collect price.
     *
     * @param ticketData the ticket data
     */
    public void collectPrice(TicketData ticketData){
        System.out.println("Введите цену билета");
        float price=collectFloat();
        ticketData.setPrice(price);
    }

    /**
     * Collect ticket type.
     *
     * @param ticketData the ticket data
     */
    public void collectTicketType(TicketData ticketData){
        System.out.println("Введите тип билета");
        TicketType type = collectTicketType();
        ticketData.setType(type);
    }

    /**
     * Collect venue.
     *
     * @param ticketData the ticket data
     */
    public void collectVenue(TicketData ticketData){
        Venue venue = new Venue();
        System.out.println("Введите место проведения мероприятия");
        String name = collectString();
        venue.setName(name);
        System.out.println("Введите вместимость площадки");
        int capacity = collectInteger();
        venue.setCapacity(capacity);
        System.out.println("Введите тип площадки");
        VenueType venueType = collectVenueType();
        venue.setType(venueType);
        venue.setId(Math.abs((long)venue.hashCode()));
        ticketData.setVenue(venue);
    }

    /**
     * Collect value from console.
     *
     * @return the string
     * @throws NullValueException       the null value exception
     * @throws IllegalArgumentException the illegal argument exception
     */
    public String collectValue() throws NullValueException, IllegalArgumentException{
        String value = scanner.nextLine();
        if(value.trim().isEmpty()){
            throw new NullValueException();
        }
        return value;
    }

    /**
     * Collect String from console
     *
     * @return the string
     */
    public String collectString(){
        while(true){
            try {
                return collectValue();
            }catch (NullValueException ex){
                System.out.println("Значение этого поля не может быть пустым");
            }
        }
    }

    /**
     * Collect Integer from string.
     *
     * @return the integer
     */
    public Integer collectInteger(){
        while(true){
            try {
                int capacity = Integer.parseInt(collectValue());
                if(capacity<=0){
                    System.out.println("значение должно быть >=0");
                    continue;
                }
                return capacity;
            }catch (NullValueException ex){
                System.out.println("Значение этого поля не может быть пустым");
            }catch(IllegalArgumentException ex){
                System.out.println("Введите тип int, ");
            }
        }
    }

    /**
     * Collect double from string.
     *
     * @return the double
     */
    public Double collectDouble(){
        while(true){
            try{
                return Double.parseDouble(collectValue());
            }catch (NullValueException ex){
                System.out.println("Значение этого поля не может быть пустым");
            }catch(IllegalArgumentException ex){
                System.out.println("Введите тип double");
            }
        }
    }

    /**
     * Collect float from string with restrictions.
     *
     * @return the float
     */
    public Float collectFloat(){
        while(true){
            try {
                float price = Float.parseFloat(collectValue());
                if(price<=0){
                    System.out.println("Значение этого поля должно быть >= 0");
                    continue;
                }
                return price;
            }catch(NullValueException ex){
                System.out.println("Значение этого поля не может быть пустым");
            }catch(IllegalArgumentException ex){
                System.out.println("Введите тип float, значение больше или равно 0");
            }
        }
    }

    /**
     * Collect float without restrictions.
     *
     * @return the float
     */
    public float collectAllFloat(){
        while(true){
            try {
                return Float.parseFloat(collectValue());
            }catch(NullValueException ex){
                System.out.println("Значение этого поля не может быть пустым");
            }catch(IllegalArgumentException ex){
                System.out.println("Введите тип float, значение больше или равно 0");
            }
        }
    }

    /**
     * Collect ticket type.
     *
     * @return the ticket type
     */
    public TicketType collectTicketType(){
        while(true){
            try{
                return TicketType.valueOf(collectValue().toUpperCase());
            }catch(NullValueException ex){
                System.out.println("Значение этого поля не может быть пустым");
            }catch (IllegalArgumentException ex){
                System.out.println("Введите одно из предложенных значений");
                EnumSet<TicketType> ticketTypes = EnumSet.allOf(TicketType.class);
                for (TicketType ticketType:ticketTypes) {
                    System.out.println(ticketType);
                }
            }
        }
    }

    /**
     * Collect venue type from string.
     *
     * @return the venue type
     */
    public VenueType collectVenueType(){
        while(true){
            try{
                return VenueType.valueOf(collectValue().toUpperCase());
            }catch(NullValueException ex){
                System.out.println("Значение этого поля не может быть пустым");
            }catch (IllegalArgumentException ex){
                System.out.println("Введите одно из предложенных значений");
                EnumSet<VenueType> venueTypes = EnumSet.allOf(VenueType.class);
                for (VenueType venueType:venueTypes) {
                    System.out.println(venueType);
                }
            }
        }
    }
}
