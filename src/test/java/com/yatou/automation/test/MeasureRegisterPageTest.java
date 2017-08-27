package com.yatou.automation.test;

import com.yatou.automation.common.StoreConstants;
import com.yatou.automation.store.Menu;
import com.yatou.automation.store.PipelineQueryPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

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
        driver = LoginPageTest.threadDriver.get();
        menu = PageFactory.initElements(driver, Menu.class);
    }

    @Test(description = "量尺登记测试",dataProvider = "pipelineNos")
    public void testMeasureRegister(String pipelineNo) throws InterruptedException {
        logger.debug("量尺登记测试driver:"+driver);
        PipelineQueryPage pipelineQueryPage = menu.clickPipelineQuery();
        pipelineQueryPage.pipelineQuery(pipelineNo);
//        MeasureRegisterPage measureRegisterPage = new MeasureRegisterPage(driver);
//        measureRegisterPage.measureRegister();
//        measureRegisterPage.uploadAllFile();
    }
    @Test(description = "上传量尺文件测试")
    public void testUploadMeasureFile() throws InterruptedException {
        logger.debug("上传量尺文件测试driver:"+driver);
        menu.clickMeasureRegister().uploadMeasureFile();
    }
    @DataProvider(name = "pipelineNos")
    public static Object[][] pipelineNos() throws Exception {
        List<String> pipelineNoS = StoreConstants.getPipelineNoS();
        Object[][] results = new Object[pipelineNoS.size()][];
        logger.debug("pipelineNos:"+pipelineNoS);
        for (int a = 0; a < pipelineNoS.size(); a++) {
            results[a] = new Object[]{pipelineNoS.get(a)};
        }
        return results;
    }

}