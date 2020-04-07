package com.hlhx.huluhuxian.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description:
 * @Author: wangjc
 * @CreateDate: 2020/4/2
 * @Version: 1.0
 */
public class DateUtil {
    /**
     * @Description:获取当前时间的前一天
     * @Author: wangjc
     * @Date: 2020/4/2
      * @param date
     java.lang.String
     **/
    public static String lastDay(Date date){
        Calendar ca=Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MINUTE,-20);
        Date date1=ca.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
        String time=sdf.format(date1);
        return time;
    }
    public static Date lastDayTime(Date date){
        Calendar ca=Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MINUTE,-20);
        Date date1=ca.getTime();
        return date1;
    }
    //查询时间段
    public static Date[] beginDateScope(){
        Date[] beginDateScope=new Date[2];
        Date date1=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String time1=lastDay(date1);
        try {
            Date endDate=sdf.parse(time1);
            String time2=lastDay(endDate);
            Date beginDate=sdf.parse(time2);
            beginDateScope[0]=beginDate;
            beginDateScope[1]=endDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return beginDateScope;
    }
    public static void main(String[] args) throws ParseException {
       String time= lastDay(new Date());
        System.out.println(beginDateScope());
    }
}
