package com.example.appdatvexemphim.DTO;

import java.io.Serializable;

public class Ticker implements Serializable {
    private int id;
    private String tenrap;
    private String ngaydat;
    private String tenphong;
    private String tenphim;
    private String tenghe;
    private String gio;

    private String diachirap;
    private String hinhphim;
    private String thoigianphim;

    public String getDiachirap() {
        return diachirap;
    }

    public void setDiachirap(String diachirap) {
        this.diachirap = diachirap;
    }

    public String getHinhphim() {
        return hinhphim;
    }

    public void setHinhphim(String hinhphim) {
        this.hinhphim = hinhphim;
    }

    public String getThoigianphim() {
        return thoigianphim;
    }

    public void setThoigianphim(String thoigianphim) {
        this.thoigianphim = thoigianphim;
    }

    public Ticker() {
    }

    public Ticker(int id, String tenrap, String ngaydat, String tenphim, String tenghe, String gio) {
        this.id = id;
        this.tenrap = tenrap;
        this.ngaydat = ngaydat;
        this.tenphim = tenphim;
        this.tenghe = tenghe;
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

    public String getTenrap() {
        return tenrap;
    }

    public void setTenrap(String tenrap) {
        this.tenrap = tenrap;
    }

    public String getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(String ngaydat) {
        this.ngaydat = ngaydat;
    }

    public String getTenphim() {
        return tenphim;
    }

    public void setTenphim(String tenphim) {
        this.tenphim = tenphim;
    }

    public String getTenghe() {
        return tenghe;
    }

    public void setTenghe(String tenghe) {
        this.tenghe = tenghe;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }
}
