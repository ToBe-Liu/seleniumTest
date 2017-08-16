package com.yatou.automation.test;

import com.yatou.automation.common.AccountConstants;
import com.yatou.automation.listeners.TestNGListener;
import com.yatou.automation.store.LoginPage;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/8/13.
 */
public class ChromeTest1 {
    private WebDriver driver;

    @BeforeClass
    public void setupClass() {
        ChromeDriverManager.getInstance().useTaobaoMirror().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(chromeOptions);
        TestNGListener.setDriver(driver);
        //driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait()
    }


    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(description = "登录测试")
    public void testLogin() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login(AccountConstants.ADVISER_USERNAME,AccountConstants.ADVISER_PASSWORD);
    }

}