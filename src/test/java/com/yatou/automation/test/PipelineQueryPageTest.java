package com.yatou.automation.test;

import com.yatou.automation.store.Menu;
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
@Test(groups = {"customerManage"})
public class PipelineQueryPageTest {
    public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();//
    public static Menu menu ;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PipelineQueryPageTest.class);
    @BeforeClass
    public void setupClass() {
        threadDriver = LoginPageTest.threadDriver;
        menu = new Menu(threadDriver.get());
    }

    @Test(description = "流水查询测试",dataProvider = "pipelineNos",dataProviderClass = MeasureRegisterPageTest.class)
    public void testPipelineQuery(String pipelineNo) throws InterruptedException {
        WebDriver driver = threadDriver.get();
        logger.debug("流水查询测试driver:"+driver);
        menu.clickPipelineQuery().pipelineQuery(pipelineNo);
    }

}