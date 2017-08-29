package com.yatou.automation.store;

import com.yatou.automation.common.FindBy;
import com.yatou.automation.common.StoreAccountConstants;
import com.yatou.automation.common.StoreConstants;
import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * 门店用户管理页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-18
 */
public class CustomerManagePage extends StoreMenu {

    @FindBy(xpath="//td/a[contains(@href,'/customer/detail/')]",description = "客户号")
    String customerNo="customerNo";//

    @FindBy(linkText="新增预约量尺",description = "新增预约量尺")
    String addMeasure="addMeasure";//

    @FindBy(id="search_address",description = "楼盘名")
    String buildingName="buildingName";//

    @FindBy(id="longLatitude",description = "楼盘坐标")
    String buildingGPS="buildingGPS";//

    @FindBy(linkText="获取定位",description = "获取定位")
    String getPosition="getPosition";//

    @FindBy(xpath="//div[contains(@id,'myModal') and contains(@style,'display: block;')]",description = "获取定位弹出层")
    String layer="layer";//

    @FindBy(xpath="//div[contains(@id,'myModal') and contains(@style,'display: none;')]",description = "获取定位弹出层消失")
    String layerDisplay="layerDisplay";//

    @FindBy(id="save",description = "保存")
    String save="save";//

    @FindBy(xpath="//button[@type='submit']",description = "确认添加")
    String submit="submit";//

    @FindBy(xpath="//div[contains(@class,'space-list') and contains(@style,'display: ;')]/label[1]",description = "量尺空间")
    String measureSpace="measureSpace";//

    @FindBy(name="receiveGid",description = "设计师")
    String designer="designer";//

    @FindBy(xpath="//td[text()='流水号']/following-sibling::*[1]",description = "流水号")
    String pipelineNo="pipelineNo";//新增预约量尺之后生成的流水号

    public CustomerManagePage(WebDriver driver){
        super(driver);
    }
    /**
     * 新增预约量尺
     *
     * @author LiuXingHai
     */
    public void addMeasure() throws InterruptedException {

        fluentFindAndClick(this.getClass(), customerNo);

        fluentFindAndClick(this.getClass(), addMeasure);

        fluentFindAndClick(this.getClass(), getPosition);
        fluentFind(this.getClass(), layer,null);

        fluentFindAndSetHtml(this.getClass(), buildingName,"自动测试");
        fluentFindAndSetHtml(this.getClass(), buildingGPS,"120.318571,30.157489");
        fluentFindAndClick(this.getClass(), save);

        fluentFind(this.getClass(), layerDisplay,null);

        fluentFindAndClick(this.getClass(), measureSpace);

        fluentFindAndSelectByValue(this.getClass(), designer, StoreAccountConstants.DESIGNER_USERNAME);

        fluentFindAndClick(this.getClass(), submit);

        fluentFindReturnMessage();

        StoreConstants.setPipelineNoS(fluentFind(this.getClass(), pipelineNo, null).getText().trim(),"流水号");

        Logger.log("新增预约量尺成功！");
    }
}
