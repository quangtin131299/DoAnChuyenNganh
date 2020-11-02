package com.example.appdatvexemphim.DTO;

import java.util.Date;

public class Movie {
    private  int id;
    private String tenphim;
    private int thoigian;
    private String hinh;
    private String status;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie(int id, String tenphim, int thoigian, String hinh, String status) {
        this.id = id;
        this.tenphim = tenphim;
        this.thoigian = thoigian;
        this.hinh = hinh;
        this.status = status;
    }

    public String getTenphim() {
        return tenphim;
    }

    public void setTenphim(String tenphim) {
        this.tenphim = tenphim;
    }

    public int getThoigian() {
        return thoigian;
    }

    public void setThoigian(int thoigian) {
        this.thoigian = thoigian;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Movie() {
    }
}
