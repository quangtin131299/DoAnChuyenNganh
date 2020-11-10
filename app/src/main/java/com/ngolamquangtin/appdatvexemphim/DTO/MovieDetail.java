package com.ngolamquangtin.appdatvexemphim.DTO;

public class MovieDetail {
    private int id;
    private String tenphim;
    private int thoigian;
    private String mota;
    private String hinh;
    private String tenloai;
    private String ngaykhoichieu;
    private String trailer;

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public MovieDetail() {
    }

    public int getThoigian() {
        return thoigian;
    }

    public void setThoigian(int thoigian) {
        this.thoigian = thoigian;
    }

    public MovieDetail(int id, String tenphim, int thoigian, String mota, String hinh, String tenloai, String ngaykhoichieu, String trailer) {
        this.id = id;
        this.tenphim = tenphim;
        this.thoigian = thoigian;
        this.mota = mota;
        this.hinh = hinh;
        this.tenloai = tenloai;
        this.ngaykhoichieu = ngaykhoichieu;
        this.trailer = trailer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenphim() {
        return tenphim;
    }

    public void setTenphim(String tenphim) {
        this.tenphim = tenphim;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public String getNgaykhoichieu() {
        return ngaykhoichieu;
    }

    public void setNgaykhoichieu(String ngaykhoichieu) {
        this.ngaykhoichieu = ngaykhoichieu;
    }
}
