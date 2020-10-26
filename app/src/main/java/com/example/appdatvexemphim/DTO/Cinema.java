package com.example.appdatvexemphim.DTO;

public class Cinema {
    private int id;
    private String tenrap;
    private String diachi;
    private String hinh;

    public Cinema() {
    }

    public Cinema(int id, String tenrap, String diachi, String hinh) {
        this.id = id;
        this.tenrap = tenrap;
        this.diachi = diachi;
        this.hinh = hinh;
    }

    public String getTenrap() {
        return tenrap;
    }

    public void setTenrap(String tenrap) {
        this.tenrap = tenrap;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
