package com.example.appdatvexemphim.Util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

    public static final String LINK_LOGIN = "http://192.168.1.7:3000/login";
    public static final String LINK_REGISTER = "http://192.168.1.7:3000/register";
    public static final String LINK_LOADPHIMDANGCHIEU = "http://192.168.1.7:3000/loadphimdangchieu";
    public static final String LINK_LOADPHIMSAPCHIEU = "http://192.168.1.7:3000/loadphimsapchieu";
    public static final String LINK_LOADRAPPHIM = "http://192.168.1.7:3000/loadrapphim";
    public static final String LINK_MOVIEFAVOURITE = "http://192.168.1.7:3000/loadphimyeuthich";
    public static final String LINK_MOVIEDETAIL = "http://192.168.1.7:3000/loadchitietphim?idmovie=%d";
    public static final String LINK_LOADSUATCHIEUTHEORAP = "http://192.168.1.7:3000/loadxuatchieu?idrap=%d&idphim=%d&ngayhientai=%s";
    public static final String LINK_LOADDSVE = "http://192.168.1.7:3000/loadve?iduser=%d";
    public static final String LINK_LOADGHE = "http://192.168.1.7:3000/loadghe?rapphim=%d&idphim=%d&suatchieu=%s&ngaydathientai=%s";

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
