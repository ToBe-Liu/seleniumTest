package com.yatou.automation.store;

import com.yatou.automation.common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 门店左侧菜单
 *
 * @author LiuXingHai
 * @date 2017-08-15
 */
public class Menu extends BasePage{

    @FindBy(linkText="个人中心")
    WebElement userCenter;

    @FindBy(linkText="门店中心")
    WebElement storeCenter;

    @FindBy(linkText="客户管理")
    WebElement customerManage;

    @FindBy(linkText="量尺登记")
    WebElement mesureRegister;

    @FindBy(linkText="沟通登记")
    WebElement communicationRegister;

    @FindBy(linkText="合同登记")
    WebElement contractRegister;

    @FindBy(linkText="合同管理")
    WebElement contractManage;

    @FindBy(linkText="订单查询")
    WebElement orderQuery;

    @FindBy(linkText="补单查询")
    WebElement orderResupplyQuery;

    @FindBy(linkText="员工管理")
    WebElement employeeManage;

    @FindBy(linkText="流水查询")
    WebElement pipelineQuery;

    @FindBy(linkText="查房登记")
    WebElement wardRoundRegister;

    @FindBy(linkText="安装登记")
    WebElement installRegister;

    @FindBy(linkText="系统配置")
    WebElement systemConfigure;

    @FindBy(linkText="补单确认")
    WebElement orderResupplyConfirm;

    @FindBy(linkText="数据统计")
    WebElement dataStatistics;

    @FindBy(linkText="收货安装")
    WebElement deliveryInstall;

    @FindBy(linkText="门店统计")
    WebElement storeStatistics;


    /*
     * 构造方法
     */
    public Menu(WebDriver driver) {
        super(driver);
    }


}  