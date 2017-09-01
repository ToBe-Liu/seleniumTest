package com.yatou.automation.test.store;

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

public class LoginTest {
    public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();//
    private LoginPage loginPage;

    @BeforeSuite
    public void setupSuite() {
        ChromeDriverManager.getInstance().useTaobaoMirror().setup();
        //System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--headless");
        //chromeOptions.addArguments("--disable-gpu");
        //chromeOptions.addArguments("--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        System.out.println("ConfigureTestDriver:"+driver);
        threadDriver.set(driver);
        TestNGListener.setDriver(driver);
        driver.manage().window().maximize();
    }

    @AfterSuite(alwaysRun = true)
    public void teardown() {
        WebDriver driver = threadDriver.get();
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeClass
    public void setupClass() {
        loginPage = new LoginPage(threadDriver.get());
    }

    @Test(description = "登录测试",groups = {"login"})
    @Parameters({"username","password"})
    public void testLogin(String username, String password) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        loginPage.login(StoreAccountConstants.getField(username), StoreAccountConstants.getField(password));
    }
}