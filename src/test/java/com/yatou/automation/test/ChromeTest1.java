package com.yatou.automation.test;

import com.yatou.automation.store.LoginPage;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/8/13.
 */
public class ChromeTest1 {
    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        ChromeDriverManager.getInstance().useTaobaoMirror().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
    /**
     *   登录测试
     * @author LiuXingHai
     */
    @Test
    @Parameters("Browser")
    public  void startBrowser(String browser){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");
        ChromeDriverManager.getInstance().useTaobaoMirror().setup();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login("530800010001","11111111");
        driver.quit();
    }
}
