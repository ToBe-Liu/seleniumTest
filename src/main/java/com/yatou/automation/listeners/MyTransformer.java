package com.yatou.automation.listeners;

import com.yatou.automation.common.StoreConstants;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * testng方法注解监听器
 *
 * @author LiuXingHai
 * @date 2017-08-28
 */
public class MyTransformer implements IAnnotationTransformer {

    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {

        //每个客户新增的流水数
        if ("testAddMeasure".equals(testMethod.getName())) {
            annotation.setInvocationCount(Integer.parseInt(StoreConstants.ADD_PIPELINE_COUNT));
        }
        //每个流水新增的订单数
        if ("testAddOrder".equals(testMethod.getName())) {
            annotation.setInvocationCount(Integer.parseInt(StoreConstants.ADD_ORDER_COUNT));
        }

    }


}
