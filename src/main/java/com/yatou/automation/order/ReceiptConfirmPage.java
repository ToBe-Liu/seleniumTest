package com.yatou.automation.order;

import com.yatou.automation.common.FindBy;
import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * 平台收款确认页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-29
 */
public class ReceiptConfirmPage extends OrderMenu {

    @FindBy(name="tid",description = "订单号输入框")
    String orderNoInput="orderNoInput";//

    @FindBy(xpath="//button[@type='submit' and contains(.,'查询')]",description = "查询")
    String query="query";//

    @FindBy(linkText="确认",description = "确认")
    String affirm="affirm";

    @FindBy(linkText="退回",description = "退回")
    String back="back";

    @FindBy(linkText="撤销",description = "撤销")
    String cancel="cancel";

    @FindBy(xpath="//td/a[contains(@href,'/order/detail/')]",description = "订单号")
    String orderNo="orderNo";//查询结果的订单号

    @FindBy(xpath="//select[@name='type']",description = "收款类型")
    String receiptType="receiptType";

    public ReceiptConfirmPage(WebDriver driver){
        super(driver);
    }
    /**
     * 收款确认
     *
     * @param orderNo 需要锁定的订单号
     * @author LiuXingHai
     */
    public void receiptConfirm(String orderNo) throws InterruptedException {

        queryOrder(orderNo);

        fluentFindAndClick(this.getClass(), affirm);

        confirm();

        Logger.log("收款确认["+orderNo+"]订单成功！");
    }
    /**
     * 收款退回
     *
     * @param orderNo 需要退回的订单号
     * @author LiuXingHai
     */
    public void receiptBack(String orderNo) throws InterruptedException {

        queryOrder(orderNo);

        fluentFindAndClick(this.getClass(), back);

        confirm();

        Logger.log("收款退回["+orderNo+"]订单成功！");
    }
    /**
     * 取消收款
     *
     * @param orderNo 需要取消收款的订单号
     * @author LiuXingHai
     */
    public void receiptCancel(String orderNo) throws InterruptedException {
        fluentFindAndSetAttribute(this.getClass(), this.orderNoInput,"value",orderNo);

        fluentFindAndSelectByValue(this.getClass(),receiptType,"3");

        fluentFindAndClick(this.getClass(), query);

        //确保查询结果存在
        fluentFind(this.getClass(),this.orderNo,orderNo);

        fluentFindAndClick(this.getClass(), cancel);

        confirm();

        Logger.log("取消收款["+orderNo+"]订单成功！");
    }
    /**
     * 查询订单
     *
     * @param orderNo 需要查询的订单号
     * @author LiuXingHai
     */
    public void queryOrder(String orderNo) throws InterruptedException {
        fluentFindAndSetAttribute(this.getClass(), this.orderNoInput,"value",orderNo);

        fluentFindAndClick(this.getClass(), query);

        //确保查询结果存在
        fluentFind(this.getClass(),this.orderNo,orderNo);
    }

}
