package com.yatou.automation.test.store;

import com.yatou.automation.common.StoreConstants;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 门店流水数据工厂
 *
 * @author LiuXingHai
 * @date 2017-08-27
 */

public class PipelineFactory {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PipelineFactory.class);

    @Factory(dataProvider = "instance")
    public Object[] createInstances() throws Exception {
        return StoreConstants.result.toArray();
    }
    @DataProvider(name = "instance")
    public static Iterator<Object[]> getInstances(){
        ArrayList<Object[]> alist=new ArrayList<Object[]>();
        for (int i = 0; i < StoreConstants.result.size(); i++) {
            alist.add(new Object[]{StoreConstants.result.get(i)});
        }
        return alist.iterator();
    }
   /* @Factory
    @Parameters({"className"})
    public Object[] createInstances(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(String.class);

        List<String> pipelineNoS = StoreConstants.getPipelineNoS();
        Object[] result = new Object[pipelineNoS.size()];
        logger.debug("pipelineNos:"+pipelineNoS);
        for (int a = 0; a < pipelineNoS.size(); a++) {
            result[a] =  declaredConstructor.newInstance(pipelineNoS.get(a));
        }
        return result;
    }*/

}