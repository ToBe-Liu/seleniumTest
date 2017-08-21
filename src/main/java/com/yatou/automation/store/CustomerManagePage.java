package com.yatou.automation.store;

import com.yatou.automation.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 门店用户管理页面
 *
 * @author LiuXingHai
 * @date 2017-08-18
 */
public class CustomerManagePage extends  Menu{

    @FindBy(id="logout")
    WebElement logout;//退出登录
    @FindBy(xpath="//td/a[contains(@href,'/customer/detail/')]")
    WebElement customerNo;//客户号
    @FindBy(linkText="新增预约量尺")
    WebElement addMeasure;//新增预约量尺
    @FindBy(id="search_address")
    WebElement buildingName;//楼盘名
    @FindBy(id="longLatitude")
    WebElement buildingGPS;//楼盘坐标
    @FindBy(linkText="获取定位")
    WebElement getPosition;//获取定位
    @FindBy(xpath="//div[contains(@id,'myModal') and contains(@style,'display: block;')]")
    WebElement layer;//获取定位弹出层
    @FindBy(id="BMapLib_PoiSearch")
    WebElement address;//详细地址
    @FindBy(id="BMapLib_sc_b0")
    WebElement search;//搜索
    @FindBy(xpath="//div[contains(@id,'BMapLib_resultArea') and contains(@style,'display: block;')]")
    WebElement searchResult;//搜索结果
    @FindBy(id="save")
    WebElement save;//保存
    @FindBy(xpath="//button[@type='submit']")
    WebElement submit;//确认添加
    @FindBy(xpath="//div[contains(@class,'space-list') and contains(@style,'display: ;')]/label[1]")
    WebElement measureSpace;//量尺空间
    @FindBy(xpath="//div[contains(@class,'space-list') and contains(@style,'display: ;')]/label[1]")
    WebElement designer;//设计师

    public CustomerManagePage(WebDriver driver){
        super(driver);
    }

    public void addMeasure() throws InterruptedException {
        WebDriver driver = threadDriver.get();
        click(this.customerNo);

        WebElement addMeasure = WaitUtil.fluentWait(driver, 10, "新增预约量尺",
                (driver1) -> driver.findElement(By.linkText("新增预约量尺")));
        click(addMeasure);

       WebElement getPosition = WaitUtil.fluentWait(driver, 10, "获取定位",
                (driver1) -> driver.findElement(By.linkText("获取定位")));
        click(getPosition);

        WebElement layer = WaitUtil.fluentWait(driver, 10, "获取定位弹出层",
                (driver1) -> driver.findElement(By.xpath("//div[contains(@id,'myModal') and contains(@style,'display: " +
                        "block;')]")));
        WebElement buildingName = WaitUtil.fluentWait(driver, 10, "楼盘名",
                (driver1) -> driver.findElement(By.id("search_address")));
        WebElement buildingGPS = WaitUtil.fluentWait(driver, 10, "楼盘坐标",
                (driver1) -> driver.findElement(By.id("longLatitude")));
        setHtml(buildingName,"自动测试");
        setHtml(buildingGPS,"120.318571,30.157489");

        WebElement save = WaitUtil.fluentWait(driver, 10, "保存",
                (driver1) -> driver.findElement(By.id("save")));
        click(save);

        WaitUtil.fluentWait(driver, 10, "获取定位弹出层消失",
                (driver1) -> driver.findElement(By.xpath("//div[contains(@id,'myModal') and contains(@style,'display: none;')]")));

        WebElement measureSpace = WaitUtil.fluentWait(driver, 10, "量尺空间",
                (driver1) -> driver.findElement(By.xpath("//div[contains(@class,'space-list') and contains(@style,'display: ;')]/label[1]")));
        click(measureSpace);

        WebElement submit = WaitUtil.fluentWait(driver, 10, "确认添加",
                (driver1) -> driver.findElement(By.xpath("//button[@type='submit']")));
        click(submit);

    }
}
