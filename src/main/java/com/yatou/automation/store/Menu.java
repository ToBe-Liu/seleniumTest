package com.yatou.automation.store;

import com.yatou.automation.common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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



    public UserCenterPage getUserCenter() {
        return null;
    }

    public StoreCenterPage  getStoreCenter() {
        return null;
    }

    public CustomerManagePage clickCustomerManage() {
        WebDriver driver = threadDriver.get();
        System.out.println("clickCustomerManageDriver:"+driver);
        click(customerManage);
        return PageFactory.initElements(driver, CustomerManagePage.class);
    }

    public WebElement getMesureRegister() {
        return mesureRegister;
    }

    public WebElement getCommunicationRegister() {
        return communicationRegister;
    }

    public WebElement getContractRegister() {
        return contractRegister;
    }

    public WebElement getContractManage() {
        return contractManage;
    }

    public WebElement getOrderQuery() {
        return orderQuery;
    }

    public WebElement getOrderResupplyQuery() {
        return orderResupplyQuery;
    }

    public WebElement getEmployeeManage() {
        return employeeManage;
    }

    public WebElement getPipelineQuery() {
        return pipelineQuery;
    }

    public WebElement getWardRoundRegister() {
        return wardRoundRegister;
    }

    public WebElement getInstallRegister() {
        return installRegister;
    }

    public WebElement getSystemConfigure() {
        return systemConfigure;
    }

    public WebElement getOrderResupplyConfirm() {
        return orderResupplyConfirm;
    }

    public WebElement getDataStatistics() {
        return dataStatistics;
    }

    public WebElement getDeliveryInstall() {
        return deliveryInstall;
    }

    public WebElement getStoreStatistics() {
        return storeStatistics;
    }
}