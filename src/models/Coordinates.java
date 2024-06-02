
package models;

public class Coordinates {
    private Double x;
    private Float y;

    public Coordinates(Double x, Float y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    public String fileToString() {
        return x +  "," + y;
    }

    public Float getY() {
        return y;
    }

    public Double getX() {
        return x;
    }
}