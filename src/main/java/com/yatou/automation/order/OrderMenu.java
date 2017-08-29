package com.yatou.automation.order;

import com.yatou.automation.common.BasePage;
import com.yatou.automation.common.FindBy;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;

/**
 * 平台左侧菜单（外加一些页面通用的元素）
 *
 * @author LiuXingHai
 * @date 2017-08-29
 */
public class OrderMenu extends BasePage{

    @FindBy(linkText="个人中心",description = "个人中心")
    String userCenter="userCenter";

    @FindBy(linkText="公告管理",description = "公告管理")
    String noticeManage="noticeManage";

    @FindBy(linkText="客户查询",description = "客户查询")
    String customerQuery="customerQuery";

    @FindBy(linkText="网络预约",description = "网络预约")
    String internetAppointment="internetAppointment";

    @FindBy(linkText="流水查询",description = "流水查询")
    String pipelineQuery="pipelineQuery";

    @FindBy(linkText="订单查询",description = "订单查询")
    String orderQuery="orderQuery";

    @FindBy(linkText="订单加急",description = "订单加急")
    String orderUrgent="orderUrgent";

    @FindBy(linkText="订单审核",description = "订单审核")
    String orderReview="orderReview";

    @FindBy(linkText="订单拆单",description = "订单拆单")
    String orderApart="orderApart";

    @FindBy(linkText="拆单审核",description = "拆单审核")
    String apartReview="apartReview";

    @FindBy(linkText="订单排料",description = "订单排料")
    String orederSchedule="orederSchedule";

    @FindBy(linkText="订单包装",description = "订单包装")
    String orderPackage="orderPackage";

    @FindBy(linkText="货位查看",description = "货位查看")
    String goodsScan="goodsScan";

    @FindBy(linkText="补单查询",description = "补单查询")
    String orderResupplyQuery="orderResupplyQuery";

    @FindBy(linkText="补单受理",description = "补单受理")
    String orderResupplyConfirm="orderResupplyConfirm";

    @FindBy(linkText="补单拆单",description = "补单拆单")
    String orderResupplyApart="orderResupplyApart";

    @FindBy(linkText="补单拆审",description = "补单拆审")
    String orderResupplyApartReview="orderResupplyApartReview";

    @FindBy(linkText="物料管理",description = "物料管理")
    String cargoManage="cargoManage";

    @FindBy(linkText="仓储管理",description = "仓储管理")
    String warehousingManage="warehousingManage";

    @FindBy(linkText="采购管理",description = "采购管理")
    String purchaseManage="purchaseManage";

    @FindBy(linkText="供应商管理",description = "供应商管理")
    String supplierManage="supplierManage";

    @FindBy(linkText="发货安装",description = "发货安装")
    String dispatchInstall="dispatchInstall";

    @FindBy(linkText="员工管理",description = "员工管理")
    String employeeManage="employeeManage";

    @FindBy(linkText="门店管理",description = "门店管理")
    String storeManage="storeManage";

    @FindBy(linkText="机构管理",description = "机构管理")
    String organisationManage="organisationManage";

    @FindBy(linkText="资料管理",description = "资料管理")
    String dataManage="dataManage";

    @FindBy(linkText="原料入库",description = "原料入库")
    String rawMaterialIn="rawMaterialIn";

    @FindBy(linkText="成品入库",description = "成品入库")
    String productIn="productIn";

    @FindBy(linkText="发货管理",description = "发货管理")
    String dispatchManage="dispatchManage";

    @FindBy(linkText="成品出库",description = "成品出库")
    String productOut="productOut";

    @FindBy(linkText="原料领取",description = "原料领取")
    String rawMaterialGet="rawMaterialGet";

    @FindBy(linkText="基础数据",description = "基础数据")
    String baseData="baseData";

    @FindBy(linkText="订单改价",description = "订单改价")
    String orderChangePrice="orderChangePrice";

    @FindBy(linkText="成品备货",description = "成品备货")
    String productStock ="productStock";

    @FindBy(linkText="库存记录",description = "库存记录")
    String stockRecord="stockRecord";

    @FindBy(linkText="客户统计",description = "客户统计")
    String customerCount="customerCount";

    @FindBy(linkText="模板管理",description = "模板管理")
    String templateManage="templateManage";

    @FindBy(linkText="批次管理",description = "批次管理")
    String batchManage="batchManage";

    @FindBy(linkText="报表管理",description = "报表管理")
    String reportManage="reportManage";

    @FindBy(linkText="补单审核",description = "补单审核")
    String orderResupplyReview="orderResupplyReview";

    @FindBy(linkText="系数配置",description = "系数配置")
    String coefficientConfig ="coefficientConfig";

    @FindBy(linkText="门店资金",description = "门店资金")
    String storeMoney="storeMoney";

    @FindBy(linkText="财务统计",description = "财务统计")
    String financeCount="financeCount";

    @FindBy(linkText="齐套查询",description = "齐套查询")
    String completeSetQuery="completeSetQuery";

    @FindBy(linkText="采购审核",description = "采购审核")
    String purchaseReview="purchaseReview";

    @FindBy(linkText="收款确认",description = "收款确认")
    String receiptConfirm ="receiptConfirm";

    @FindBy(linkText="计划任务",description = "计划任务")
    String taskPlan="taskPlan";

    @FindBy(id="logout",description = "退出登录")
    String logout="logout";//

    @FindBy(id="smallbox1",description = "操作返回信息")
    String message="message";//

    @FindBy(xpath="//div[contains(@class,'flatpickr-calendar')]", description = "日期弹出层")
    String dateLayer="dateLayer";

    @FindBy(id="header", description = "头部")
    String header="header";

    @FindBy(className="layui-layer-btn0", description = "确定")
    String confirm="confirm";

    @FindBy(className="layui-layer-shade",description = "弹出遮罩层")
    String layerShade="layerShade";//


    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OrderMenu.class);
    /*
     * 构造方法
     */
    public OrderMenu(WebDriver driver) {
        super(driver);
    }


    public PipelineQueryPage clickPipelineQuery() {
        WebDriver driver = threadDriver.get();
        logger.debug("clickPipelineQueryDriver:"+driver);
        fluentFindAndClick(OrderMenu.class,pipelineQuery);
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