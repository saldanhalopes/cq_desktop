/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author rafael
 */
public class DataHora {

    public static String getStringDate(Date d) {
        SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return time.format(d);
        } catch (Exception e) {
            return "";
        }
    }
    public static String getStringDate(Timestamp d) {
        SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return time.format(d);
        } catch (Exception e) {
            return "";
        }
    }

    public static String getStringDateTime(Date d) {
        SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return time.format(d);
        } catch (Exception e) {
            return "";
        }
    }

    public static String getStringDateTime(Timestamp d) {
        SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return time.format(d);
        } catch (Exception e) {
            return "";
        }
    }

    public static String DateTimeNow() {
        Date d = new Date();
        SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return time.format(d);
    }

    public static String DateNow() {
        Date d = new Date();
        SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy");
        return time.format(d);
    }

    public static String TimeNow() {
        Date d = new Date();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        return time.format(d);
    }

    public static Timestamp getTimestampDate(String data) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return new Timestamp(format.parse(data).getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public static Timestamp getTimestampDate(Date data) {
        try {
            return new Timestamp(data.getTime());
        } catch (Exception e) {
            return null;
        }
    }

    public static Timestamp getTimestampDateTime(String data) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return new Timestamp(format.parse(data).getTime());
        } catch (ParseException e) {
            return null;
        }
    }
    
    public static String DateFileNow() {
        Date d = new Date();
        SimpleDateFormat time = new SimpleDateFormat("(dd_MM_yyyy-HH_mm)");
        return time.format(d);
    }

}
