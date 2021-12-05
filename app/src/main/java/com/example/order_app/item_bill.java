package com.example.order_app;

public class item_bill {
    private int id;
    private int hinhmon;
    private String tenmon;
    private int giatien;
    private int soluong;

    public item_bill (int id, int hinhmon, String tenmon, int giatien, int soluong){
        this.id = id;
        this.hinhmon = hinhmon;
        this.tenmon = tenmon;
        this.soluong = soluong;
        this.giatien = giatien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHinhmon() {
        return hinhmon;
    }

    public void setHinhmon(int hinhmon) {
        this.hinhmon = hinhmon;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }


    public int getGiatien() {
        return giatien;
    }

    public void setDis(int giatien) {
        this.giatien = giatien;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}

