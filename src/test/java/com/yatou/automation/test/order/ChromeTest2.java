package com.yatou.automation.test.order;

import com.yatou.automation.listeners.TestNGListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/8/13.
 */
public class ChromeTest2 {
    private WebDriver driver;

    @BeforeClass
    public void setupClass() {
        //ChromeDriverManager.getInstance().useTaobaoMirror().setup();
        System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--headless");
        //chromeOptions.addArguments("--disable-gpu");
        //chromeOptions.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(chromeOptions);
        System.out.println("ChromeTest2:"+driver);
        TestNGListener.setDriver(driver);
        driver.manage().window().maximize();

    }


    @AfterClass
    public void teardown() {
        if (driver != null) {
            //driver.quit();
        }
    }

    @Test(description = "新增预约量尺测试")
    @Parameters({"username","password"})
    public void testLogin(String username, String password) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
       /* driver.get(StoreConstants.LOGINURL);
        Logger.log("打开门店首页！");
        WebElement userNo = WaitUtil.fluentWait(driver, 10, "10秒内没有找到门店首页！", (driver1) -> driver.findElement(By.name("userNo")));
        if (userNo != null) {
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            System.out.println("ChromeTest1:"+loginPage);
            loginPage.login(username,password);
        }*/

    }

    /*@Test(description = "新增预约量尺测试")
    public void testAddMeasure() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login(StoreAccountConstants.ADVISER_USERNAME,StoreAccountConstants.ADVISER_PASSWORD);
    }*/

}