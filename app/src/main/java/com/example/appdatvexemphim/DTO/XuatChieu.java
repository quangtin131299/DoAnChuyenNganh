package com.example.appdatvexemphim.DTO;

public class XuatChieu {
    int id;
    String thoigian;

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public XuatChieu(int id, String thoigian) {
        this.id = id;
        this.thoigian = thoigian;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public XuatChieu() {
    }
}
