package com.example.newui;

//public class CardItem {
//
//    private int name;
//    private String info;
//    private String number;
//
//    public int getName() {
//        return name;
//    }
//
//    public String getInfo() {
//        return info;
//    }
//
//    public String getNumber() {
//        return number;
//    }
//
//    public CardItem(int name, String info , String number){
//        this.name = name;
//        this.info = info;
//        this.number = number;
//
//    }
//
//}

public class CardItem {

    private int img;
    private String name;
    private String number;
    private String cvc;
    private String validity;
    private String nickname;


    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getCvc() {
        return cvc;
    }

    public String getValidity() {
        return validity;

    }

    public String getNickname() {
        return nickname;
    }

    public CardItem(int img, String name, String number, String cvc, String validity, String nickname) {
        this.img = img;
        this.name = name;
        this.number = number;
        this.cvc = cvc;
        this.validity = validity;
        this.nickname = nickname;

    }


}