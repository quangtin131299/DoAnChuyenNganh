package com.example.appdatvexemphim.DTO;

public class Seat {
    private int id;
    private String tenghe;
    private String trangthai;



    public Seat(int id, String tenghe, String trangthai) {
        this.id = id;
        this.tenghe = tenghe;
        this.trangthai = trangthai;
    }

    public Seat() {
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
