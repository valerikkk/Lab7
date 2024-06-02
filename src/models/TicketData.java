package models;

import exceptions.NullValueException;

import java.time.LocalDateTime;
import java.util.Objects;

public class TicketData {
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDateTime creationDate;
    private float price;
    private TicketType type;
    private Venue venue;
    private String username;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public float getPrice() {
        return price;
    }

    public TicketType getType() {
        return type;
    }

    public Venue getVenue() {
        return venue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        try{
            if (name == null || name.trim().isEmpty()){
                throw new NullValueException();
            }
            else {
                this.name = name;
            }
        }catch (NullValueException e){
            System.out.println(e.getMessage());
        }
    }
    public void setCoordinates(Coordinates coordinates) {
        try{
            if (coordinates == null){
                throw new NullValueException();
            }
            else{
                this.coordinates = coordinates;
            }
        }catch (NullValueException e){
            System.out.println(e.getMessage());
        }
    }
    public void setCreationDate(LocalDateTime creationDate) {
        try{
            if(creationDate == null){
                throw new NullValueException();
            }else{
                this.creationDate = creationDate;
            }
        }catch (NullValueException e){
            System.out.println(e.getMessage());
        }
    }

    public void setPrice(float price) {
        try{
            if (price <= 0.0){
                throw new NullValueException();
            }
            else {
                this.price = price;
            }
        }catch (NullValueException e){
            System.out.println(e.getMessage());
        }
    }

    public void setType(TicketType type) {
        try{
            if (type == null){
                throw new NullValueException();
            }
            else {
                this.type = type;
            }
        }catch (NullValueException e){
            System.out.println(e.getMessage());
        }
    }

    public void setVenue(Venue venue) {
        try{
            if (venue == null){
                throw new NullValueException();
            }
            else {
                this.venue = venue;
            }
        }catch (NullValueException e){
            System.out.println(e.getMessage());
        }
    }

    public String getUsername() {
        return username.toUpperCase();
    }

    public void setUsername(String username) {
        this.username = username.toUpperCase();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates, creationDate, price, type);
    }
}
