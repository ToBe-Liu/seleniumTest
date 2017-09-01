package com.yatou.automation.test.store;

import com.yatou.automation.store.StoreMenu;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 门店量尺登记页面Test
 *
 * @author LiuXingHai
 * @date 2017-08-22
 */
@Test(groups = {"measureRegister"})
public class MeasureRegisterPageTest {
    public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();//
    public static StoreMenu storeMenu;
    public String pipelineNo;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MeasureRegisterPageTest.class);
    @BeforeClass
    public void setupClass() {
        threadDriver = LoginTest.threadDriver;
        storeMenu = new StoreMenu(threadDriver.get());
    }

    public MeasureRegisterPageTest(String pipelineNo){
        this.pipelineNo = pipelineNo;
    }

    @Test(description = "流水查询测试")
    public void testPipelineQuery() throws InterruptedException {
        WebDriver driver = threadDriver.get();
        logger.debug("流水查询测试driver:"+driver);
        //storeMenu.clickPipelineQuery().pipelineQuery(pipelineNo);
        System.out.println(pipelineNo);
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


}