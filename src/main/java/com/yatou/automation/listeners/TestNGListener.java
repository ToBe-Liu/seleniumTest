package com.yatou.automation.listeners;

import com.yatou.automation.utils.DateUtil;
import com.yatou.automation.utils.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;

/**
 * TestNG监听器
 *
 * @author LiuXingHai
 * @date 2017-08-16
 */
public class TestNGListener extends TestListenerAdapter {

    private static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        TestNGListener.driver = driver;
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        Logger.log("Test Success");
        super.onTestSuccess(tr);
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        Logger.log("Test Failure");
        super.onTestFailure(tr);
        Reporter.setCurrentTestResult(tr);
        File screenShotFile = new File(new File("").getAbsolutePath() + "/Screenshots/" + tr.getName()+"_"+ DateUtil.getCurrentTime() + ".jpg");
        File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        Reporter.log("<img src='data:image/jpg;base64,"+screenshotAs+"' hight='100' width='100'/>");
        try {
            // 拷贝截图文件到我们项目./Screenshots
            FileUtils.copyFile(src, screenShotFile);
        } catch (IOException e) {
            Logger.log(e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        Logger.log("Test Skipped");
        super.onTestSkipped(tr);
    }

    @Override
    public void onStart(ITestContext testContext) {
        Logger.log("Test Start");
        super.onStart(testContext);
    }

    @Override
    public void onFinish(ITestContext testContext) {
        Logger.log("Test Finish");
        super.onFinish(testContext);
        /*Iterator<ITestResult> listOfFailedTests = testContext.getFailedTests().getAllResults().iterator();
        while (listOfFailedTests.hasNext()) {
            ITestResult failedTest = listOfFailedTests.next();
            ITestNGMethod method = failedTest.getMethod();
            if (testContext.getFailedTests().getResults(method).size() > 1) {
                listOfFailedTests.remove();
            } else {
                if (testContext.getPassedTests().getResults(method).size() > 0) {
                    listOfFailedTests.remove();
                }
            }
        }*/

    }

}