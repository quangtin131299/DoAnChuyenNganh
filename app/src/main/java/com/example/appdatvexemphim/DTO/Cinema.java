package com.example.appdatvexemphim.DTO;

public class Cinema {

    private String tenrap;
    private String diachi;

    public Cinema(String tenrap, String diachi) {
        this.tenrap = tenrap;
        this.diachi = diachi;
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


}
