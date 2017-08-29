package com.yatou.automation.order;

import com.yatou.automation.common.FindBy;
import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * 平台订单查询页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-29
 */
public class OrderQueryPage extends OrderMenu {

    @FindBy(xpath="//button[@type='submit']",description = "查询")
    String submit="submit";//

    @FindBy(name="tid",description = "订单号输入框")
    String orderNoInput="orderNoInput";//

    @FindBy(xpath="//td/a[contains(@href,'/order/detail/')]",description = "订单号")
    String orderNo="orderNo";//查询结果的订单号

    public OrderQueryPage(WebDriver driver){
        super(driver);
    }
    /**
     * 订单查询
     *
     * @param orderNo 需要查询的订单号
     * @author LiuXingHai
     */
    public void orderNoQuery(String orderNo) throws InterruptedException {

        fluentFindAndSetAttribute(this.getClass(), this.orderNoInput,"value",orderNo);

        fluentFindAndClick(this.getClass(), submit);

        fluentFindAndClickByText(this.getClass(), this.orderNo,orderNo);

        Logger.log("查询[" + orderNo + "]成功！");
    }
}
