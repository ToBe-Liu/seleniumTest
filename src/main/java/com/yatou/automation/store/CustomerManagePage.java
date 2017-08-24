package com.yatou.automation.store;

import com.yatou.automation.common.AccountConstants;
import com.yatou.automation.utils.Logger;
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
    @FindBy(xpath="//div[contains(@id,'myModal') and contains(@style,'display: none;')]")
    WebElement layerDisplay;//获取定位弹出层消失
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
    @FindBy(name="receiveGid")
    WebElement designer;//设计师
    public CustomerManagePage(WebDriver driver){
        super(driver);
    }
    /**
     * 新增预约量尺
     *
     * @author LiuXingHai
     */
    public void addMeasure() throws InterruptedException {

        fluentFindAndClick(findElement(CustomerManagePage.class, "customerNo"),"客户号");

        fluentFindAndClick(findElement(CustomerManagePage.class, "addMeasure"),"新增预约量尺");

        fluentFindAndClick(findElement(CustomerManagePage.class, "getPosition"),"获取定位");
        fluentFind(findElement(CustomerManagePage.class, "layer"),"获取定位弹出层");

        fluentFindAndSetHtml(findElement(CustomerManagePage.class, "buildingName"),"自动测试","楼盘名");
        fluentFindAndSetHtml(findElement(CustomerManagePage.class, "buildingGPS"),"120.318571,30.157489","楼盘坐标");
        fluentFindAndClick(findElement(CustomerManagePage.class, "save"),"保存");

        fluentFind(findElement(CustomerManagePage.class, "layerDisplay"),"获取定位弹出层消失");

        fluentFindAndClick(findElement(CustomerManagePage.class, "measureSpace"),"量尺空间");
        fluentFindAndSelectByValue(findElement(CustomerManagePage.class, "designer"), AccountConstants.DESIGNER_USERNAME,"设计师");
        fluentFindAndClick(findElement(CustomerManagePage.class, "submit"),"确认添加");

        Logger.log("新增预约量尺成功！");
    }
}
