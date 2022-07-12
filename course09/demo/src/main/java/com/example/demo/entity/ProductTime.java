package com.example.demo.entity;
import java.util.Objects;

public class ProductTime {
    private Long id;
    private String name;
    private int sum;
    private String time;


    public ProductTime() {
    }

    public ProductTime(Long id, String name, int sum, String time) {
        this.id = id;
        this.name = name;
        this.sum = sum;
        this.time = time;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSum() {
        return this.sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ProductTime id(Long id) {
        setId(id);
        return this;
    }

    public ProductTime name(String name) {
        setName(name);
        return this;
    }

    public ProductTime sum(int sum) {
        setSum(sum);
        return this;
    }

    public ProductTime time(String time) {
        setTime(time);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProductTime)) {
            return false;
        }
        ProductTime productTime = (ProductTime) o;
        return Objects.equals(id, productTime.id) && Objects.equals(name, productTime.name) && sum == productTime.sum && Objects.equals(time, productTime.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sum, time);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", sum='" + getSum() + "'" +
            ", time='" + getTime() + "'" +
            "}";
    }


}