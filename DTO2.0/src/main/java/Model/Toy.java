package Model;

import java.io.Serializable;

public class Toy implements Serializable {
    private String name;
    private Type type;
    private Integer price;
    private Integer amount;

    public Toy(String name, Type type, Integer prize, Integer amount) {
        this.name = name;
        this.type = type;
        this.price = prize;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}