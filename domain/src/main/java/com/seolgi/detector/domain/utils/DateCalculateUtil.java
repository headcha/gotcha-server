package com.seolgi.detector.domain.utils;

import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateCalculateUtil extends DateUtils {
    private static int TIME_MINUTE = 60 * 1000;
    private static int TIME_HOUR = 60 * TIME_MINUTE;
    private static int TIME_DAY = 24 * TIME_HOUR;

    public static Date transformTo(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.parse(date);
    }

    public static String transformTo(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    public static String transformTo(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date transformTo(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(date);
    }

    public static Date setTime(Date date, String time, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd " + time);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd " + format);
        String startDate = sdf.format(date);
        return sdf2.parse(startDate);
    }

    public static long diffDays(Date begin, Date end) {
        long diff = end.getTime() - begin.getTime();
        long diffDays = diff / TIME_DAY;

        return diffDays;
    }

    public static long diffHours(Date begin, Date end) {
        long diff = end.getTime() - begin.getTime();
        long diffDays = diff / TIME_HOUR;

        return diffDays;
    }

    public static long diffMinutes(Date begin, Date end) {
        long diff = end.getTime() - begin.getTime();
        long diffDays = diff / TIME_MINUTE;

        return diffDays;
    }

    public static double diffMinutesCeil(Date begin, Date end) {
        long diff = end.getTime() - begin.getTime();
        double difMinutes = Math.ceil((double) diff / TIME_MINUTE);

        return difMinutes;
    }


    public static long diffMinutes(long startMillis, long endMillis) {
        long diff = endMillis - startMillis;
        long diffDays = diff / TIME_MINUTE;

        return diffDays;
    }

    public static List<Date> splitByDay(Date begin, Date end) {
        Date current = begin;
        long diffDays = diffDays(begin, end);
        List<Date> days = new ArrayList<>();
        if (diffDays > 0) {
            days.add(current);
            for (int i = 1; i < diffDays; i++) {
                current = DateUtils.addDays(current, 1);
                days.add(current);
            }
        }
        return days;
    }

    public static Date adjustTime(Date date, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    public static Date adjustTime(Date date, int hour, int minute, int second, int millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar.getTime();
    }

    public static Date getFirstTime(Date date) {
        return adjustTime(date, 0, 0, 0, 0);
    }

    public static Date getLastTime(Date date) {
        return adjustTime(date, 24, 0, 0, 0);
    }

    public static int getDayOfWeek(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static int getHour(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        return calendar.get(Calendar.MINUTE);
    }

    public static Date adjustHour(Date day, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    public static Date clone(Date day, int hour) {
        Date newOne = (Date) day.clone();
        return adjustHour(newOne, hour);
    }

    public static boolean between(Date today, Date startAt, Date endAt) {
        return !today.before(startAt) && (today.before(endAt));
    }

    public static Date getDateOfMonthAgo(Date date, int ago) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, ago);
        return calendar.getTime();
    }

    public static long diffSecond(Date begin, Date end) {
        if (begin == null || end == null)
            return 0;

        long diff = end.getTime() - begin.getTime();
        long second = diff / (1000);

        return second;
    }

    public static long diffMillisecond(Date begin, Date end) {
        if (begin == null || end == null)
            return 0;

        return end.getTime() - begin.getTime();
    }

    public static Date ceilMaxTenMinute(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int minute = cal.get(Calendar.MINUTE);
        if (minute % 10 == 0)
            minute += 10;

        int ceilMinute = (int)(Math.ceil((double)minute / 10) * 10);
        cal.set(Calendar.MINUTE, ceilMinute);

        return new Date(cal.getTimeInMillis());
    }
}
