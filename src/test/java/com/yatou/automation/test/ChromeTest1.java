package com.yatou.automation.test;

import com.yatou.automation.store.LoginPage;
import com.yatou.automation.utils.DateUtil;
import com.yatou.automation.utils.Logger;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/8/13.
 */
public class ChromeTest1 {
    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        ChromeDriverManager.getInstance().useTaobaoMirror().setup();
    }


    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(description = "登录测试")
    public void startBrowser() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--headless");
        //chromeOptions.addArguments("--disable-gpu");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login("530800010001", "11111111");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) throws Exception {
        if (!result.isSuccess())
            catchExceptions(result);
    }

    public void catchExceptions(ITestResult result) {
        System.out.println("result" + result);
        String methodName = result.getName();
        System.out.println(methodName);
        if (!result.isSuccess()) {
            Reporter.setCurrentTestResult(result);
            File screenShotFile = new File(new File("").getAbsolutePath() + "/Screenshots/" + result.getName()+"_"+ DateUtil.getCurrentTime() + ".jpg");
            File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            Reporter.log("<img src='"+screenShotFile+"' hight='100' width='100'/>");
            try {
                // 拷贝截图文件到我们项目./Screenshots
                FileUtils.copyFile(src, screenShotFile);
            } catch (IOException e) {
                Logger.log(e.getMessage());
            }

            /*File file = new File("");
            Reporter.setCurrentTestResult(result);
            System.out.println(file.getAbsolutePath());
            Reporter.log(file.getAbsolutePath());
            String filePath = file.getAbsolutePath();
            filePath  = filePath.replace("/opt/apache-tomcat-7.0.64/webapps","http://172.18.44.114:8080");
            Reporter.log("<img src='"+filePath+"/"+result.getName()+".jpg' hight='100' width='100'/>");
            int width = 100;
            int height = 100;
            String s = "这是一张测试图片";
            File screenShotFile = new File(file.getAbsolutePath()+"/"+result.getName()+".jpg");

            Font font = new Font("Serif", Font.BOLD, 10);
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = (Graphics2D)bi.getGraphics();
            g2.setBackground(Color.BLACK);
            g2.clearRect(0, 0, width, height);
            g2.setPaint(Color.RED);

            FontRenderContext context = g2.getFontRenderContext();
            Rectangle2D bounds = font.getStringBounds(s, context);
            double x = (width - bounds.getWidth()) / 2;
            double y = (height - bounds.getHeight()) / 2;
            double ascent = -bounds.getY();
            double baseY = y + ascent;

            g2.drawString(s, (int)x, (int)baseY);

            try {
                ImageIO.write(bi, "jpg", screenShotFile);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }
}