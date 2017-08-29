package com.yatou.automation.order;

import com.yatou.automation.common.FindBy;
import com.yatou.automation.common.OrderConstants;
import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * 平台订单改价页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-29
 */
public class OrderChangePricePage extends OrderMenu {

    @FindBy(name="tid",description = "订单号输入框")
    String orderNoInput="orderNoInput";//

    @FindBy(xpath="//button[@type='submit' and contains(.,'查询')]",description = "查询")
    String query="query";//

    @FindBy(linkText="锁定",description = "锁定")
    String lock="lock";

    @FindBy(linkText="改价",description = "改价")
    String changePrice="changePrice";

    @FindBy(name="settlementType",description = "结算方式")
    String settlementType="settlementType";

    @FindBy(id="discount",description = "直减金额/折扣率")
    String discount="discount";

    @FindBy(className="//div[contains(@class,'layui-layer')]//button[@type='submit']", description = "确认")
    String submit="submit";

    @FindBy(xpath="//td/a[contains(@href,'/order/detail/')]",description = "订单号")
    String orderNo="orderNo";//查询结果的订单号

    public OrderChangePricePage(WebDriver driver){
        super(driver);
    }
    /**
     * 订单锁定
     *
     * @param orderNo 订单号
     * @author LiuXingHai
     */
    public void markAparting(String orderNo) throws InterruptedException {

        orderQuery(orderNo);

        fluentFindAndClick(this.getClass(), lock);

        confirm();

        Logger.log("订单["+orderNo+"]锁定成功！");
    }
    /**
     * 订单改价
     *
     * @param orderNo 订单号
     * @author LiuXingHai
     */
    public void changePrice(String orderNo) throws InterruptedException {

        fluentFindAndClick(this.getClass(), changePrice);

        fluentFindAndSelectByValue(this.getClass(), settlementType, OrderConstants.SETTLEMENT_TYPE);

        fluentFindAndType(this.getClass(), discount,OrderConstants.DISCOUNT);

        fluentFindAndClick(this.getClass(), submit);

        fluentFindReturnMessage();

        Logger.log("订单["+orderNo+"]改价成功！");
    }

    /**
     * 订单查询
     *
     * @param orderNo 订单号
     * @author LiuXingHai
     */
    public void orderQuery(String orderNo) throws InterruptedException {

        fluentFindAndSetAttribute(this.getClass(), this.orderNoInput,"value",orderNo);

        fluentFindAndClick(this.getClass(), query);

        //确保查询结果存在
        fluentFind(this.getClass(),this.orderNo,orderNo);
    }
}
