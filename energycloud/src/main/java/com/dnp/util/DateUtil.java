package com.dnp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Copyright 2016 DONOPO Ltd. All rights reserved.
 * <p>
 * Remark   : 常用日期工具类
 * <p/>
 * Author   : Tim Mars
 * Project  : Quake
 * Date     : 6/22/2016
 */
public class DateUtil {

    public static Date strToDate(String _date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(_date);
    }

    public static String dateToStr(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String curDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return sdf.format(date);
    }

    public static int getYearTwoBit() {
        Calendar c = Calendar.getInstance();
        String year = String.valueOf(c.get(Calendar.YEAR));
        return Integer.parseInt(year.substring(2, 4));
    }

    public static int getDayOfYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_YEAR);
    }

    public static void main(String[] args) {
        System.out.println(getDayOfYear());
    }
}
