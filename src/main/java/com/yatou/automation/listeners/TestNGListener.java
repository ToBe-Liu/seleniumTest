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

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();//

    public static void setDriver(WebDriver driver) {
        TestNGListener.threadDriver.set(driver);
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        Logger.log("测试成功！");
        super.onTestSuccess(tr);
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        WebDriver driver = threadDriver.get();
        Long threadName = Thread.currentThread().getId();
        Logger.log("测试失败！");
        super.onTestFailure(tr);
        Reporter.setCurrentTestResult(tr);
        File screenShotFile = new File(new File("").getAbsolutePath() + "/Screenshots/" +threadName +"_"+tr.getName()+"_"+ DateUtil.getCurrentTime() + ".jpg");
        File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);//本地保存文件
        String screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);//测试报告显示base64
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
        Logger.log("测试跳过！");
        super.onTestSkipped(tr);
    }

    @Override
    public void onStart(ITestContext testContext) {
        Logger.log("测试开始！");
        super.onStart(testContext);
    }

    @Override
    public void onFinish(ITestContext testContext) {
        Logger.log("测试完成！");
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