package com.ngolamquangtin.appdatvexemphim.Util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

    public static final String LINK_LOGIN = "https://serverappdatve.herokuapp.com/login";
    public static final String LINK_REGISTER = "https://serverappdatve.herokuapp.com/register";
    public static final String LINK_LOADPHIMDANGCHIEU = "https://serverappdatve.herokuapp.com/loadphimdangchieu";
    public static final String LINK_LOADPHIMSAPCHIEU = "https://serverappdatve.herokuapp.com/loadphimsapchieu";
    public static final String LINK_LOADRAPPHIM = "https://serverappdatve.herokuapp.com/loadrapphim";
    public static final String LINK_MOVIEFAVOURITE = "https://serverappdatve.herokuapp.com/loadphimyeuthich";
    public static final String LINK_MOVIEDETAIL = "https://serverappdatve.herokuapp.com/loadchitietphim?idmovie=%d";
    public static final String LINK_LOADSUATCHIEUTHEORAP = "https://serverappdatve.herokuapp.com/loadxuatchieu?idrap=%d&idphim=%d&ngayhientai=%s";
    public static final String LINK_LOADDSVE = "https://serverappdatve.herokuapp.com/loadve?iduser=%d";
    public static final String LINK_LOADGHE = "https://serverappdatve.herokuapp.com/loadghe?rapphim=%d&idphim=%d&suatchieu=%s&ngaydathientai=%s";
    public static final String LINK_LOADPHONG = "https://serverappdatve.herokuapp.com/loadphong?suatchieu=%s&idphim=%d&idrap=%d&ngayhientai=%s";
    public static final String LINK_UPDATEUSER = "https://serverappdatve.herokuapp.com/capnhatthongtinkhach";
<<<<<<< HEAD
    public static final String LINK_DATVE = "https://serverappdatve.herokuapp.com/datvephim?ngaydat=%s&idsuat=%d&idghe=%d&idphim=%d&idkhachhang=%d&idrap=%d&&status=%s&idphong=%d";
=======
    public static final String LINK_DATVE = "https://serverappdatve.herokuapp.com/datvephim?ngaydat=%s&idsuat=%d&idghe=%d&idphim=%d&idkhachhang=%d&idrap=%d&status=%s&idphong=%d";
    public static final String LINK_UPDATESTATUSVE = "https://serverappdatve.herokuapp.com/capnhattrangthaidatve?idphong=%d&idghe=%d&ngaydat=%s&idsuat=%d&idve=%d&idhoadon=%d";
>>>>>>> 6a9b0bba5bd7a5f5641eebea5ea67688c0a31a9e
    public static  final  String LINK_UPDATEPASSWORDUSER = "http://192.168.0.123:3000/updatepassuser";

    public static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            return input;

        }
    }
}
