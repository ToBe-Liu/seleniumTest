package com.yatou.automation.test.store;

import com.yatou.automation.store.CommunicationRegisterPage;
import com.yatou.automation.store.ContractRegisterPage;
import com.yatou.automation.store.MeasureRegisterPage;
import com.yatou.automation.store.StoreMenu;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 门店流水查询页面Test
 *
 * @author LiuXingHai
 * @date 2017-08-27
 */

public class PipelineTest {
    public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();//
    private StoreMenu storeMenu;
    private String pipelineNo;
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

    public PipelineTest(String pipelineNo){
        this.pipelineNo = pipelineNo;
    }

    @Test(description = "流水查询测试",groups = {"pipeline"})
    public void testPipelineQuery() throws InterruptedException {
        storeMenu.clickPipelineQuery().pipelineQuery(pipelineNo);
    }

    @Test(description = "量尺登记测试",groups = {"pipeline"})
    public void testMeasureRegister() throws InterruptedException {
        measureRegisterPage.measureRegister();
    }
    @Test(description = "上传量尺文件和方案文件测试",groups = {"pipeline"})
    public void testUploadMeasureFile() throws InterruptedException {
        measureRegisterPage.uploadAllFile();
    }

    @Test(description = "沟通登记测试",groups = {"pipeline"})
    public void testAddCommunicationRegister() throws InterruptedException {
        communicationRegisterPage.addCommunicationRegister();
    }
    @Test(description = "合同登记测试",groups = {"pipeline"})
    public void testAddContractRegister() throws InterruptedException {
        contractRegisterPage.addContractRegister();
    }
    @Test(description = "新增空间测试",groups = {"pipeline"})
    public void testAddSpace() throws InterruptedException {
        contractRegisterPage.addSpace();
    }
    @Test(description = "新增订单测试",groups = {"pipeline"})
    public void testAddOrder() throws InterruptedException {
        contractRegisterPage.addOrder();
        contractRegisterPage.uploadCreateOrderFile();
        Thread.sleep(3000);
    }


}