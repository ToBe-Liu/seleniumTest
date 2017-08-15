package com.yatou.automation.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 日期工具
 *
 * @author LiuXingHai
 * @date 2017-08-15
 */
public class DateUtil {


    /**
     * 获取当前时间
     */
    public static String getCurrentTime(){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String currentTime=sdf.format(date);
        return currentTime;
    }
}
