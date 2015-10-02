package com.h2ord.corp.api.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chy on 14/11/14.
 */
public class DateUtil {

    private static final int FIRST_DAY = Calendar.MONDAY;

    public static final List<String> week_zn = Collections.unmodifiableList(Arrays.asList("星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"));

    public static List<Date> getWeekdays() {
        List<Date> weeks = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        setToFirstDay(calendar);
        for (int i = 0; i < 7; i++) {
            weeks.add(calendar.getTime());
            calendar.add(Calendar.DATE, 1);

        }

        return weeks;
    }

    private static void setToFirstDay(Calendar calendar) {
        while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
            calendar.add(Calendar.DATE, -1);

        }
    }

    public static boolean areSameDay(Date dateA, Date dateB) {
        Calendar calDateA = Calendar.getInstance();
        calDateA.setTime(dateA);

        Calendar calDateB = Calendar.getInstance();
        calDateB.setTime(dateB);

        return calDateA.get(Calendar.YEAR) == calDateB.get(Calendar.YEAR)
                && calDateA.get(Calendar.MONTH) == calDateB.get(Calendar.MONTH)
                && calDateA.get(Calendar.DAY_OF_MONTH) == calDateB.get(Calendar.DAY_OF_MONTH);
    }

    public static int daysOfTwo(Date fDate, Date oDate) {

        Calendar aCalendar = Calendar.getInstance();

        aCalendar.setTime(fDate);

        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

        aCalendar.setTime(oDate);

        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

        return day2 - day1;

    }

    /**
     * 将日期转换成规定字符串格式
     * @param date
     * @param formatStr
     * @return
     */
    public static String dateToDateStr(Date date,String formatStr){
        String dataStr=null;
        SimpleDateFormat format=new SimpleDateFormat(formatStr);
        dataStr= format.format(date);
        return dataStr;
    }

    public static Date getDateFormat(Date date,String formatStr){
        Date dateR=null;
        String dateStr=null;
        SimpleDateFormat format=new SimpleDateFormat(formatStr);
        dateStr=format.format(date);
        try {
            dateR=format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateR;
    }
    /**
     * 得到当前月的第一天
     * @return
     */
    public static String getTheCurrentMonthFirstDay(){
        String firstDay=null;
        Calendar c=Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        Date lastDate = c.getTime();
        firstDay=DateUtil.dateToDateStr(lastDate,"yyyy-MM-dd");
        return firstDay;
    }

    /**
     * 得到当前月的最后一天
     * @return
     */
    public static String getTheCurrentMonthLastDay(){
        String lastDay=null;
        Calendar c=Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date lastDate = c.getTime();
        lastDay=DateUtil.dateToDateStr(lastDate,"yyyy-MM-dd");
        return lastDay;
    }

    /**
     * 把时间字符串转换成规定格式时间
     * @param dateStr
     * @param formatStr
     * @return
     */
    public static Date getDateByDateStr(String dateStr,String formatStr){
        Date date=null;
        SimpleDateFormat format=new SimpleDateFormat(formatStr);
        try {
            date=format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }


}
