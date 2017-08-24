package com.yatou.automation.test;

import com.yatou.automation.common.StoreAccountConstants;
import com.yatou.automation.listeners.TestNGListener;
import com.yatou.automation.store.LoginPage;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/8/13.
 */

public class LoginPageTest {
    public static WebDriver driver;

    @BeforeSuite
    public void setupSuite() {
        ChromeDriverManager.getInstance().useTaobaoMirror().setup();
        //System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--headless");
        //chromeOptions.addArguments("--disable-gpu");
        //chromeOptions.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(chromeOptions);
        System.out.println("ChromeTest1:"+driver);
        TestNGListener.setDriver(driver);
        driver.manage().window().maximize();

    }


    @AfterSuite
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(description = "登录测试",groups = {"login"})
    @Parameters({"username","password"})
    public void testLogin(String username, String password) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login(StoreAccountConstants.getField(username), StoreAccountConstants.getField(password));
    }

    /*@Test(description = "新增预约量尺测试")
    public void testAddMeasure() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login(StoreAccountConstants.ADVISER_USERNAME,StoreAccountConstants.ADVISER_PASSWORD);
    }*/

}