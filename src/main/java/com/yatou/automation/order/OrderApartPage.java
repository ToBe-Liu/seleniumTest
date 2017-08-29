package com.yatou.automation.order;

import com.yatou.automation.common.FindBy;
import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * 平台订单拆单页面
 * 本类的字段都是String类型的，不要使用{@link PageFactory#initElements(WebDriver, Class)}来实例化
 *
 * @author LiuXingHai
 * @date 2017-08-29
 */
public class OrderApartPage extends OrderMenu {

    @FindBy(linkText="标记为拆单中",description = "标记为拆单中")
    String markAparting="markAparting";

    @FindBy(linkText="确定拆单",description = "确定拆单")
    String confirmApart="confirmApart";

    @FindBy(id="money",description = "精确价格")
    String exactPrice="exactPrice";

    @FindBy(className="//div[contains(@class,'layui-layer')]//button[contains(text()[2],'审核提交')]", description = "审核提交")
    String submit="submit";


    public OrderApartPage(WebDriver driver){
        super(driver);
    }
    /**
     * 标记为拆单中
     *
     * @param orderNo 订单号
     * @author LiuXingHai
     */
    public void markAparting(String orderNo) throws InterruptedException {

        fluentFindAndClick(this.getClass(), markAparting);

        confirm();

        Logger.log("标记["+orderNo+"]为拆单中成功！");
    }
    /**
     * 确定拆单
     *
     * @param orderNo 订单号
     * @author LiuXingHai
     */
    public void confirmApart(String orderNo) throws InterruptedException {

        fluentFindAndClick(this.getClass(), confirmApart);

        fluentFindAndType(this.getClass(), exactPrice,"10000");

        fluentFindAndClick(this.getClass(), submit);

        fluentFindReturnMessage();

        Logger.log("确定拆单["+orderNo+"]成功！");
    }
}
