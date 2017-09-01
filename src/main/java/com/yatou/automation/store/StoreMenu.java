package com.yatou.automation.store;

import com.yatou.automation.common.BasePage;
import com.yatou.automation.common.FindBy;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;

/**
 * 门店左侧菜单（外加一些页面通用的元素）
 *
 * @author LiuXingHai
 * @date 2017-08-15
 */
public class StoreMenu extends BasePage{

    @FindBy(linkText="个人中心",description = "个人中心")
    String userCenter="userCenter";

    @FindBy(linkText="门店中心",description = "门店中心")
    String storeCenter="storeCenter";

    @FindBy(linkText="客户管理",description = "客户管理")
    String customerManage="customerManage";

    @FindBy(linkText="量尺登记",description = "量尺登记")
    String measureRegister="measureRegister";

    @FindBy(linkText="沟通登记",description = "沟通登记")
    String communicationRegister="communicationRegister";

    @FindBy(linkText="合同登记",description = "合同登记")
    String contractRegister="contractRegister";

    @FindBy(linkText="合同管理",description = "合同管理")
    String contractManage="contractManage";

    @FindBy(linkText="订单查询",description = "订单查询")
    String orderQuery="orderQuery";

    @FindBy(linkText="补单查询",description = "补单查询")
    String orderResupplyQuery="orderResupplyQuery";

    @FindBy(linkText="员工管理",description = "员工管理")
    String employeeManage="employeeManage";

    @FindBy(linkText="流水查询",description = "流水查询")
    String pipelineQuery="pipelineQuery";

    @FindBy(linkText="查房登记",description = "查房登记")
    String wardRoundRegister="wardRoundRegister";

    @FindBy(linkText="安装登记",description = "安装登记")
    String installRegister="installRegister";

    @FindBy(linkText="系统配置",description = "系统配置")
    String systemConfigure="systemConfigure";

    @FindBy(linkText="补单确认",description = "补单确认")
    String orderResupplyConfirm="orderResupplyConfirm";

    @FindBy(linkText="数据统计",description = "数据统计")
    String dataStatistics="dataStatistics";

    @FindBy(linkText="收货安装",description = "收货安装")
    String deliveryInstall="deliveryInstall";

    @FindBy(linkText="门店统计",description = "门店统计")
    String storeStatistics="storeStatistics";

    @FindBy(id="logout",description = "退出登录")
    String logout="logout";//

    @FindBy(xpath="//div[starts-with(@id,'smallbox')]",description = "操作返回信息")
    String message="message";//

    @FindBy(xpath="//div[contains(@class,'flatpickr-calendar')]", description = "日期弹出层")
    String dateLayer="dateLayer";

    @FindBy(id="header", description = "头部")
    String header="header";

    @FindBy(className="layui-layer-btn0", description = "确定")
    String confirm="confirm";

    @FindBy(className="layui-layer-shade",description = "弹出遮罩层")
    String layerShade="layerShade";//

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(StoreMenu.class);
    /*
     * 构造方法
     */
    public StoreMenu(WebDriver driver) {
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
        logger.debug("clickCustomerManageDriver:"+driver);
        fluentFindAndClick(StoreMenu.class,customerManage);
        return new CustomerManagePage(driver);
    }

    public MeasureRegisterPage clickMeasureRegister() {
        WebDriver driver = threadDriver.get();
        logger.debug("clickMeasureRegisterDriver:"+driver);
        fluentFindAndClick(StoreMenu.class,measureRegister);
        return new MeasureRegisterPage(driver);
    }

    public CommunicationRegisterPage clickCommunicationRegister() {
        WebDriver driver = threadDriver.get();
        logger.debug("clickCommunicationRegisterDriver:"+driver);
        fluentFindAndClick(StoreMenu.class,communicationRegister);
        return new CommunicationRegisterPage(driver);
    }

    public ContractRegisterPage clickContractRegister() {
        WebDriver driver = threadDriver.get();
        logger.debug("clickContractRegisterDriver:"+driver);
        fluentFindAndClick(StoreMenu.class,contractRegister);
        return new ContractRegisterPage(driver);
    }
    public PipelineQueryPage clickPipelineQuery() {
        WebDriver driver = threadDriver.get();
        logger.debug("clickPipelineQueryDriver:"+driver);
        fluentFindAndClick(StoreMenu.class,pipelineQuery);
        return new PipelineQueryPage(driver);
    }

    /**
     * 确认操作
     *
     * @author LiuXingHai
     */
    public void confirm() throws InterruptedException {
        //移除弹出层遮罩层，以免对下面的操作造成影响
        fluentFindAndRemoveElement(this.getClass(), layerShade);
        fluentFindAndClick(this.getClass(), confirm);
        fluentFindReturnMessage();
    }

}