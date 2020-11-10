package com.ngolamquangtin.appdatvexemphim.DTO;

public class MovieFavourite {
    private  int id;
    private String tenphim;
    private int thoigian;
    private String hinh;
    private String mota;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MovieFavourite(String tenphim, int thoigian, String hinh, String mota) {
        this.tenphim = tenphim;
        this.thoigian = thoigian;
        this.hinh = hinh;
        this.mota = mota;
    }

    public MovieFavourite() {
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

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
