package com.yatou.automation.listeners;

import com.yatou.automation.common.TestNGRetry;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
/**
 * 失败重跑监听器
 *
 * @author LiuXingHai
 * @date 2017-08-16
 */
public class RetryListener implements IAnnotationTransformer {
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        IRetryAnalyzer retry = annotation.getRetryAnalyzer();
        if (retry == null) {
            annotation.setRetryAnalyzer(TestNGRetry.class);
        }
    }   
}