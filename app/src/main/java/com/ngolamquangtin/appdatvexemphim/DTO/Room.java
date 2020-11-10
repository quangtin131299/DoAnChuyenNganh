package com.ngolamquangtin.appdatvexemphim.DTO;

public class Room {
    private int id;
    private String tenphong;
    private String thoigian;

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public Room(int id, String tenphong) {
        this.id = id;
        this.tenphong = tenphong;
    }

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenphong() {
        return tenphong;
    }

    public void setTenphong(String tenphong) {
        this.tenphong = tenphong;
    }
}
