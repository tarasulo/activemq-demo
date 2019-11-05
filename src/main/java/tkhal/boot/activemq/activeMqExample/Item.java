package tkhal.boot.activemq.activeMqExample;

import java.io.Serializable;

public class Item implements Serializable {
    private String name;
    private int year;
    private double price;

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

    public Double getPrice() {
        return price;
    }

    public Item(String name, Integer year, Double price) {
        this.name = name;
        this.year = year;
        this.price = price;
    }
}
