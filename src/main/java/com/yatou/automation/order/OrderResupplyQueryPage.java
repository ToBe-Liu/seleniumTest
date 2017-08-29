package com.yatou.automation.order;

import com.yatou.automation.common.FindBy;
import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * 平台补单查询页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-29
 */
public class OrderResupplyQueryPage extends OrderMenu {

    @FindBy(xpath="//button[@type='submit']",description = "查询")
    String submit="submit";//

    @FindBy(name="tid",description = "补单号输入框")
    String orderResupplyNoInput="orderResupplyNoInput";//

    @FindBy(xpath="//td/a[contains(@href,'/order/resupply/detail/')]",description = "补单号")
    String orderResupplyNo="orderResupplyNo";//查询结果的补单号

    public OrderResupplyQueryPage(WebDriver driver){
        super(driver);
    }
    /**
     * 补单查询
     *
     * @param orderResupplyNo 需要查询的补单号
     * @author LiuXingHai
     */
    public void orderNoQuery(String orderResupplyNo) throws InterruptedException {

        fluentFindAndSetAttribute(this.getClass(), this.orderResupplyNoInput,"value",orderResupplyNo);

        fluentFindAndClick(this.getClass(), submit);

        fluentFindAndClickByText(this.getClass(), this.orderResupplyNo,orderResupplyNo);

        Logger.log("查询[" + orderResupplyNo + "]成功！");
    }
}
