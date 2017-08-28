package com.yatou.automation.test;

import com.yatou.automation.common.StoreConstants;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Factory;

import java.util.List;

/**
 * 门店流水数据工厂
 *
 * @author LiuXingHai
 * @date 2017-08-27
 */

public class PipelineFactory {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PipelineFactory.class);

    @Factory
    public Object[] createInstances() throws Exception {
        List<String> pipelineNoS = StoreConstants.getPipelineNoS();
        Object[] result = new Object[pipelineNoS.size()];
        logger.debug("pipelineNos:"+pipelineNoS);
        for (int a = 0; a < pipelineNoS.size(); a++) {
            result[a] =  new PipelineQueryPageTest(pipelineNoS.get(a));
        }
        return result;
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