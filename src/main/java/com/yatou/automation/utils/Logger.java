package com.yatou.automation.utils;

import org.testng.Reporter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Report的封装
 *
 * @author LiuXingHai
 * @date 2017-08-14
 */
public class Logger{
    public static void log(String msg) {
        Reporter.log(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+":  " +msg);
    }
}
