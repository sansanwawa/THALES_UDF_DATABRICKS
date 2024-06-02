package com.thales.id.jakarta.cadp.lib.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * sandy.haryono@thalesgroup.com
 * Date Utilization for modifying a Date format
 *
 */
public class DateUtils {

    public static String modify(String date, int numberOfDate){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj = null;
        try {
            dateObj = formatter.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(dateObj);
            c.add(Calendar.DATE, numberOfDate);
            dateObj = c.getTime();
            return formatter.format(dateObj);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Date modify(Date date, int numberOfDate){
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, numberOfDate);
            return c.getTime();
    }

    public static long diff(Date start, Date end){
        long diffInMillies = Math.abs(end.getTime() - start.getTime());
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public static String diffWithPad(Date start, Date end, int size){
        String format = size < 10 ? "%0" + size + "d" : "%" +size+ "d";
        return String.format(format,diff(start,end));
    }







}
