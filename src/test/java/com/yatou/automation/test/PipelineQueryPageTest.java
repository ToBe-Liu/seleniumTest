package com.yatou.automation.test;

import com.yatou.automation.store.ContractRegisterPage;
import com.yatou.automation.store.StoreMenu;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 门店流水查询页面Test
 *
 * @author LiuXingHai
 * @date 2017-08-27
 */

public class PipelineQueryPageTest {
    public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();//
    public static StoreMenu storeMenu;
    public String pipelineNo;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PipelineQueryPageTest.class);
    @BeforeClass
    public void setupClass() {
        threadDriver = LoginPageTest.threadDriver;
        storeMenu = new StoreMenu(threadDriver.get());
    }

    public PipelineQueryPageTest(String pipelineNo){
        this.pipelineNo = pipelineNo;
    }

    @Test(description = "流水查询测试")
    public void testPipelineQuery() throws InterruptedException {
        WebDriver driver = threadDriver.get();
        logger.debug("流水查询测试driver:"+driver);
        storeMenu.clickPipelineQuery().pipelineQuery(pipelineNo);
    }

    @Test(description = "量尺登记测试")
    public void testMeasureRegister() throws InterruptedException {
        WebDriver driver = threadDriver.get();
        logger.debug("量尺登记测试driver:"+driver);
        //MeasureRegisterPage measureRegisterPage = new MeasureRegisterPage(driver);
        //measureRegisterPage.measureRegister();
    }
    @Test(description = "上传量尺文件和方案文件测试")
    public void testUploadMeasureFile() throws InterruptedException {
        WebDriver driver = threadDriver.get();
        logger.debug("上传量尺文件和方案文件测试:"+driver);
        //MeasureRegisterPage measureRegisterPage = new MeasureRegisterPage(driver);
        //measureRegisterPage.uploadAllFile();
    }

    @Test(description = "沟通登记测试")
    public void testAddCommunicationRegister() throws InterruptedException {
        WebDriver driver = threadDriver.get();
        logger.debug("沟通登记测试driver:"+driver);
        //new CommunicationRegisterPage(driver).addCommunicationRegister();
    }
    @Test(description = "合同登记测试")
    public void testAddContractRegister() throws InterruptedException {
        WebDriver driver = threadDriver.get();
        logger.debug("合同登记测试driver:"+driver);
        //storeMenu.clickContractRegister().addContractRegister();
    }
    @Test(description = "新增空间测试")
    public void testAddSpace() throws InterruptedException {
        WebDriver driver = threadDriver.get();
        logger.debug("新增空间测试driver:"+driver);
        //storeMenu.clickContractRegister().addSpace();
    }
    @Test(description = "新增订单测试")
    public void testAddOrder() throws InterruptedException {
        WebDriver driver = threadDriver.get();
        logger.debug("新增订单测试driver:"+driver);
        ContractRegisterPage contractRegisterPage = new ContractRegisterPage(driver);
        contractRegisterPage.addOrder();
        contractRegisterPage.uploadCreateOrderFile();
    }

  /*  @DataProvider(name = "pipelineNos")
    public static Object[][] pipelineNos() throws Exception {
        List<String> pipelineNoS = StoreConstants.getPipelineNoS();
        Object[][] results = new Object[pipelineNoS.size()][];
        logger.debug("pipelineNos:"+pipelineNoS);
        for (int a = 0; a < pipelineNoS.size(); a++) {
            results[a] = new Object[]{pipelineNoS.get(a)};
        }
        return results;
    }*/

}