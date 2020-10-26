package com.example.appdatvexemphim.DTO;

public class Seat {
    private int id;
    private String tenghe;
    private String trangthai;
    private String tenphong;
    private String gio;

    public Seat(int id, String tenghe, String trangthai) {
        this.id = id;
        this.tenghe = tenghe;
        this.trangthai = trangthai;
    }

    public Seat() {
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public String getTenphong() {
        return tenphong;
    }

    public void setTenphong(String tenphong) {
        this.tenphong = tenphong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenghe() {
        return tenghe;
    }

    public void setTenghe(String tenghe) {
        this.tenghe = tenghe;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
