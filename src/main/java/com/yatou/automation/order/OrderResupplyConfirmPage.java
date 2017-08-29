package com.yatou.automation.order;

import com.yatou.automation.common.FindBy;
import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * 平台补单受理页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-29
 */
public class OrderResupplyConfirmPage extends OrderMenu {

    @FindBy(linkText="标记为受理中",description = "标记为受理中")
    String markOrderResupplyConfirming="markOrderResupplyConfirming";//

    @FindBy(linkText="确认受理",description = "确认受理")
    String affirm="affirm";

    @FindBy(linkText="补单原因",description = "补单原因")
    String resupplyReason="resupplyReason";//补单原因按钮

    @FindBy(xpath="(//input[@name='causeStr']/..)[1]",description = "补单原因")
    String resupplyReason1="resupplyReason1";//补单原因选择

    @FindBy(className="//div[contains(@class,'layui-layer')]//button", description = "确认")
    String submit="submit";//补单原因确认

    public OrderResupplyConfirmPage(WebDriver driver){
        super(driver);
    }
    /**
     * 标记为受理中
     *
     * @param orderResupplyNo 需要标记的补单号
     * @author LiuXingHai
     */
    public void markOrderResupplyConfirming(String orderResupplyNo) throws InterruptedException {

        fluentFindAndClick(this.getClass(), markOrderResupplyConfirming);

        confirm();

        Logger.log("标记["+orderResupplyNo+"]为受理中成功！");
    }
    /**
     * 补单原因
     *
     * @param orderResupplyNo 需要确认的补单号
     * @author LiuXingHai
     */
    public void resupplyReason(String orderResupplyNo) throws InterruptedException {

        fluentFindAndClick(this.getClass(), resupplyReason);

        fluentFindAndClick(this.getClass(), resupplyReason1);

        fluentFindAndClick(this.getClass(), submit);

        fluentFindReturnMessage();

        Logger.log("选择["+orderResupplyNo+"]补单原因成功！");
    }
    /**
     * 确认受理
     *
     * @param orderResupplyNo 需要确认的补单号
     * @author LiuXingHai
     */
    public void affirm(String orderResupplyNo) throws InterruptedException {

        fluentFindAndClick(this.getClass(), affirm);

        confirm();

        Logger.log("标记["+orderResupplyNo+"]为受理中成功！");
    }
}
