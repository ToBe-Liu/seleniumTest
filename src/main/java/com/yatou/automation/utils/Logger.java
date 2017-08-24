package com.yatou.automation.utils;

import com.yatou.automation.common.BasePage;
import org.slf4j.LoggerFactory;
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
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BasePage.class);

    public static void log(String msg) {

        Reporter.log(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+":  " +msg);
        logger.debug(msg);
    }
}
