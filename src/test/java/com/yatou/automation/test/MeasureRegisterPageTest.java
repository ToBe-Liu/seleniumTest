package com.yatou.automation.test;

import com.yatou.automation.store.MeasureRegisterPage;
import com.yatou.automation.store.Menu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
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
    public static WebDriver driver ;
    public static Menu menu ;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MeasureRegisterPageTest.class);
    @BeforeClass
    public void setupClass() {
        driver = LoginPageTest.driver;
        menu = PageFactory.initElements(driver, Menu.class);
    }

    @Test(description = "量尺登记测试")
    public void testAddMeasure() throws InterruptedException {
        logger.debug("量尺登记测试driver:"+driver);
        MeasureRegisterPage measureRegisterPage = menu.clickMeasureRegister();
        measureRegisterPage.measureRegister();
        measureRegisterPage.uploadAllFile();
    }
    @Test(description = "上传量尺文件测试")
    public void testUploadMeasureFile() throws InterruptedException {
        logger.debug("上传量尺文件测试driver:"+driver);
        menu.clickMeasureRegister().uploadMeasureFile();
    }
}