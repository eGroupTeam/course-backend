package com.example.demo.entity;

public class TakeTimeRange {
    private Long id;
    private String name;
    private int sum;
    private String time;

    public TakeTimeRange(Long id, String name, int sum, String time) {
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
    
}
