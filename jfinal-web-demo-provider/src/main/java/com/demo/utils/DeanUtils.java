package com.demo.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DeanUtils {

    public static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");

    public static String getRandom(int bound) {
        return String.valueOf(new Random().nextInt(bound));
    }

    public static Timestamp getTimeStamp() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    public static void main(String[] args) {
        System.out.println(getTimeStamp());
    }

}
