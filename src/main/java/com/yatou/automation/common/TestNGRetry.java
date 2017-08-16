package com.yatou.automation.common;

import com.yatou.automation.utils.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
/**
 * 失败重跑实现类
 *
 * @author LiuXingHai
 * @date 2017-08-16
 */
public class TestNGRetry implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxRetryCount=2;


    public boolean retry(ITestResult result) {
        if (retryCount <= maxRetryCount) {
            String message = "running retry for  '" + result.getName() + "' on class " + 
                                       this.getClass().getName() + " Retrying " + retryCount + " times";
            Logger.log(message);
            retryCount++;
            return true;
        }
        return false;
    }
}