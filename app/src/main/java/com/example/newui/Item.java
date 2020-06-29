package com.example.newui;

public class Item {

    private int name;
    private String info;
    private String number;

    public int getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public String getNumber() {
        return number;
    }

    public Item(int name, String info , String number){
        this.name = name;
        this.info = info;
        this.number = number;

    }

}
