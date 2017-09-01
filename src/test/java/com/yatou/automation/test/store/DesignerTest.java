package com.yatou.automation.test.store;

import com.yatou.automation.common.StoreConstants;
import com.yatou.automation.store.CommunicationRegisterPage;
import com.yatou.automation.store.ContractRegisterPage;
import com.yatou.automation.store.MeasureRegisterPage;
import com.yatou.automation.store.StoreMenu;
import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * 门店设计师Test
 *
 * @author LiuXingHai
 * @date 2017-09-01
 */

public class DesignerTest {
    public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();//
    private StoreMenu storeMenu;
    private MeasureRegisterPage measureRegisterPage;
    private CommunicationRegisterPage communicationRegisterPage;
    private ContractRegisterPage contractRegisterPage;

    @BeforeClass
    public void setupClass() {
        threadDriver = LoginTest.threadDriver;
        storeMenu = new StoreMenu(threadDriver.get());
        measureRegisterPage = new MeasureRegisterPage(threadDriver.get());
        communicationRegisterPage = new CommunicationRegisterPage(threadDriver.get());
        contractRegisterPage = new ContractRegisterPage(threadDriver.get());
    }


    @Test(description = "一个流水测试",groups = {"designer"})
    public void testOnePipeline() throws InterruptedException {
        List<String> pipelineNoS = StoreConstants.getPipelineNoS();
        pipelineNoS.forEach((p)->{
            try {
                storeMenu.clickPipelineQuery().pipelineQuery(p);
                measureRegisterPage.measureRegister();
                measureRegisterPage.uploadAllFile();
                communicationRegisterPage.addCommunicationRegister();
                contractRegisterPage.addContractRegister();
                contractRegisterPage.addSpace();
                Integer addOrderCount = Integer.parseInt(StoreConstants.ADD_ORDER_COUNT);
                if(addOrderCount != null){
                    for (int i = 0; i < addOrderCount; i++) {
                        contractRegisterPage.addOrder();
                        contractRegisterPage.uploadCreateOrderFile();
                    }
                }
            } catch(InterruptedException e) {
                Logger.log(e.getMessage());
                e.printStackTrace();
            }
        });
    }


}