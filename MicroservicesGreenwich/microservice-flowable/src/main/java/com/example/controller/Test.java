package com.example.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws ParseException {
//        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//
//        Date date = new Date(format.parse("2019/06/05 22:00:00").getTime());
//        System.out.println(date);


        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        String str = null;

        // String转Date
        str = "2019/06/05 22:00:00";
        try {
            date = format.parse(str);  // Thu Jan 18 00:00:00 CST 2007
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
