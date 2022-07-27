package egroup.test202207.entity;

import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private String description;
    private int order;
    private int price;
    private int orgnztnId;
    private String orgnztnName = "unmapped";

    public Product() {
    }

    public Product(int id, String name, String description, int order, int price, int orgnztnId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.order = order;
        this.price = price;
        this.orgnztnId = orgnztnId;
    }
    
    public Product(int id, String name, String description, int order, int price,int orgnztnId, String orgnztnName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.order = order;
        this.price = price;
        this.orgnztnId = orgnztnId;
        this.orgnztnName = orgnztnName;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOrgnztnId() {
        return this.orgnztnId;
    }

    public void setOrgnztnName(String orgnztnName) {
        this.orgnztnName = orgnztnName;
    }

    public String getOrgnztnName() {
        return this.orgnztnName;
    }

    public void setOrgnztnId(int orgnztnId) {
        this.orgnztnId = orgnztnId;
    }

    public Product id(int id) {
        setId(id);
        return this;
    }

    public Product name(String name) {
        setName(name);
        return this;
    }

    public Product description(String description) {
        setDescription(description);
        return this;
    }

    public Product order(int order) {
        setOrder(order);
        return this;
    }

    public Product price(int price) {
        setPrice(price);
        return this;
    }

    public Product orgnztnId(int orgnztnId) {
        setOrgnztnId(orgnztnId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return id == product.id && Objects.equals(name, product.name) && Objects.equals(description, product.description) && order == product.order && price == product.price && Objects.equals(orgnztnId, product.orgnztnId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, order, price, orgnztnId);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", order='" + getOrder() + "'" +
            ", price='" + getPrice() + "'" +
            ", orgnztnId='" + getOrgnztnId() + "'" +
            "}";
    }
}
