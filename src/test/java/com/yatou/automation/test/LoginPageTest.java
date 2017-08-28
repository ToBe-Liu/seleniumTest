package com.yatou.automation.test;

import com.yatou.automation.common.StoreAccountConstants;
import com.yatou.automation.listeners.TestNGListener;
import com.yatou.automation.store.LoginPage;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

/**
 * 门店登录页面Test
 *
 * @author LiuXingHai
 * @date 2017-08-21
 */

public class LoginPageTest {
    public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();//
    @BeforeSuite
    public void setupSuite() {
        ChromeDriverManager.getInstance().useTaobaoMirror().setup();

    }
    @BeforeTest
    public void setupTest() {
        //ChromeDriverManager.getInstance().useTaobaoMirror().setup();
        //System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--headless");
        //chromeOptions.addArguments("--disable-gpu");
        //chromeOptions.addArguments("--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        System.out.println("ChromeTest1:"+driver);
        threadDriver.set(driver);
        TestNGListener.setDriver(driver);
        driver.manage().window().maximize();

    }


    @AfterTest(alwaysRun = true)
    public void teardown() {
        WebDriver driver = threadDriver.get();
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(description = "登录测试",groups = {"login"})
    @Parameters({"username","password"})
    public void testLogin(String username, String password) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        WebDriver driver = threadDriver.get();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(StoreAccountConstants.getField(username), StoreAccountConstants.getField(password));
    }

    /*@Test(description = "新增预约量尺测试")
    public void testAddMeasure() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login(StoreAccountConstants.ADVISER_USERNAME,StoreAccountConstants.ADVISER_PASSWORD);
    }*/

}