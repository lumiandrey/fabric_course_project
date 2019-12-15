package by.bsuir.ief.system.fabric.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConvert {

    public static Date fromTimeStamp(int timestamp) {

        java.util.Date date;
        Timestamp tm = new Timestamp(timestamp);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");

        date = new Date(tm.getTime());
        long lMilliSecs = date.getTime();
        Date sdate = new Date(lMilliSecs);

        return sdate;
    }

    public static java.util.Date fromToUtilDate(Timestamp timestamp){

        return new java.util.Date(timestamp.getTime());
    }

    public static int ToTimeStamp (String abc) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
        java.util.Date date = format.parse(abc);
        Timestamp tm = new Timestamp (date.getTime());
        return (int) tm.getTime();
    }

    public static Date UtilDateToSqlDate(java.util.Date udate){
        long lMilliSecs = udate.getTime();
        Date sdate = new Date(lMilliSecs);
        return sdate;
    }

    public static java.util.Date sqlDateto(Date udate) {

        long lMilliSecs = udate.getTime();
        Date sdate = new Date(lMilliSecs);

        return sdate;
    }

    public static java.util.Date StringToUtilDate(String str)
            throws ParseException{
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
        java.util.Date date = format.parse(str);
        return date;
    }

    public static Boolean validDate(String date)
    {
        try {
            StringToUtilDate(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

}
