package com.example.order_app;

public class item_menu {
    private int id;
    private int hinhmon;
    private String tenmon;
    private String mota;
    private int giatien;

    public item_menu(int id, int hinhmon, String tenmon, String mota, int giatien){
        this.id = id;
        this.hinhmon = hinhmon;
        this.tenmon = tenmon;
        this.mota = mota;
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

    public String getMota() {
        return mota;
    }

    public void setMota(String country) {
        this.mota = mota;
    }

    public int getGiatien() {
        return giatien;
    }

    public void setDis(int giatien) {
        this.giatien = giatien;
    }
}
